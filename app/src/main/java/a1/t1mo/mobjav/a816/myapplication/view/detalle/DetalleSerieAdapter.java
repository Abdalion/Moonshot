package a1.t1mo.mobjav.a816.myapplication.view.detalle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 09/11/2016.
 */

public class DetalleSerieAdapter extends DetalleAdapter {
    private List<? extends Feature> mListaDeSeries;

    public DetalleSerieAdapter(FragmentManager fm, List<? extends Feature> series) {
        super(fm);
        mListaDeSeries = series;
    }

    public DetalleSerieAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFeatures(List<? extends Feature> features) {
        mListaDeSeries = features;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return DetalleSerie.getDetalle((Serie) mListaDeSeries.get(position));
    }

    @Override
    public int getCount() {
        return mListaDeSeries != null ? mListaDeSeries.size() : 0;
    }
}