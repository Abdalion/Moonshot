package a1.t1mo.mobjav.a816.myapplication.controller;

import android.content.Context;

import a1.t1mo.mobjav.a816.myapplication.model.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.model.PeliculaDAO;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 22/10/2016.
 */

public class PeliculaController {
    public Pelicula getPelicula(Context context, Integer assetId) {
        return PeliculaDAO.getPelicula(context, assetId);
    }
}
