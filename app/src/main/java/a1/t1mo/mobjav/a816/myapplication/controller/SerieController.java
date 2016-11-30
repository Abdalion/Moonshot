package a1.t1mo.mobjav.a816.myapplication.controller;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.data.SerieDAO;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.model.Genre;
import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;

public class SerieController implements Controller {
    private SerieDAO mSerieDAO;

    public SerieController() {
        mSerieDAO = SerieDAO.getDAO();
    }

    @Override
    public void getFeatures(int menuId, Listener<List<? extends Feature>> listener) {
        if (menuId == R.id.menu_series_opcion_todas) {
            getSeriesPopulares(listener);
        } else {
            getSeriesPorGenero(Genre.SERIE_ID.get(menuId), listener);
        }
    }

    public void getSeriesPopulares(Listener<List<? extends Feature>> listener) {
        mSerieDAO.getSeriesPopularesDeTmdb(listener);
    }

    public void getSeriesPorGenero(Integer id, Listener<List<? extends Feature>> listener) {
        mSerieDAO.getSeriesPorGeneroDeTmdb(id, listener);
    }

    public void setFavorito(final int id, final boolean isFav) {
        mSerieDAO.setFavorito(id, isFav);
    }

    public List<Serie> getFavoritos() {
        return mSerieDAO.getFavoritos();
    }
}