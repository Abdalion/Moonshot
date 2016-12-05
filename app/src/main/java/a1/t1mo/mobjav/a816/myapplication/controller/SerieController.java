package a1.t1mo.mobjav.a816.myapplication.controller;

import android.content.Context;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.data.SerieDAO;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.model.Genre;
import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;

public class SerieController implements Controller {
    private SerieDAO mSerieDAO;
    private Context mContext;

    public SerieController(Context context) {
        mSerieDAO = SerieDAO.getDAO();
        mContext = context;
    }

    @Override
    public void getFeatures(int menuId, Listener<List<? extends Feature>> listener) {
        if (menuId == R.id.menu_series_opcion_todas) {
            getSeriesPopulares(listener);
        } else {
            getSeriesPorGenero(Genre.SERIE_ID.get(menuId), listener);
        }
    }

    @Override
    public void getSiguientePagina(int menuId, Listener<List<? extends Feature>> listener) {

    }

    //todo: ?
    @Override
    public List<? extends Feature> getFavoritos() {
        return null;
    }

    public void getSeriesPopulares(Listener<List<? extends Feature>> listener) {
        mSerieDAO.getSeriesPopularesDeTmdb(listener);
    }

    public void getSeriesPorGenero(String id, Listener<List<? extends Feature>> listener) {
        mSerieDAO.getSeriesPorGeneroDeTmdb(id, listener);
    }

    @Override
    public void setFavorito(final int id, final boolean isFav) {
        mSerieDAO.setFavorito(id, isFav);
    }

    public List<Serie> getFavoritos(Context context) {
        return mSerieDAO.getFavoritos(context);
    }
}