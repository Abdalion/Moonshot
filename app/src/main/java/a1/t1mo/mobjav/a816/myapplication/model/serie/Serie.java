package a1.t1mo.mobjav.a816.myapplication.model.serie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.model.Genre;
import a1.t1mo.mobjav.a816.myapplication.model.RealmString;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 29/10/2016.
 */


public class Serie extends RealmObject implements Feature {

    @PrimaryKey
    @SerializedName("id")
    public Integer id;
    @SerializedName("backdrop_path")
    public String backdropPath;
    @SerializedName("first_air_date")
    public String fechaDeEstreno;
    @SerializedName("genres")
    public RealmList<Genre> generos;
    @SerializedName("homepage")
    public String homepage;
    @SerializedName("languages")
    public RealmList<RealmString> lenguajes;
    @SerializedName("last_air_date")
    public String fechaDeUltimoCapitulo;
    @SerializedName("name")
    public String nombre;
    @SerializedName("number_of_episodes")
    public Integer numeroDeEpisodios;
    @SerializedName("number_of_seasons")
    public Integer numeroDeTemporadas;
    @SerializedName("origin_country")
    public RealmList<RealmString> paisesDeOrigen;
    @SerializedName("original_language")
    public String lenguaje;
    @SerializedName("overview")
    public String resumen;
    @SerializedName("popularity")
    public Double popularidad;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("seasons")
    public RealmList<Season> temporadas;
    @SerializedName("status")
    public String status;
    @SerializedName("vote_average")
    public Double puntajePromedio;
    @SerializedName("vote_count")
    public Integer totalDeVotos;

    public boolean favorito = false;

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getFechaDeEstreno() {
        return fechaDeEstreno;
    }

    public void setFechaDeEstreno(String fechaDeEstreno) {
        this.fechaDeEstreno = fechaDeEstreno;
    }

    public List<Genre> getGeneros() {
        return generos;
    }

    public void setGeneros(RealmList<Genre> generos) {
        this.generos = generos;
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

    public List<RealmString> getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(RealmList<RealmString> lenguajes) {
        this.lenguajes = lenguajes;
    }

    public String getFechaDeUltimoCapitulo() {
        return fechaDeUltimoCapitulo;
    }

    public void setFechaDeUltimoCapitulo(String fechaDeUltimoCapitulo) {
        this.fechaDeUltimoCapitulo = fechaDeUltimoCapitulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumeroDeEpisodios() {
        return numeroDeEpisodios;
    }

    public void setNumeroDeEpisodios(Integer numeroDeEpisodios) {
        this.numeroDeEpisodios = numeroDeEpisodios;
    }

    public Integer getNumeroDeTemporadas() {
        return numeroDeTemporadas;
    }

    public void setNumeroDeTemporadas(Integer numeroDeTemporadas) {
        this.numeroDeTemporadas = numeroDeTemporadas;
    }

    public List<RealmString> getPaisesDeOrigen() {
        return paisesDeOrigen;
    }

    public void setPaisesDeOrigen(RealmList<RealmString> paisesDeOrigen) {
        this.paisesDeOrigen = paisesDeOrigen;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
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

    public List<Season> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(RealmList<Season> temporadas) {
        this.temporadas = temporadas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPuntajePromedio() {
        return puntajePromedio;
    }

    public void setPuntajePromedio(Double puntajePromedio) {
        this.puntajePromedio = puntajePromedio;
    }

    public Integer getTotalDeVotos() {
        return totalDeVotos;
    }

    public void setTotalDeVotos(Integer totalDeVotos) {
        this.totalDeVotos = totalDeVotos;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
}
