package a1.t1mo.mobjav.a816.myapplication.model;

import java.util.Date;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 22/10/2016.
 */

public class Pelicula {
    private String mImdbId;
    private String mTitulo;
    private String mDirector;
    private String mActores;
    private Date mFechaDeEstreno;
    private String mTrama;
    private Float mImdbRating;
    private String mLenguaje;
    private String mGeneros;
    private Integer mImagenId;

    public String getImdbId() {
        return mImdbId;
    }

    public void setImdbId(String imdbId) {
        mImdbId = imdbId;
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

    public String getActores() {
        return mActores;
    }

    public void setActores(String actores) {
        mActores = actores;
    }

    public String getTrama() {
        return mTrama;
    }

    public Date getFechaDeEstreno() {
        return mFechaDeEstreno;
    }

    public void setFechaDeEstreno(Date fechaDeEstreno) {
        mFechaDeEstreno = fechaDeEstreno;
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

    public String getGeneros() {
        return mGeneros;
    }

    public void setGeneros(String generos) {
        mGeneros = generos;
    }

    public Integer getImagenId() {
        return mImagenId;
    }

    public void setImagenId(Integer imagenId) {
        mImagenId = imagenId;
    }
}
