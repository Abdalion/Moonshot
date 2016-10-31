package a1.t1mo.mobjav.a816.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 22/10/2016.
 */

public class Pelicula {

    @SerializedName("adult")
    private Boolean mAdultos;
    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("genres")
    private List<Genre> genres = new ArrayList<Genre>();
    @SerializedName("homepage")
    private String mHomepage;
    @SerializedName("id")
    private Integer mId;
    @SerializedName("imdb_id")
    private String mImdbId;
    @SerializedName("original_language")
    private String mLenguage;
    @SerializedName("overview")
    private String mResumen;
    @SerializedName("popularity")
    private Double mPopularidad;
    @SerializedName("poster_path")
    private String mPosterPath;
    @SerializedName("release_date")
    private String mFechaDeEstreno;
    @SerializedName("runtime")
    private Integer mDuracion;
    @SerializedName("tagline")
    private String mTagline;
    @SerializedName("title")
    private String mTitulo;
    @SerializedName("vote_average")
    private Double mPuntajePromedio;
    @SerializedName("vote_count")
    private Integer mTotalVotos;

    public Boolean getAdultos() {
        return mAdultos;
    }

    public void setAdultos(Boolean adultos) {
        mAdultos = adultos;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return mHomepage;
    }

    public void setHomepage(String homepage) {
        mHomepage = homepage;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getImdbId() {
        return mImdbId;
    }

    public void setImdbId(String imdbId) {
        mImdbId = imdbId;
    }

    public String getLenguage() {
        return mLenguage;
    }

    public void setLenguage(String lenguage) {
        mLenguage = lenguage;
    }

    public String getResumen() {
        return mResumen;
    }

    public void setResumen(String resumen) {
        mResumen = resumen;
    }

    public Double getPopularidad() {
        return mPopularidad;
    }

    public void setPopularidad(Double popularidad) {
        mPopularidad = popularidad;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getFechaDeEstreno() {
        return mFechaDeEstreno;
    }

    public void setFechaDeEstreno(String fechaDeEstreno) {
        mFechaDeEstreno = fechaDeEstreno;
    }

    public Integer getDuracion() {
        return mDuracion;
    }

    public void setDuracion(Integer duracion) {
        mDuracion = duracion;
    }

    public String getTagline() {
        return mTagline;
    }

    public void setTagline(String tagline) {
        mTagline = tagline;
    }

    public String getTitulo() {
        return mTitulo;
    }

    public void setTitulo(String titulo) {
        mTitulo = titulo;
    }

    public Double getPuntajePromedio() {
        return mPuntajePromedio;
    }

    public void setPuntajePromedio(Double puntajePromedio) {
        mPuntajePromedio = puntajePromedio;
    }

    public Integer getTotalVotos() {
        return mTotalVotos;
    }

    public void setTotalVotos(Integer totalVotos) {
        mTotalVotos = totalVotos;
    }
}
