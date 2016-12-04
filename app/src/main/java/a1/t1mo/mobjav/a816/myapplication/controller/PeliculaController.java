package a1.t1mo.mobjav.a816.myapplication.controller;

import android.content.Context;
import android.util.Log;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.data.PeliculaDAO;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.model.Genre;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 22/10/2016.
 */

public class PeliculaController implements Controller {
    private PeliculaDAO mPeliculaDAO;

    public PeliculaController() {
        mPeliculaDAO = PeliculaDAO.getDAO();
    }

    @Override
    public void getFeatures(int menuId, Listener<List<? extends Feature>> listener) {
        if (menuId == R.id.menu_peliculas_opcion_todas) {
            Log.d(getClass().getSimpleName(), "Traer peliculas populares");
            getPeliculasPopulares(listener);
        } else {
            Log.d(getClass().getSimpleName(), "Traer peliculas de genero");
            getPeliculasPorGenero(Genre.PELICULA_ID.get(menuId), listener);
        }
    }

    //todo: ?
    @Override
    public List<? extends Feature> getFavoritos() {
        return null;
    }

    public void getPeliculasPopulares(Listener<List<? extends Feature>> listener) {
        mPeliculaDAO.getPeliculasPopularesDeTmdb(listener);
    }

    public void getPeliculasPorGenero(Integer id, Listener<List<? extends Feature>> listener) {
        mPeliculaDAO.getPeliculasPorGeneroDeTmdb(id, listener);
    }

    @Override
    public void setFavorito(final int id, final boolean isFav) {
        mPeliculaDAO.setFavorito(id, isFav);
    }

    public List<Pelicula> getFavoritos(Context context) {
        return mPeliculaDAO.getFavoritos(context);
    }
}