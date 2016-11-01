package a1.t1mo.mobjav.a816.myapplication.controller;


import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.model.Serie;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;


public class SerieController {
    private SerieDAO mSerieDAO;

    public SerieController() {
        mSerieDAO = new SerieDAO();
    }

    public void getSerie(Integer id, Listener<Serie> listener) {
        mSerieDAO.getSerie(id, listener);
    }

    public void getSeriesPopulares(Listener<List<Serie>> listener) {
        mSerieDAO.getSeriesPopulares(listener);
    }

    public void getSeriesPorGenero(Integer id, Listener<List<Serie>> listener) {
        mSerieDAO.getSeriesPorGenero(id, listener);
    }
}