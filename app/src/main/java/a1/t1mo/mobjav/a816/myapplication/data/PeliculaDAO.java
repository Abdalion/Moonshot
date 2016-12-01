package a1.t1mo.mobjav.a816.myapplication.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import a1.t1mo.mobjav.a816.myapplication.controller.Controller;
import a1.t1mo.mobjav.a816.myapplication.data.services.ServiceFactory;
import a1.t1mo.mobjav.a816.myapplication.data.services.TmdbService;
import a1.t1mo.mobjav.a816.myapplication.model.User;
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
        return mRealm.where(Pelicula.class).equalTo("id", id).count() == 0;
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

    public Pelicula getPeliculaDeRealm(Integer id) {
        return mRealm.where(Pelicula.class).equalTo("id", id).findFirst();
    }

    public void getPeliculasPopularesDeTmdb(final Controller.ListenerPeliculas listener) {
        sTmdbService.getPeliculasPopulares().enqueue(new Callback<ListadoPeliculas>() {
            @Override
            public void onResponse(Call<ListadoPeliculas> call, Response<ListadoPeliculas> response) {
                if (response.isSuccessful()) {
                    persistirEnRealm(response.body().getPeliculas());
                    listener.onFinish(getPeliculasPopularesDeRealm());
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

    public void getPeliculasPorGeneroDeTmdb(final Integer id, final Controller.ListenerPeliculas listener) {
        sTmdbService.getPeliculasPorGenero(id).enqueue(new Callback<ListadoPeliculas>() {
            @Override
            public void onResponse(Call<ListadoPeliculas> call, Response<ListadoPeliculas> response) {
                if (response.isSuccessful()) {
                    persistirEnRealm(response.body().getPeliculas());
                    listener.onFinish(getPeliculasPorGeneroDeRealm(id));
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
        return mRealm
                    .where(Pelicula.class)
                    .equalTo("generos.id", id)
                    .findAllSorted("popularidad", Sort.DESCENDING);
    }

    public List<Pelicula> getFavoritos(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if(!(FirebaseAuth.getInstance().getCurrentUser() == null)
                && !(activeNetwork == null)
                && (activeNetwork.isConnectedOrConnecting())) {
            mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference userReference = FirebaseDatabase.getInstance().getReference().child("users").child(mFirebaseUser.getUid());
            userReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User myUser = dataSnapshot.getValue(User.class);
                    Map<String, Integer> myMap = (Map<String, Integer>) myUser.getPeliculasFavoritas();
                    for(Integer i : myMap.values()) {
                        setFavoritoRealm(i, true);
                    }
                    mListaDeFavoritos = mRealm.where(Pelicula.class).equalTo("favorito", true).findAll();
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
        else {
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
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                RealmResults<Pelicula> persistidas = bgRealm.where(Pelicula.class).findAll();
                List<Integer> ids = new ArrayList<>();
                for (Pelicula pelicula : persistidas) {
                    ids.add(pelicula.getId());
                }
                for (Pelicula pelicula : peliculas) {
                    if (!ids.contains(pelicula.getId())) {
                        bgRealm.copyToRealmOrUpdate(pelicula);
                    }
                }
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
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Pelicula pelicula = realm.where(Pelicula.class).equalTo("id", id).findFirst();
                if (pelicula != null) {
                    pelicula.setFavorito(true);
                    realm.copyToRealmOrUpdate(pelicula);
                }
                Log.d("db", realm.where(Pelicula.class).equalTo("favorito", true).findFirst().getTitulo());

                if (pelicula.isFavorito()) {
                    Log.d(TAG, "La pelicula " + pelicula.getTitulo() + " fue agregada a favoritos");
                }
            }
        });
    }

    public void quitarDeFavoritos(final Integer id) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Pelicula pelicula = realm.where(Pelicula.class).equalTo("id", id).findFirst();
                if (pelicula != null) {
                    pelicula.setFavorito(false);
                    realm.copyToRealmOrUpdate(pelicula);
                }
                if (!pelicula.isFavorito()) {
                    Log.d(TAG, "La pelicula " + pelicula.getTitulo() + " fue removida de favoritos");
                }
            }
        });
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
        if(isFav) {
            databaseReference.push().setValue(id);
        }
        else if(!(isFav)) {
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
}
