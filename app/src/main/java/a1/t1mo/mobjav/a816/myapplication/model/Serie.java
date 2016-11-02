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

}
