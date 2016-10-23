package a1.t1mo.mobjav.a816.myapplication.controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

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

    public Pelicula getPelicula(Context context, String asset, Integer imgId) {
        // TODO: 23/10/2016 las imagenes las tendriamos que traer de algun servidor y cachearlas
        return mDAO.getPelicula(context, asset, imgId);
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

        List<Pelicula> listaDePeliculas = new ArrayList<>();
        for (int i=0; i < assets.length; i++) {
            listaDePeliculas.add(this.getPelicula(context, assets[i], imgIds[i]));
        }
        return listaDePeliculas;
    }
}
