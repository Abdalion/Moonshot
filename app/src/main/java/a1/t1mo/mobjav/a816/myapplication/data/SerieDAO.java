package a1.t1mo.mobjav.a816.myapplication.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.controller.Controller;
import a1.t1mo.mobjav.a816.myapplication.data.services.ServiceFactory;
import a1.t1mo.mobjav.a816.myapplication.data.services.TmdbService;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.model.serie.ListadoSeries;
import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;
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
 * Archivo creado por Juan Pablo on 03/11/2016.
 */

public class SerieDAO {
    private static final String TAG = SerieDAO.class.getSimpleName();
    private static TmdbService sTmdbService;
    private Realm mRealm;
    private static SerieDAO sInstance;

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
        return mRealm.where(Serie.class).equalTo("id", id).count() == 0;
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

    public void getSeriesPopularesDeTmdb(final Listener<List<? extends Feature>> listener) {
        sTmdbService.getSeriesPopulares().enqueue(new Callback<ListadoSeries>() {
            @Override
            public void onResponse(Call<ListadoSeries> call, Response<ListadoSeries> response) {
                if (response.isSuccessful()) {
                    persistirEnRealm(response.body().getSeries());
                    listener.done(getSeriesPopularesDeRealm());
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
        return mRealm.where(Serie.class).findAllSorted("popularidad", Sort.DESCENDING);
    }

    public void getSeriesPorGeneroDeTmdb(final Integer id, final Listener<List<? extends Feature>> listener) {
        sTmdbService.getSeriesPorGenero(id).enqueue(new Callback<ListadoSeries>() {
            @Override
            public void onResponse(Call<ListadoSeries> call, Response<ListadoSeries> response) {
                if (response.isSuccessful()) {
                    persistirEnRealm(response.body().getSeries());
                    listener.done(getSeriesPorGeneroDeRealm(id));
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

    public List<Serie> getSeriesPorGeneroDeRealm(Integer id) {
        return mRealm
                .where(Serie.class)
                .equalTo("generos.id", id)
                .findAllSorted("popularidad", Sort.DESCENDING);
    }

    private void persistirEnRealm(final Serie serie) {
        serie.setFavorito(false);
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(serie);
            }
        });
    }

    private void persistirEnRealm(final RealmList<Serie> series) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                RealmResults<Serie> persistidas = bgRealm.where(Serie.class).findAll();
                List<Integer> ids = new ArrayList<>();
                for (Serie serie : persistidas) {
                    ids.add(serie.getId());
                }
                for (Serie serie : series) {
                    if (!ids.contains(serie.getId())) {
                        bgRealm.copyToRealmOrUpdate(serie);
                    }
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "Se persistieron las series correctamente.");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG, "Error al persistir las series");
            }
        });
    }

    public void setFavorito(final int id, final boolean isFav) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Serie serie = realm.where(Serie.class).equalTo("id", id).findFirst();
                if (serie != null) {
                    serie.setFavorito(isFav);
                    realm.copyToRealmOrUpdate(serie);
                }
            }
        });
    }

    public List<Serie> getFavoritos() {
        return mRealm.where(Serie.class).equalTo("favorito", true).findAll();
    }
}
