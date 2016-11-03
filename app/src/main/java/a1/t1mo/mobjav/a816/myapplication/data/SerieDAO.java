package a1.t1mo.mobjav.a816.myapplication.data;

import android.util.Log;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.data.services.ServiceFactory;
import a1.t1mo.mobjav.a816.myapplication.data.services.TmdbService;
import a1.t1mo.mobjav.a816.myapplication.model.ListadoSeries;
import a1.t1mo.mobjav.a816.myapplication.model.Serie;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;
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
    private TmdbService mTmdbService;

    public SerieDAO() {
        mTmdbService = ServiceFactory.getTmdbService();
    }

    public void getSerie(Integer id, final Listener<Serie> listener) {
        mTmdbService.getSerie(id).enqueue(new Callback<Serie>() {
            @Override
            public void onResponse(Call<Serie> call, Response<Serie> response) {
                if (response.isSuccessful()) {
                    listener.done(response.body());
                } else {
                    Log.e(TAG, "El servidor respondio con el codigo " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Serie> call, Throwable t) {
                Log.e(TAG, "No se pudo obtener la pelicula.");
            }
        });
    }

    public void getSeriesPopulares(final Listener<List<Serie>> listener) {
        mTmdbService.getSeriesPopulares().enqueue(new Callback<ListadoSeries>() {
            @Override
            public void onResponse(Call<ListadoSeries> call, Response<ListadoSeries> response) {
                if (response.isSuccessful()) {
                    listener.done(response.body().getSeries());
                } else {
                    Log.e(TAG, "El servidor respondio con el codigo " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ListadoSeries> call, Throwable t) {
                Log.e(TAG, "No se pudo obtener la pelicula.");
            }
        });
    }

    public void getSeriesPorGenero(Integer id, final Listener<List<Serie>> listener) {
        mTmdbService.getSeriesPorGenero(id).enqueue(new Callback<ListadoSeries>() {
            @Override
            public void onResponse(Call<ListadoSeries> call, Response<ListadoSeries> response) {
                if (response.isSuccessful()) {
                    listener.done(response.body().getSeries());
                } else {
                    Log.e(TAG, "El servidor respondio con el codigo " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ListadoSeries> call, Throwable t) {
                Log.e(TAG, "No se pudo obtener la pelicula.");
            }
        });
    }
}
