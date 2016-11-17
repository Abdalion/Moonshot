package a1.t1mo.mobjav.a816.myapplication.controller;


import android.content.Context;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.data.SerieDAO;
import a1.t1mo.mobjav.a816.myapplication.model.Genre;
import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;


public class SerieController extends Controller {
    private SerieDAO mSerieDAO;
    private Context mContext;

    public SerieController(Context context) {
        mSerieDAO = SerieDAO.getDAO();
        mContext = context;
    }

    public void getSerie(Integer id, Listener<Serie> listener) {
        if (mSerieDAO.isPersisted(id)) {
            listener.done(mSerieDAO.getSerieDeRealm(id));
        } else {
            mSerieDAO.getSerieDeTmdb(id, listener);
        }
    }

    public void getSeries(int id, ListenerSeries listener) {
        if (id == R.id.menu_series_opcion_todas) {
            getSeriesPopulares(listener);
        } else {
            getSeriesPorGenero(Genre.SERIE_ID.get(id), listener);
        }
    }

    public void getSeriesPopulares(ListenerSeries listener) {
        if (hasConnectivity(mContext)) {
            mSerieDAO.getSeriesPopularesDeTmdb(listener);
        } else {
            listener.onDone(mSerieDAO.getSeriesPopularesDeRealm());
        }
    }

    public void getSeriesPorGenero(Integer id, ListenerSeries listener) {
        if (hasConnectivity(mContext)) {
            mSerieDAO.getSeriesPorGeneroDeTmdb(id, listener);
        } else {
            listener.onDone(mSerieDAO.getSeriesPorGeneroDeRealm(id));
        }
    }

    public void agregarAFavoritos(final Integer id) {
        mSerieDAO.agregarAFavoritos(id);
    }

    public void quitarDeFavoritos(final Integer id) {
        mSerieDAO.quitarDeFavoritos(id);
    }

    public void setFavorito(final int id, final boolean isFav) {
        mSerieDAO.setFavorito(id, isFav);
    }

    public List<Serie> getFavoritos() {
        return mSerieDAO.getFavoritos();
    }
}