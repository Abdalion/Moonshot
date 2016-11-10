package a1.t1mo.mobjav.a816.myapplication.view.detalle;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a1.t1mo.mobjav.a816.myapplication.model.Genre;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 09/11/2016.
 */

public class DetallePeliculaAdapter extends FragmentStatePagerAdapter
        implements Listener<List<Pelicula>>, DetalleFeature.Likeable {
    private List<Pelicula> mListaDePeliculas;
    private PeliculaController mPeliculaController;

    public DetallePeliculaAdapter(FragmentManager fm, Context ctx, Integer genero) {
        super(fm);
        mPeliculaController = new PeliculaController(ctx);
        if (genero == Genre.PELICULA_ID.get(R.id.menu_peliculas_opcion_todas)) {
            mPeliculaController.getPeliculasPopulares(this);
        } else {
            mPeliculaController.getPeliculasPorGenero(Genre.PELICULA_ID.get(genero), this);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return DetallePelicula.getDetallePelicula(mListaDePeliculas.get(position), this);
    }

    @Override
    public int getCount() {
        return mListaDePeliculas != null ? mListaDePeliculas.size() : 0;
    }

    @Override
    public void done(List<Pelicula> peliculas) {
        mListaDePeliculas = peliculas;
        notifyDataSetChanged();
    }

    @Override
    public void onLike(Integer featureID) {
        mPeliculaController.agregarAFavoritos(featureID);
    }

    @Override
    public void onUnlike(Integer featureID) {
        mPeliculaController.quitarDeFavoritos(featureID);
    }
}
