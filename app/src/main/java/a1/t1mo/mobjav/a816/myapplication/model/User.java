package a1.t1mo.mobjav.a816.myapplication.model;

import java.util.ArrayList;

import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;

/**
 * Created by Egon on 30/11/2016.
 */

public class User {

    private String username;
    private String userID;
    private ArrayList<Serie> seriesFavoritas;
    private ArrayList<Pelicula> peliculasFavoritas;

    public User(String username, String userID, ArrayList<Serie> seriesFavoritas, ArrayList<Pelicula> peliculasFavoritas) {
        this.username = username;
        this.userID = userID;
        this.seriesFavoritas = seriesFavoritas;
        this.peliculasFavoritas = peliculasFavoritas;
    }

    public String getUsername() {
        return username;
    }

    public String getUserID() {
        return userID;
    }

    public ArrayList<Serie> getSeriesFavoritas() {
        return seriesFavoritas;
    }

    public ArrayList<Pelicula> getPeliculasFavoritas() {
        return peliculasFavoritas;
    }
}
