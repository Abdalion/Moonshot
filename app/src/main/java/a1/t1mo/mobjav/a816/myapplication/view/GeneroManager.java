package a1.t1mo.mobjav.a816.myapplication.view;

import android.content.Context;
import android.support.annotation.IntegerRes;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.model.GeneroPelicula;
import a1.t1mo.mobjav.a816.myapplication.model.GeneroSerie;

/**
 * Created by Egon on 02/11/2016.
 */

public class GeneroManager {
    public static String PELICULA_O_SERIE;
    private static GeneroManager instancia = null;

    private GeneroManager() {
        cargarListasDeGeneros();
    }

    private void cargarListasDeGeneros() {
        //todo: este metodo. LAS ID A CARGAR SON TIPO R.id.menu_opcion_todas
    }


    public static GeneroManager getGeneroManager() {
        if(instancia == null) {
            instancia = new GeneroManager();
            PELICULA_O_SERIE = "PELICULA";
        }
        return instancia;
    }

    public String getPeliculaOSerie () {
        return PELICULA_O_SERIE;
    }

    public void setPeliculaOSerie(String peliculaOSerie) {
        PELICULA_O_SERIE = peliculaOSerie;
    }


    public void updateNavigationMenu(NavigationView navigationView) {
        navigationView.getMenu().clear();
        if(getPeliculaOSerie() == "PELICULA") {
            navigationView.inflateMenu(R.menu.menu_navigation_peliculas);
        }
        else {
            navigationView.inflateMenu(R.menu.menu_navigation_series);
        }
    }

    public void onNavigationGenreClick(Integer itemID, AdapterViewPagerFragment adapter) {
        //Esto se puede hacer con un foreach mapeando los generos a los de xml

        Integer generoPelicula = 0;
        Integer generoSerie = 0;
        switch (itemID) {
            case R.id.menu_peliculas_opcion_todas:
                generoPelicula = GeneroPelicula.TODAS.id;
                break;
            case R.id.menu_peliculas_opcion_accion:
                generoPelicula = GeneroPelicula.ACTION.id;
                break;
            case R.id.menu_peliculas_opcion_drama:
                generoPelicula = GeneroPelicula.DRAMA.id;
                break;
            case R.id.menu_peliculas_opcion_thriller:
                generoPelicula = GeneroPelicula.THRILLER.id;
                break;
            case R.id.menu_series_opcion_todas:
                generoSerie = GeneroSerie.TODAS.id;
                break;
            case R.id.menu_series_opcion_comedia:
                generoSerie = GeneroSerie.COMEDY.id;
                break;
            case R.id.menu_series_opcion_crimen:
                generoSerie = GeneroSerie.CRIME.id;
                break;
            case R.id.menu_series_opcion_drama:
                generoSerie = GeneroSerie.DRAMA.id;
                break;
        }
        adapter.changeCategory(generoPelicula, generoSerie);
    }
}
