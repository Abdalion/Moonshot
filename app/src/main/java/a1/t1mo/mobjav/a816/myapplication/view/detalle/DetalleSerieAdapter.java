package a1.t1mo.mobjav.a816.myapplication.view.detalle;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.controller.SerieController;
import a1.t1mo.mobjav.a816.myapplication.model.Genre;
import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 10/11/2016.
 */

public class DetalleSerieAdapter extends FragmentStatePagerAdapter
        implements Listener<List<Serie>>, DetalleFeature.Likeable {
    private List<Serie> mListaDeSeries;
    private SerieController mSerieController;
    private DetalleAdapterCallback mCallback;

    public DetalleSerieAdapter(FragmentManager fm, DetalleAdapterCallback cb, Context ctx,
                               Integer genero) {
        super(fm);
        mCallback = cb;
        mSerieController = new SerieController(ctx);
        if (genero == Genre.PELICULA_ID.get(R.id.menu_series_opcion_todas)) {
            mSerieController.getSeriesPopulares(this);
        } else {
            mSerieController.getSeriesPorGenero(Genre.SERIE_ID.get(genero), this);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return DetalleSerie.getDetalleSerie(mListaDeSeries.get(position), this);
    }

    @Override
    public int getCount() {
        return mListaDeSeries != null ? mListaDeSeries.size() : 0;
    }

    @Override
    public void done(List<Serie> series) {
        mListaDeSeries = series;
        notifyDataSetChanged();
        mCallback.onFinish();
    }

    @Override
    public void onLike(Integer featureID) {
        mSerieController.agregarAFavoritos(featureID);
    }

    @Override
    public void onUnlike(Integer featureID) {
        mSerieController.quitarDeFavoritos(featureID);
    }
}