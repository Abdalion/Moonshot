package a1.t1mo.mobjav.a816.myapplication.view;

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

    public String getPeliculaOSerie () {
        return PELICULA_O_SERIE;
    }

    public void setPeliculaOSerie(String peliculaOSerie) {
        PELICULA_O_SERIE = peliculaOSerie;
    }





}
