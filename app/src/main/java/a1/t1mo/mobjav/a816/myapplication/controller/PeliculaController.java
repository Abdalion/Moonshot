package a1.t1mo.mobjav.a816.myapplication.controller;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.data.PeliculaDAO;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 22/10/2016.
 */

public class PeliculaController {
    private PeliculaDAO mPeliculaDAO;

    public PeliculaController() {
        mPeliculaDAO = PeliculaDAO.getDAO();
    }

    public void getPelicula(Integer id, Listener<Pelicula> listener) {
        if (mPeliculaDAO.isPersisted(id)) {
            listener.done(mPeliculaDAO.getPeliculaDeRealm(id));
        } else {
            mPeliculaDAO.getPeliculaDeTmdb(id, listener);
        }
    }

    public void getPeliculasPopulares(Listener<List<Pelicula>> listener) {
        mPeliculaDAO.getPeliculasPopularesDeTmdb(listener);
    }

    public void getPeliculasPorGenero(Integer id, Listener<List<Pelicula>> listener) {
        mPeliculaDAO.getPeliculasPorGenero(id, listener);
    }

    public void agregarAFavoritos(Integer id) {

    }

    public void quitarDeFavoritos(Integer id) {

    }

    public List<Pelicula> getFavoritos() {
        return null;
    }
}