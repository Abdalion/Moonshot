package a1.t1mo.mobjav.a816.myapplication.controller;

import android.content.Context;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.data.PeliculaDAO;
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

public class PeliculaController extends Controller {
    private PeliculaDAO mPeliculaDAO;
    private Context mContext;

    public PeliculaController(Context context) {
        mPeliculaDAO = PeliculaDAO.getDAO();
        mContext = context;
    }

    public void getPelicula(Integer id, Listener<Pelicula> listener) {
        if (mPeliculaDAO.isPersisted(id)) {
            listener.done(mPeliculaDAO.getPeliculaDeRealm(id));
        } else {
            mPeliculaDAO.getPeliculaDeTmdb(id, listener);
        }
    }

    public void getPeliculas(int id, ListenerPeliculas listener) {
        if (id == Genre.PELICULA_ID.get(R.id.menu_peliculas_opcion_todas)) {
            getPeliculasPopulares(listener);
        } else {
            getPeliculasPorGenero(Genre.PELICULA_ID.get(id), listener);
        }
    }

    public void getPeliculasPopulares(ListenerPeliculas listener) {
        if (hasConnectivity(mContext)) {
            mPeliculaDAO.getPeliculasPopularesDeTmdb(listener);
        } else {
            listener.done(mPeliculaDAO.getPeliculasPopularesDeRealm());
        }
    }

    public void getPeliculasPorGenero(Integer id, ListenerPeliculas listener) {
        if (hasConnectivity(mContext)) {
            mPeliculaDAO.getPeliculasPorGeneroDeTmdb(id, listener);
        } else {
            listener.done(mPeliculaDAO.getPeliculasPorGeneroDeRealm(id));
        }
    }

    public void agregarAFavoritos(final Integer id) {
        mPeliculaDAO.agregarAFavoritos(id);
    }

    public void quitarDeFavoritos(final Integer id) {
        mPeliculaDAO.quitarDeFavoritos(id);
    }

    public List<Pelicula> getFavoritos() {
        return mPeliculaDAO.getFavoritos();
    }
}