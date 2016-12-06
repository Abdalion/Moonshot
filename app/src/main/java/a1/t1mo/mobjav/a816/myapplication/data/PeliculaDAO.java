package a1.t1mo.mobjav.a816.myapplication.data;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Map;

import a1.t1mo.mobjav.a816.myapplication.data.services.ServiceFactory;
import a1.t1mo.mobjav.a816.myapplication.data.services.TmdbService;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.model.User;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.ListadoPeliculas;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.utils.ConnectivityCheck;
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
    private static final int PAGE_SIZE = 20;
    private static TmdbService sTmdbService;
    private Realm mRealm;
    private static PeliculaDAO sInstance;
    private FirebaseUser mFirebaseUser;
    private List<Pelicula> mListaDeFavoritos;

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
        return mRealm.where(Pelicula.class).equalTo("id", id).count() == 1;
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
        sTmdbService.getPeliculasPopulares(1).enqueue(new Callback<ListadoPeliculas>() {
            @Override
            public void onResponse(Call<ListadoPeliculas> call, Response<ListadoPeliculas> response) {
                if (response.isSuccessful()) {
                    persistirEnRealm(response.body().getPeliculas());
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

    public void getPeliculasPopularesDeTmdb(final int page, final Listener<List<? extends Feature>> listener) {
        sTmdbService.getPeliculasPopulares(page).enqueue(new Callback<ListadoPeliculas>() {
            @Override
            public void onResponse(Call<ListadoPeliculas> call, Response<ListadoPeliculas> response) {
                if (response.isSuccessful()) {
                    persistirEnRealm(response.body().getPeliculas());
                    listener.done(getPeliculasPopularesDeRealm(page));
                } else {
                    Log.e(TAG, "El servidor respondio con el codigo " + response.code() +
                            " Llamando a getPeliculasPopularesDeTmdb()" + " Pagina: " + page);
                }
            }

            @Override
            public void onFailure(Call<ListadoPeliculas> call, Throwable t) {
                Log.e(TAG, "No se pudo obtener la lista de peliculas populares.");
            }
        });
    }

    public List<Pelicula> getPeliculasPopularesDeRealm() {
        return mRealm
                .where(Pelicula.class)
                .findAllSorted("popularidad", Sort.DESCENDING)
                .subList(0, PAGE_SIZE);
    }

    public List<Pelicula> getPeliculasPopularesDeRealm(int page) {
        int cantidadDePeliculas = (int) mRealm.where(Pelicula.class).count();
        int indice = (page + 1) * PAGE_SIZE < cantidadDePeliculas ? (page + 1) * PAGE_SIZE : cantidadDePeliculas;
        return mRealm
                    .where(Pelicula.class)
                    .findAllSorted("popularidad", Sort.DESCENDING)
                    .subList(0, indice);
    }

    public void getPeliculasPorGeneroDeTmdb(final String id, final Listener<List<? extends Feature>> listener) {
        sTmdbService.getPeliculasPorGenero(id, 1).enqueue(new Callback<ListadoPeliculas>() {
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

    public void getPeliculasPorGeneroDeTmdb(final int page, final String id, final Listener<List<? extends Feature>> listener) {
        sTmdbService.getPeliculasPorGenero(id, page).enqueue(new Callback<ListadoPeliculas>() {
            @Override
            public void onResponse(Call<ListadoPeliculas> call, Response<ListadoPeliculas> response) {
                if (response.isSuccessful()) {
                    persistirEnRealm(response.body().getPeliculas());
                    listener.done(getPeliculasPorGeneroDeRealm(page, id));
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

    public List<Pelicula> getPeliculasPorGeneroDeRealm(String id) {
        return mRealm
                .where(Pelicula.class)
                .equalTo("generos.value", id)
                .findAllSorted("popularidad", Sort.DESCENDING)
                .subList(0, PAGE_SIZE);
    }

    public List<Pelicula> getPeliculasPorGeneroDeRealm(int page, String id) {
        int cantidadDePeliculas = (int) mRealm.where(Pelicula.class).count();
        int indice = (page + 1) * PAGE_SIZE < cantidadDePeliculas ? (page + 1) * PAGE_SIZE : cantidadDePeliculas;
        return mRealm
                    .where(Pelicula.class)
                    .equalTo("generos.value", id)
                    .findAllSorted("popularidad", Sort.DESCENDING)
                    .subList(0, indice);
    }

    public List<Pelicula> getFavoritos(Context context) {
        if (!(FirebaseAuth.getInstance().getCurrentUser() == null)
                && ConnectivityCheck.hasConnectivity(context)) {
            mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference userReference = FirebaseDatabase.getInstance().getReference().child("users").child(mFirebaseUser.getUid());
            userReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User myUser = dataSnapshot.getValue(User.class);
                    Map<String, Integer> myMap = myUser.getPeliculasFavoritas();
                    for (Integer i : myMap.values()) {
                        setFavoritoRealm(i, true);
                    }
                    mListaDeFavoritos = mRealm.where(Pelicula.class).equalTo("favorito", true).findAll();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        } else {
            mListaDeFavoritos = mRealm.where(Pelicula.class).equalTo("favorito", true).findAll();
        }
        return mListaDeFavoritos;
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
                    }
                });
            }
        }
    }

    private void setFavoritoRealm(final Integer id, final boolean isFav) {
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

    public void setFavorito(Integer id, boolean isFav) {
        setFavoritoRealm(id, isFav);
        setFavoritoFirebase(id, isFav);
    }

    private void setFavoritoFirebase(final Integer id, boolean isFav) {
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(mFirebaseUser.getUid()).child("peliculasFavoritas");
        if (isFav) {
            databaseReference.push().setValue(id);
        } else {
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Map<String, Integer> map = (Map<String, Integer>) dataSnapshot.getValue();
                    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        Long longId = new Long(id);
                        if (longId.equals(entry.getValue()))
                            databaseReference.child(entry.getKey()).removeValue();

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
    }

    public boolean isLastPage(int page) {
        int cantidadDePeliculas = (int) mRealm.where(Pelicula.class).count();
        return (page >= cantidadDePeliculas % PAGE_SIZE && cantidadDePeliculas - page * PAGE_SIZE <= 0);
    }
}
