package a1.t1mo.mobjav.a816.myapplication.data;

import android.util.Log;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.data.services.ServiceFactory;
import a1.t1mo.mobjav.a816.myapplication.data.services.TmdbService;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.ListadoPeliculas;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
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
    private static Realm sRealm;
    private static PeliculaDAO sInstance;

    private PeliculaDAO() {
        sTmdbService = ServiceFactory.getTmdbService();
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .name("peliculas.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        sRealm = Realm.getInstance(config);
    }

    public static PeliculaDAO getDAO() {
        sInstance = new PeliculaDAO();
        return sInstance;
    }

    public static void closeRealm() {
        if (sRealm != null) sRealm.close();
    }

    public Boolean isPersisted(Integer id) {
        return sRealm.where(Pelicula.class).equalTo("id", id).count() == 0;
    }

    public void getPeliculaDeTmdb(final Integer id, final Listener<Pelicula> listener) {
        sTmdbService.getPelicula(id).enqueue(new Callback<Pelicula>() {
            @Override
            public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                if (response.isSuccessful()) {
                    persistirEnRealm(response.body());
                    listener.done(response.body());
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

    public void getPeliculasPopularesDeTmdb(final Listener<List<Pelicula>> listener) {
        sTmdbService.getPeliculasPopulares().enqueue(new Callback<ListadoPeliculas>() {
            @Override
            public void onResponse(Call<ListadoPeliculas> call, Response<ListadoPeliculas> response) {
                if (response.isSuccessful()) {
                    persistirEnRealm(response.body().getPeliculas());
                    listener.done(response.body().getPeliculas());
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

    public void getPeliculasPorGeneroDeTmdb(final Integer id, final Listener<List<Pelicula>> listener) {
        sTmdbService.getPeliculasPorGenero(id).enqueue(new Callback<ListadoPeliculas>() {
            @Override
            public void onResponse(Call<ListadoPeliculas> call, Response<ListadoPeliculas> response) {
                if (response.isSuccessful()) {
                    persistirEnRealm(response.body().getPeliculas());
                    listener.done(response.body().getPeliculas());
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

    public Pelicula getPeliculaDeRealm(Integer id) {
        return sRealm.where(Pelicula.class).equalTo("id", id).findFirst();
    }

    public List<Pelicula> getPeliculasPopularesDeRealm() {
        return sRealm.where(Pelicula.class).findAllSorted("popularidad", Sort.DESCENDING);
    }

    public List<Pelicula> getPeliculasPorGeneroDeRealm(Integer id) {
        return sRealm
                    .where(Pelicula.class)
                    .equalTo("generos.id", id)
                    .findAllSorted("popularidad", Sort.DESCENDING);
    }

    public List<Pelicula> getFavoritos() {
        return sRealm.where(Pelicula.class).equalTo("favorito", true).findAll();
    }

    private void persistirEnRealm(final Pelicula pelicula) {
        pelicula.setFavorito(false);
        sRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(pelicula);
                }
        });
    }

    private void persistirEnRealm(final RealmList<Pelicula> peliculas) {
        for (Pelicula pelicula : peliculas) {
            pelicula.setFavorito(false);
        }

        sRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.copyToRealmOrUpdate(peliculas);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "Se persistieron las peliculas correctamente.");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG, "Error al persistir las peliculas");
            }
        });
    }

    public void agregarAFavoritos(final Integer id) {
        sRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Pelicula pelicula = realm.where(Pelicula.class).equalTo("id", id).findFirst();
                if (pelicula != null) pelicula.setFavorito(true);
            }
        });
    }

    public void quitarDeFavoritos(final Integer id) {
        sRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Pelicula pelicula = realm.where(Pelicula.class).equalTo("id", id).findFirst();
                if (pelicula != null) pelicula.setFavorito(false);
            }
        });
    }
}
