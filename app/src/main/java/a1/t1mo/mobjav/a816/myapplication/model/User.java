package a1.t1mo.mobjav.a816.myapplication.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;

/**
 * Created by Egon on 30/11/2016.
 */

public class User {

    private String username;
    private String userID;
    private HashMap<String, Integer> seriesFavoritas;
    private HashMap<String, Integer> peliculasFavoritas;

    public User() {
        seriesFavoritas = new HashMap<>();
        peliculasFavoritas = new HashMap<>();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setSeriesFavoritas(HashMap<String, Integer> seriesFavoritas) {
        this.seriesFavoritas = seriesFavoritas;
    }

    public void setPeliculasFavoritas(HashMap<String, Integer> peliculasFavoritas) {
        this.peliculasFavoritas = peliculasFavoritas;
    }

    public String getUsername() {
        return username;
    }

    public String getUserID() {
        return userID;
    }

    public HashMap<String, Integer> getSeriesFavoritas() {
        return seriesFavoritas;
    }

    public HashMap<String, Integer> getPeliculasFavoritas() {
        return peliculasFavoritas;
    }
}
