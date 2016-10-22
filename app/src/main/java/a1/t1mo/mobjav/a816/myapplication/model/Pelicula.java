package a1.t1mo.mobjav.a816.myapplication.model;

import java.util.Date;
import java.util.List;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 22/10/2016.
 */

public class Pelicula {
    private Integer mImdbID;
    private String mTitulo;
    private String mDirector;
    private List<String> mActores;
    private Date mFechaDeEstreno;
    private String mTrama;
    private Float mImdbRating;
    private String mLenguaje;
    private Integer mImagenId;

    public Integer getImdbID() {
        return mImdbID;
    }

    public void setImdbID(Integer imdbID) {
        mImdbID = imdbID;
    }

    public String getTitulo() {
        return mTitulo;
    }

    public void setTitulo(String titulo) {
        mTitulo = titulo;
    }

    public String getDirector() {
        return mDirector;
    }

    public void setDirector(String director) {
        mDirector = director;
    }

    public List<String> getActores() {
        return mActores;
    }

    public void setActores(List<String> actores) {
        mActores = actores;
    }

    public Date getFechaDeEstreno() {
        return mFechaDeEstreno;
    }

    public void setFechaDeEstreno(Date fechaDeEstreno) {
        mFechaDeEstreno = fechaDeEstreno;
    }

    public String getTrama() {
        return mTrama;
    }

    public void setTrama(String trama) {
        mTrama = trama;
    }

    public Float getImdbRating() {
        return mImdbRating;
    }

    public void setImdbRating(Float imdbRating) {
        mImdbRating = imdbRating;
    }

    public String getLenguaje() {
        return mLenguaje;
    }

    public void setLenguaje(String lenguaje) {
        mLenguaje = lenguaje;
    }

    public Integer getImagenId() {
        return mImagenId;
    }

    public void setImagenId(Integer imagenId) {
        mImagenId = imagenId;
    }
}
