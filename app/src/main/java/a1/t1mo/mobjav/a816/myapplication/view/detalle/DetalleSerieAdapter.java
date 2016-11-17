package a1.t1mo.mobjav.a816.myapplication.view.detalle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 09/11/2016.
 */

public class DetalleSerieAdapter extends FragmentStatePagerAdapter {
    private List<Serie> mListaDeSeries;

    public DetalleSerieAdapter(FragmentManager fm, List<Serie> peliculas) {
        super(fm);
        mListaDeSeries = peliculas;
    }

    @Override
    public Fragment getItem(int position) {
        return DetalleSerie.getDetalle(mListaDeSeries.get(position));
    }

    @Override
    public int getCount() {
        return mListaDeSeries != null ? mListaDeSeries.size() : 0;
    }
}