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
    private static ArrayList<Integer> generos_peliculas_id;
    private static ArrayList<Integer> generos_series_id;

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

    public static String getPeliculaOSerie () {
        return PELICULA_O_SERIE;
    }

    public void setPeliculaOSerie(String peliculaOSerie) {
        PELICULA_O_SERIE = peliculaOSerie;
    }


    public static void updateNavigationMenu(NavigationView navigationView) {
        navigationView.getMenu().clear();
        if(getPeliculaOSerie() == "PELICULA") {
            navigationView.inflateMenu(R.menu.menu_navigation_peliculas);
        }
        else {
            navigationView.inflateMenu(R.menu.menu_navigation_series);
        }
    }

    public static void onNavigationGenreClick(Integer itemID, Context context, AdapterViewPagerFragment adapter) {
        //Esto se puede hacer con un foreach mapeando los generos a los de xml
        if(PELICULA_O_SERIE == "PELICULA") {
            switch (itemID) {
                case R.id.menu_peliculas_opcion_todas: adapter.changeCategory(GeneroPelicula.TODAS.id, null);
                    break;
                case R.id.menu_peliculas_opcion_accion: adapter.changeCategory(GeneroPelicula.ACTION.id, null);
                    break;
                //TODO: COMPLETAR CON LA ESTRUCTURA DE ARRIBA CON LOS DEMAS GENEROS PELICULA.
            }
        }
        else if(PELICULA_O_SERIE == "SERIE") {
            switch (itemID) {
                case R.id.menu_peliculas_opcion_todas: adapter.changeCategory(null,GeneroSerie.TODAS.id);
                    break;
                case R.id.menu_peliculas_opcion_accion: adapter.changeCategory(null, GeneroSerie.ACTION_ADVENTURE.id);
                    break;
                //TODO: COMPLETAR CON LA ESTRUCTURA DE ARRIBA CON LOS DEMAS GENEROS SERIE.
            }
        }
    }
}
