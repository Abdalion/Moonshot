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
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.model.serie.ListadoSeries;
import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;
import a1.t1mo.mobjav.a816.myapplication.utils.ConnectivityCheck;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.Sort;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static a1.t1mo.mobjav.a816.myapplication.utils.UserActions.isUserLogged;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 03/11/2016.
 */

public class SerieDAO {
    private static final String TAG = SerieDAO.class.getSimpleName();
    private static final int PAGE_SIZE = 20;
    private static TmdbService sTmdbService;
    private Realm mRealm;
    private static SerieDAO sInstance;
    private FirebaseUser mFirebaseUser;

    private SerieDAO() {
        sTmdbService = ServiceFactory.getTmdbService();
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .name("series.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        mRealm = Realm.getInstance(config);
    }

    public static SerieDAO getDAO() {
        sInstance = new SerieDAO();
        return sInstance;
    }

    public static void closeRealm() {
        if (sInstance.mRealm != null) sInstance.mRealm.close();
    }

    public Boolean isPersisted(Integer id) {
        return mRealm.where(Serie.class).equalTo("id", id).count() == 1;
    }

    public void getSerieDeTmdb(final Integer id, final Listener<Serie> listener) {
        sTmdbService.getSerie(id).enqueue(new Callback<Serie>() {
            @Override
            public void onResponse(Call<Serie> call, Response<Serie> response) {
                if (response.isSuccessful()) {
                    persistirEnRealm(response.body());
                    listener.done(getSerieDeRealm(id));
                } else {
                    Log.e(TAG, "El servidor respondio con el codigo " + response.code() +
                            " Llamando a getSerieDeTmdb(" + id + ")");
                }
            }

            @Override
            public void onFailure(Call<Serie> call, Throwable t) {
                Log.e(TAG, "No se pudo obtener la serie.");
            }
        });
    }

    public Serie getSerieDeRealm(Integer id) {
        return mRealm.where(Serie.class).equalTo("id", id).findFirst();
    }

    public void getSeriesPopularesDeTmdb(final int page, final Listener<List<? extends Feature>> listener) {
        sTmdbService.getSeriesPopulares(page).enqueue(new Callback<ListadoSeries>() {
            @Override
            public void onResponse(Call<ListadoSeries> call, Response<ListadoSeries> response) {
                if (response.isSuccessful()) {
                    persistirEnRealm(response.body().getSeries());
                    listener.done(getSeriesPopularesDeRealm(page));
                } else {
                    Log.e(TAG, "El servidor respondio con el codigo " + response.code() +
                            " Llamando a getSeriesPopularesDeTmdb()");
                }
            }

            @Override
            public void onFailure(Call<ListadoSeries> call, Throwable t) {
                Log.e(TAG, "No se pudo obtener la lista de series populares.");
            }
        });
    }

    public List<Serie> getSeriesPopularesDeRealm() {
        return mRealm
                .where(Serie.class)
                .findAllSorted("popularidad", Sort.DESCENDING)
                .subList(0, PAGE_SIZE);
    }

    public List<Serie> getSeriesPopularesDeRealm(int page) {
        int cantidadDeSeries = (int) mRealm.where(Serie.class).count();
        int indice = (page + 1) * PAGE_SIZE < cantidadDeSeries ? (page + 1) * PAGE_SIZE : cantidadDeSeries;
        return mRealm
                .where(Serie.class)
                .findAllSorted("popularidad", Sort.DESCENDING)
                .subList(0, indice);
    }

    public void getSeriesPorGeneroDeTmdb(final int page, final String id, final Listener<List<? extends Feature>> listener) {
        sTmdbService.getSeriesPorGenero(id, page).enqueue(new Callback<ListadoSeries>() {
            @Override
            public void onResponse(Call<ListadoSeries> call, Response<ListadoSeries> response) {
                if (response.isSuccessful()) {
                    persistirEnRealm(response.body().getSeries());
                    listener.done(getSeriesPorGeneroDeRealm(page, id));
                } else {
                    Log.e(TAG, "El servidor respondio con el codigo " + response.code() +
                            " Llamando a getSeriesPorGeneroDeTmdb(" + id + ")");
                }
            }

            @Override
            public void onFailure(Call<ListadoSeries> call, Throwable t) {
                Log.e(TAG, "No se pudo obtener la lista de series por genero.");
            }
        });
    }

    public List<Serie> getSeriesPorGeneroDeRealm(String id) {
        return mRealm
                .where(Serie.class)
                .equalTo("generos.value", id)
                .findAllSorted("popularidad", Sort.DESCENDING)
                .subList(0, PAGE_SIZE);
    }

    public List<Serie> getSeriesPorGeneroDeRealm(int page, String id) {
        int cantidadDeSeries = (int) mRealm.where(Serie.class).equalTo("generos.value", id).count();
        int indice = (page + 1) * PAGE_SIZE < cantidadDeSeries ? (page + 1) * PAGE_SIZE : cantidadDeSeries;
        return mRealm
                .where(Serie.class)
                .equalTo("generos.value", id)
                .findAllSorted("popularidad", Sort.DESCENDING)
                .subList(0, indice);
    }

    private void persistirEnRealm(final Serie serie) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(serie);
            }
        });
    }

    private void persistirEnRealm(final RealmList<Serie> series) {
        for (final Serie serie : series) {
            if (mRealm.where(Serie.class).equalTo("id", serie.getId()).findFirst() == null) {
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealmOrUpdate(serie);
                        Log.d(TAG, "Guardamos la serie: " + serie.getNombre());
                    }
                });
            }
        }
    }

    private void setFavoritoRealm(final int id, final boolean isFav) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Serie serie = realm.where(Serie.class).equalTo("id", id).findFirst();
                if (isNot(serie == null)) {
                    serie.setFavorito(isFav);
                    realm.copyToRealmOrUpdate(serie);
                }
            }
        });
    }

    public void setFavorito(final int id, final boolean isFav) {
        setFavoritoRealm(id, isFav);
        setFavoritoFirebase(id, isFav);
    }

    private void setFavoritoFirebase(final Integer id, boolean isFav) {
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(mFirebaseUser.getUid()).child("seriesFavoritas");
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

    public void getFavoritos(Context context, final Listener<List<? extends Feature>> listener) {
        if (FirebaseAuth.getInstance().getCurrentUser() != null
                && ConnectivityCheck.hasConnectivity(context)) {
            mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference userReference = FirebaseDatabase.getInstance().getReference().child("users").child(mFirebaseUser.getUid());
            userReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User myUser = dataSnapshot.getValue(User.class);
                    Map<String, Integer> myMap = (Map<String, Integer>) myUser.getSeriesFavoritas();
                    for (Integer i : myMap.values()) {
                        setFavoritoRealm(i, true);
                    }
                    listener.done(getFavoritosDeRealm());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        } else {
            if (!isUserLogged()) {
                for (Serie serie : getFavoritosDeRealm()) {
                    setFavoritoRealm(serie.getId(), false);
                }
            } else {
                listener.done(getFavoritosDeRealm());
            }
        }
    }

    private List<Serie> getFavoritosDeRealm() {
        return mRealm.where(Serie.class).equalTo("favorito", true).findAll();
    }

    private boolean isNot(boolean bool) {
        return !bool;
    }

    public boolean isLastPage(int page) {
        int cantidadDeSeries = (int) mRealm.where(Serie.class).count();
        return (page >= cantidadDeSeries % PAGE_SIZE && cantidadDeSeries - page * PAGE_SIZE <= 0);
    }
}
