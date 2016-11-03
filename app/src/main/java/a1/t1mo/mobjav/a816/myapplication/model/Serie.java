package a1.t1mo.mobjav.a816.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 29/10/2016.
 */


public class Serie {

    @SerializedName("backdrop_path")
    public String mBackDropPath;
    @SerializedName("first_air_date")
    public String mFechaDeEstreno;
    @SerializedName("genres")
    public List<Genre> mGeneros = new ArrayList<Genre>();
    @SerializedName("homepage")
    public String mHomepage;
    @SerializedName("id")
    public Integer mId;
    @SerializedName("languages")
    public List<String> mLenguajes = new ArrayList<String>();
    @SerializedName("last_air_date")
    public String mFechaDeUltimoCapitulo;
    @SerializedName("name")
    public String mNombre;
    @SerializedName("number_of_episodes")
    public Integer mNumeroDeEpisodios;
    @SerializedName("number_of_seasons")
    public Integer getmNumeroDeTemporadas;
    @SerializedName("origin_country")
    public List<String> mPaisesDeOrigen = new ArrayList<String>();
    @SerializedName("original_language")
    public String mLenguaje;
    @SerializedName("overview")
    public String mResumen;
    @SerializedName("popularity")
    public Double mPopularidad;
    @SerializedName("poster_path")
    public String mPosterPath;
    @SerializedName("seasons")
    public List<Season> mTemporadas = new ArrayList<Season>();
    @SerializedName("status")
    public String mStatus;
    @SerializedName("vote_average")
    public Double mPuntajePromedio;
    @SerializedName("vote_count")
    public Integer mTotalVotos;

    public String getBackDropPath() {
        return mBackDropPath;
    }

    public void setBackDropPath(String backDropPath) {
        mBackDropPath = backDropPath;
    }

    public String getFechaDeEstreno() {
        return mFechaDeEstreno;
    }

    public void setFechaDeEstreno(String fechaDeEstreno) {
        mFechaDeEstreno = fechaDeEstreno;
    }

    public List<Genre> getGeneros() {
        return mGeneros;
    }

    public void setGeneros(List<Genre> generos) {
        mGeneros = generos;
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

    public List<String> getLenguajes() {
        return mLenguajes;
    }

    public void setLenguajes(List<String> lenguajes) {
        mLenguajes = lenguajes;
    }

    public String getFechaDeUltimoCapitulo() {
        return mFechaDeUltimoCapitulo;
    }

    public void setFechaDeUltimoCapitulo(String fechaDeUltimoCapitulo) {
        mFechaDeUltimoCapitulo = fechaDeUltimoCapitulo;
    }

    public String getNombre() {
        return mNombre;
    }

    public void setNombre(String nombre) {
        mNombre = nombre;
    }

    public Integer getNumeroDeEpisodios() {
        return mNumeroDeEpisodios;
    }

    public void setNumeroDeEpisodios(Integer numeroDeEpisodios) {
        mNumeroDeEpisodios = numeroDeEpisodios;
    }

    public Integer getGetmNumeroDeTemporadas() {
        return getmNumeroDeTemporadas;
    }

    public void setGetmNumeroDeTemporadas(Integer getmNumeroDeTemporadas) {
        this.getmNumeroDeTemporadas = getmNumeroDeTemporadas;
    }

    public List<String> getPaisesDeOrigen() {
        return mPaisesDeOrigen;
    }

    public void setPaisesDeOrigen(List<String> paisesDeOrigen) {
        mPaisesDeOrigen = paisesDeOrigen;
    }

    public String getLenguaje() {
        return mLenguaje;
    }

    public void setLenguaje(String lenguaje) {
        mLenguaje = lenguaje;
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

    public List<Season> getTemporadas() {
        return mTemporadas;
    }

    public void setTemporadas(List<Season> temporadas) {
        mTemporadas = temporadas;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
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
