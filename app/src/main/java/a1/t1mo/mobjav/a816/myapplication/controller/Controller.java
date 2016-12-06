package a1.t1mo.mobjav.a816.myapplication.controller;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;


/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 09/11/2016.
 */

public interface Controller {
    void getFeatures(int menuId, Listener<List<? extends Feature>> listener);

    void getNextPage(int menuId, Listener<List<? extends Feature>> listener);

    void setPaginaActual(int page);

    boolean isLastPage();

    void getFavoritos(Listener<List<? extends Feature>> listener);

    void setFavorito(final int id, final boolean isFav);
}
