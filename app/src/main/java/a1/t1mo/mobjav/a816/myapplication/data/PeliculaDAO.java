package a1.t1mo.mobjav.a816.myapplication.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.data.services.ServiceFactory;
import a1.t1mo.mobjav.a816.myapplication.data.services.TmdbService;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.ListadoPeliculas;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 22/10/2016.
 */

public class PeliculaDAO {
    private static final String TAG = PeliculaDAO.class.getSimpleName();
    private static TmdbService sTmdbService;
    private Realm mRealm;
    private static PeliculaDAO sInstance;

    private PeliculaDAO() {
        sTmdbService = ServiceFactory.getTmdbService();
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .name("peliculas.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        mRealm = Realm.getInstance(config);
    }

    public static PeliculaDAO getDAO() {
        sInstance = new PeliculaDAO();
        return sInstance;
    }

    public static void closeRealm() {
        if (sInstance.mRealm != null) sInstance.mRealm.close();
    }

    public Boolean isPersisted(Integer id) {
        return mRealm.where(Pelicula.class).equalTo("id", id).count() == 0;
    }

    public void getPeliculaDeTmdb(final Integer id, final Listener<Pelicula> listener) {
        sTmdbService.getPelicula(id).enqueue(new Callback<Pelicula>() {
            @Override
            public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                if (response.isSuccessful()) {
                    persistirEnRealm(response.body());
                    listener.done(getPeliculaDeRealm(id));
                } else {
                    Log.e(TAG, "El servidor respondio con el codigo " + response.code() +
                            " Llamando a getPeliculaDeTmdb(" + id + ")");
                }
            }

            @Override
            public void onFailure(Call<Pelicula> call, Throwable t) {
                Log.e(TAG, "No se pudo obtener la pelicula.");
            }
        });
    }

    public Pelicula getPeliculaDeRealm(Integer id) {
        return mRealm.where(Pelicula.class).equalTo("id", id).findFirst();
    }

    public void getPeliculasPopularesDeTmdb(final Listener<List<? extends Feature>> listener) {
        sTmdbService.getPeliculasPopulares().enqueue(new Callback<ListadoPeliculas>() {
            @Override
            public void onResponse(Call<ListadoPeliculas> call, Response<ListadoPeliculas> response) {
                if (response.isSuccessful()) {
                    persistirEnRealm(response.body().getPeliculas());
                    Log.d(TAG, "Cantidad de peliculas recibidas " + response.body().getPeliculas().size());
                    listener.done(getPeliculasPopularesDeRealm());
                } else {
                    Log.e(TAG, "El servidor respondio con el codigo " + response.code() +
                            " Llamando a getPeliculasPopularesDeTmdb()");
                }
            }

            @Override
            public void onFailure(Call<ListadoPeliculas> call, Throwable t) {
                Log.e(TAG, "No se pudo obtener la lista de peliculas populares.");
            }
        });
    }

    public List<Pelicula> getPeliculasPopularesDeRealm() {
        return mRealm.where(Pelicula.class).findAllSorted("popularidad", Sort.DESCENDING);
    }

    public void getPeliculasPorGeneroDeTmdb(final Integer id, final Listener<List<? extends Feature>> listener) {
        sTmdbService.getPeliculasPorGenero(id).enqueue(new Callback<ListadoPeliculas>() {
            @Override
            public void onResponse(Call<ListadoPeliculas> call, Response<ListadoPeliculas> response) {
                if (response.isSuccessful()) {
                    persistirEnRealm(response.body().getPeliculas());
                    listener.done(getPeliculasPorGeneroDeRealm(id));
                } else {
                    Log.e(TAG, "El servidor respondio con el codigo " + response.code() +
                            " Llamando a getPeliculasPorGeneroDeTmdb(" + id + ")");
                }
            }

            @Override
            public void onFailure(Call<ListadoPeliculas> call, Throwable t) {
                Log.e(TAG, "No se pudo obtener la lista de peliculas por genero.");
            }
        });
    }

    public List<Pelicula> getPeliculasPorGeneroDeRealm(Integer id) {
        List<Pelicula> peliculas =
                mRealm
                    .where(Pelicula.class)
                    .equalTo("generos.id", 28)
                    .findAllSorted("popularidad", Sort.DESCENDING);

        Log.d(TAG, "Cantidad de peliculas: " + peliculas.size());
        return peliculas;
    }

    public List<Pelicula> getFavoritos() {
        return mRealm.where(Pelicula.class).equalTo("favorito", true).findAll();

    }

    private void persistirEnRealm(final Pelicula pelicula) {
        mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(pelicula);
                }
        });
    }

    private void persistirEnRealm(final RealmList<Pelicula> peliculas) {
        Log.d(TAG, "Entramos a persistirEnRealm");
        for (final Pelicula pelicula : peliculas) {
            if (mRealm.where(Pelicula.class).equalTo("id", pelicula.getId()).findFirst() == null) {
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealmOrUpdate(pelicula);
                        Log.d(TAG, "Guardamos la pelicula: " + pelicula.getTitulo());
                    }
                });
            }
        }
    }

    public void setFavorito(final Integer id, final boolean isFav) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Pelicula pelicula = realm.where(Pelicula.class).equalTo("id", id).findFirst();
                if (pelicula != null) {
                    pelicula.setFavorito(isFav);
                    realm.copyToRealmOrUpdate(pelicula);
                }
            }
        });
    }
}
