package a1.t1mo.mobjav.a816.myapplication.view.detalle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 09/11/2016.
 */

public class DetallePeliculaAdapter extends FragmentStatePagerAdapter {
    private List<Pelicula> mListaDePeliculas;

    public DetallePeliculaAdapter(FragmentManager fm, List<Pelicula> peliculas) {
        super(fm);
        mListaDePeliculas = peliculas;
    }

    @Override
    public Fragment getItem(int position) {
        return DetallePelicula.getDetalle(mListaDePeliculas.get(position));
    }

    @Override
    public int getCount() {
        return mListaDePeliculas != null ? mListaDePeliculas.size() : 0;
    }
}
