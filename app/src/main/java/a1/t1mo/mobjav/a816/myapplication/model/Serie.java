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
    public String backdropPath;
    @SerializedName("first_air_date")
    public String firstAirDate;
    @SerializedName("genres")
    public List<Genre> generos = new ArrayList<Genre>();
    @SerializedName("homepage")
    public String homepage;
    @SerializedName("id")
    public Integer id;
    @SerializedName("in_production")
    public Boolean inProduction;
    @SerializedName("languages")
    public List<String> languages = new ArrayList<String>();
    @SerializedName("last_air_date")
    public String lastAirDate;
    @SerializedName("name")
    public String name;
    @SerializedName("number_of_episodes")
    public Integer numberOfEpisodes;
    @SerializedName("number_of_seasons")
    public Integer numberOfSeasons;
    @SerializedName("origin_country")
    public List<String> originCountry = new ArrayList<String>();
    @SerializedName("original_language")
    public String originalLanguage;
    @SerializedName("original_name")
    public String originalName;
    @SerializedName("overview")
    public String overview;
    @SerializedName("popularity")
    public Double popularity;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("seasons")
    public List<Season> seasons = new ArrayList<Season>();
    @SerializedName("status")
    public String status;
    @SerializedName("type")
    public String type;
    @SerializedName("vote_average")
    public Double voteAverage;
    @SerializedName("vote_count")
    public Integer voteCount;

}
