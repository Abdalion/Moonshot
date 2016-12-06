package a1.t1mo.mobjav.a816.myapplication.controller;

import android.content.Context;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.data.PeliculaDAO;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.model.Genre;
import a1.t1mo.mobjav.a816.myapplication.utils.ConnectivityCheck;
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
    private Context mContext;
    private int mPaginaActual = 1;

    public PeliculaController(Context context) {
        mPeliculaDAO = PeliculaDAO.getDAO();
        mContext = context;
    }

    @Override
    public void getFeatures(int menuId, Listener<List<? extends Feature>> listener) {
        if (ConnectivityCheck.hasConnectivity(mContext)) {
            if (menuId == R.id.menu_peliculas_opcion_todas) {
                mPeliculaDAO.getPeliculasPopularesDeTmdb(listener);
            } else {
                mPeliculaDAO.getPeliculasPorGeneroDeTmdb(Genre.PELICULA_ID.get(menuId), listener);
            }
        } else {
            if (menuId == R.id.menu_peliculas_opcion_todas) {
                listener.done(mPeliculaDAO.getPeliculasPopularesDeRealm());
            } else {
                listener.done(mPeliculaDAO.getPeliculasPorGeneroDeRealm(Genre.PELICULA_ID.get(menuId)));
            }
        }
    }

    @Override
    public void getNextPage(int menuId, Listener<List<? extends Feature>> listener) {
        mPaginaActual++;
        if (ConnectivityCheck.hasConnectivity(mContext)) {
            if (menuId == R.id.menu_peliculas_opcion_todas) {
                mPeliculaDAO.getPeliculasPopularesDeTmdb(mPaginaActual, listener);
            } else {
                mPeliculaDAO.getPeliculasPorGeneroDeTmdb(mPaginaActual, Genre.PELICULA_ID.get(menuId), listener);
            }
        } else {
            if (menuId == R.id.menu_peliculas_opcion_todas) {
                listener.done(mPeliculaDAO.getPeliculasPopularesDeRealm(mPaginaActual));
            } else {
                listener.done(mPeliculaDAO.getPeliculasPorGeneroDeRealm(mPaginaActual, Genre.PELICULA_ID.get(menuId)));
            }
        }
    }

    @Override
    public boolean isLastPage() {
        return (!ConnectivityCheck.hasConnectivity(mContext) && mPeliculaDAO.isLastPage(mPaginaActual));
    }

    @Override
    public List<? extends Feature> getFavoritos() {
        return mPeliculaDAO.getFavoritos(mContext);
    }

    @Override
    public void setFavorito(final int id, final boolean isFav) {
        mPeliculaDAO.setFavorito(id, isFav);
    }

    public void setPaginaActual(int paginaActual) {
        mPaginaActual = paginaActual;
    }
}