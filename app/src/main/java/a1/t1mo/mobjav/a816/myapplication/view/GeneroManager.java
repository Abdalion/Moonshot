package a1.t1mo.mobjav.a816.myapplication.view;

import android.support.design.widget.NavigationView;

import a1.t1mo.mobjav.a816.myapplication.R;

/**
 * Created by Egon on 02/11/2016.
 */

public class GeneroManager {
    public static String PELICULA_O_SERIE;

    private static GeneroManager instancia = null;

    private GeneroManager() {}

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

    public static void onNavigationGenreClick(Integer item) {

    }
}
