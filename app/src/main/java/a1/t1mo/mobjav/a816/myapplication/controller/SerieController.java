package a1.t1mo.mobjav.a816.myapplication.controller;

import android.content.Context;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.data.SerieDAO;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.model.Genre;
import a1.t1mo.mobjav.a816.myapplication.utils.ConnectivityCheck;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;

public class SerieController implements Controller {
    private SerieDAO mSerieDAO;
    private Context mContext;
    private int mPaginaActual = 1;

    public SerieController(Context context) {
        mSerieDAO = SerieDAO.getDAO();
        mContext = context;
    }

    @Override
    public void getFeatures(int menuId, Listener<List<? extends Feature>> listener) {
        if (ConnectivityCheck.hasConnectivity(mContext)) {
            if (menuId == R.id.menu_series_opcion_todas) {
                mSerieDAO.getSeriesPopularesDeTmdb(listener);
            } else {
                mSerieDAO.getSeriesPorGeneroDeTmdb(Genre.SERIE_ID.get(menuId), listener);
            }
        } else {
            if (menuId == R.id.menu_series_opcion_todas) {
                listener.done(mSerieDAO.getSeriesPopularesDeRealm());
            } else {
                listener.done(mSerieDAO.getSeriesPorGeneroDeRealm(Genre.SERIE_ID.get(menuId)));
            }
        }
    }

    @Override
    public void getNextPage(int menuId, Listener<List<? extends Feature>> listener) {
        mPaginaActual++;
        if (ConnectivityCheck.hasConnectivity(mContext)) {
            if (menuId == R.id.menu_series_opcion_todas) {
                mSerieDAO.getSeriesPopularesDeTmdb(mPaginaActual, listener);
            } else {
                mSerieDAO.getSeriesPorGeneroDeTmdb(mPaginaActual, Genre.SERIE_ID.get(menuId), listener);
            }
        } else {
            if (menuId == R.id.menu_series_opcion_todas) {
                listener.done(mSerieDAO.getSeriesPopularesDeRealm(mPaginaActual));
            } else {
                listener.done(mSerieDAO.getSeriesPorGeneroDeRealm(mPaginaActual, Genre.SERIE_ID.get(menuId)));
            }
        }
    }

    @Override
    public boolean isLastPage() {
        return (!ConnectivityCheck.hasConnectivity(mContext) && mSerieDAO.isLastPage(mPaginaActual));
    }

    @Override
    public void getFavoritos(Listener<List<? extends Feature>> listener) {
        mSerieDAO.getFavoritos(mContext, listener);
    }

    @Override
    public void setFavorito(final int id, final boolean isFav) {
        mSerieDAO.setFavorito(id, isFav);
    }

    public void setPaginaActual(int paginaActual) {
        mPaginaActual = paginaActual;
    }
}