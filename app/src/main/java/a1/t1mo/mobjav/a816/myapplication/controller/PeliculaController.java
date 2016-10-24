package a1.t1mo.mobjav.a816.myapplication.controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import a1.t1mo.mobjav.a816.myapplication.R;
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
    private PeliculaDAO mDAO;

    public PeliculaController() {
        mDAO = new PeliculaDAO();
    }

    public Pelicula getPelicula(Context context, String asset, Integer imgId, String generos) {
        // TODO: 23/10/2016 las imagenes las tendriamos que traer de algun servidor y cachearlas
        return mDAO.getPelicula(context, asset, imgId, generos);
    }

    public List<Pelicula> getPeliculas(Context context) {
        // Por ahora, hardcodeamos la lista de peliculas
        String[] assets = {"deepwater_horizon", "hell_on_high_water", "inferno", "snowden", "sully",
                "the_accountant", "the_girl_on_the_train", "the_light_between_oceans",
                "the_magnificent_seven", "war_dogs"};

        Integer[] imgIds = {R.drawable.deepwater_horizon, R.drawable.hell_on_high_water,
                R.drawable.inferno, R.drawable.snowden, R.drawable.sully, R.drawable.the_accountant,
                R.drawable.the_girl_on_the_train, R.drawable.the_light_between_oceans,
                R.drawable.the_magnificent_seven, R.drawable.war_dogs};

        String[] generos = {"Action, Drama, Thriller", "Crime, Drama", "Action, Adventure, Crime",
                "Biography, Drama, Thriller", "Biography, Drama", "Action, Crime, Drama",
                "Mystery, Thriller", "Drama, Romance", "Action, Adventure, Western",
                "Comedy, Crime, Drama"};

        List<Pelicula> listaDePeliculas = new ArrayList<>();
        for (int i=0; i < assets.length; i++) {
            listaDePeliculas.add(this.getPelicula(context, assets[i], imgIds[i], generos[i]));
        }
        return listaDePeliculas;
    }

    public List<Pelicula> getGenero(Context context, String genero) {
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        switch (genero) {
            case "Accion":
                peliculas.add(getPelicula(context, "deepwater_horizon", R.drawable.deepwater_horizon,
                        "Action, Drama, Thriller"));
                peliculas.add(getPelicula(context, "inferno", R.drawable.inferno,
                        "Action, Adventure, Crime"));
                peliculas.add(getPelicula(context, "the_accountant", R.drawable.the_accountant,
                        "Action, Crime, Drama"));
                peliculas.add(getPelicula(context, "the_magnificent_seven",
                        R.drawable.the_magnificent_seven, "Action, Adventure, Western"));
                break;
            case "Drama":
                peliculas.add(getPelicula(context, "deepwater_horizon", R.drawable.deepwater_horizon,
                        "Action, Drama, Thriller"));
                peliculas.add(getPelicula(context, "hell_on_high_water",
                        R.drawable.hell_on_high_water, "Crime, Drama"));
                peliculas.add(getPelicula(context, "snowden", R.drawable.snowden,
                        "Biography, Drama, Thriller"));
                peliculas.add(getPelicula(context, "sully", R.drawable.sully,
                        "Biography, Drama"));
                peliculas.add(getPelicula(context, "the_accountant", R.drawable.the_accountant,
                        "Action, Crime, Drama"));
                peliculas.add(getPelicula(context, "the_light_between_oceans",
                        R.drawable.the_light_between_oceans, "Drama, Romance"));
                peliculas.add(getPelicula(context, "war_dogs",
                        R.drawable.war_dogs, "Comedy, Crime, Drama"));
                break;
            case "Thriller":
                peliculas.add(getPelicula(context, "deepwater_horizon", R.drawable.deepwater_horizon,
                        "Action, Drama, Thriller"));
                peliculas.add(getPelicula(context, "snowden", R.drawable.snowden,
                        "Biography, Drama, Thriller"));
                peliculas.add(getPelicula(context, "the_girl_on_the_train",
                        R.drawable.the_girl_on_the_train, "Mystery, Thriller"));
                break;
        }
        return peliculas;
    }
}
