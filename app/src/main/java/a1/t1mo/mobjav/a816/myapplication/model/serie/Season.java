package a1.t1mo.mobjav.a816.myapplication.model.serie;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dh-mob-tt on 31/10/16.
 */
public class Season {

    @SerializedName("air_date")
    public String airDate;
    @SerializedName("episode_count")
    public Integer episodeCount;
    @SerializedName("id")
    public Integer id;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("season_number")
    public Integer seasonNumber;

}