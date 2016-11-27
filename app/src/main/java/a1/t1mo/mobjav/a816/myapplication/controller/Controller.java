package a1.t1mo.mobjav.a816.myapplication.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;


/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 09/11/2016.
 */

public interface Controller {
    List<Feature> getFeatures(int id, Listener<List<Feature>> listener);

    public interface ListenerPeliculas {
        void onFinish(List<Pelicula> peliculas);
    }

    public interface ListenerSeries {
        void onDone(List<Serie> series);
    }
}
