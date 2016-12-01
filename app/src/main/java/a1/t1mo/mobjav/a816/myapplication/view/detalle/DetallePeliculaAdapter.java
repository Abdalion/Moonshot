package a1.t1mo.mobjav.a816.myapplication.view.detalle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 09/11/2016.
 */

public class DetallePeliculaAdapter extends DetalleAdapter {
    private List<? extends Feature> mListaDePeliculas;

    public DetallePeliculaAdapter(FragmentManager fm, List<? extends Feature> peliculas) {
        super(fm);
        mListaDePeliculas = peliculas;
    }

    public DetallePeliculaAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFeatures(List<? extends Feature> features) {
        mListaDePeliculas = features;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return DetallePelicula.getDetalle((Pelicula) mListaDePeliculas.get(position));
    }

    @Override
    public int getCount() {
        return mListaDePeliculas != null ? mListaDePeliculas.size() : 0;
    }
}
