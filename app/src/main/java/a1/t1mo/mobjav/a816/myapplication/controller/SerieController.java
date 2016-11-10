package a1.t1mo.mobjav.a816.myapplication.controller;


import android.content.Context;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.data.SerieDAO;
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

    public void getSeriesPopulares(Listener<List<Serie>> listener) {
        if (hasConnectivity(mContext)) {
            mSerieDAO.getSeriesPopularesDeTmdb(listener);
        } else {
            listener.done(mSerieDAO.getSeriesPopularesDeRealm());
        }
    }

    public void getSeriesPorGenero(Integer id, Listener<List<Serie>> listener) {
        if (hasConnectivity(mContext)) {
            mSerieDAO.getSeriesPorGeneroDeTmdb(id, listener);
        } else {
            listener.done(mSerieDAO.getSeriesPorGeneroDeRealm(id));
        }
    }

    public void agregarAFavoritos(final Integer id) {
        mSerieDAO.agregarAFavoritos(id);
    }

    public void quitarDeFavoritos(final Integer id) {
        mSerieDAO.quitarDeFavoritos(id);
    }

    public List<Serie> getFavoritos() {
        return mSerieDAO.getFavoritos();
    }
}