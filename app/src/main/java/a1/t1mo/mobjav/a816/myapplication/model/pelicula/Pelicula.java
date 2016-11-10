package a1.t1mo.mobjav.a816.myapplication.model.pelicula;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.model.Genre;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 22/10/2016.
 */

public class Pelicula extends RealmObject implements Feature {
    @PrimaryKey
    @SerializedName("id")
    private Integer id;

    @SerializedName("adult")
    private boolean adultos;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("genres")
    private RealmList<Genre> generos;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("original_language")
    private String lenguaje;
    @SerializedName("overview")
    private String resumen;
    @SerializedName("popularity")
    private Double popularidad;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("release_date")
    private String fechaDeEstreno;
    @SerializedName("runtime")
    private Integer duracion;
    @SerializedName("tagline")
    private String tagline;
    @SerializedName("title")
    private String titulo;
    @SerializedName("vote_average")
    private Double puntajePromedio;
    @SerializedName("vote_count")
    private Integer totalVotos;

    private boolean favorito = false;

    public boolean getAdultos() {
        return adultos;
    }

    public void setAdultos(boolean adultos) {
        this.adultos = adultos;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public List<Genre> getGeneros() {
        return generos;
    }

    public void setGeneros(RealmList<Genre> genres) {
        this.generos = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguage) {
        lenguaje = lenguage;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public Double getPopularidad() {
        return popularidad;
    }

    public void setPopularidad(Double popularidad) {
        this.popularidad = popularidad;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getFechaDeEstreno() {
        return fechaDeEstreno;
    }

    public void setFechaDeEstreno(String fechaDeEstreno) {
        this.fechaDeEstreno = fechaDeEstreno;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getPuntajePromedio() {
        return puntajePromedio;
    }

    public void setPuntajePromedio(Double puntajePromedio) {
        this.puntajePromedio = puntajePromedio;
    }

    public Integer getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(Integer totalVotos) {
        this.totalVotos = totalVotos;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
}
