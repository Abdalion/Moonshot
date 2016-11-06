package a1.t1mo.mobjav.a816.myapplication.model.serie;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.model.Genre;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 29/10/2016.
 */


public class Serie implements Feature, Parcelable {

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
    public Integer mNumeroDeTemporadas;
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

    public Integer getNumeroDeTemporadas() {
        return mNumeroDeTemporadas;
    }

    public void setNumeroDeTemporadas(Integer numeroDeTemporadas) {
        this.mNumeroDeTemporadas = numeroDeTemporadas;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mBackDropPath);
        dest.writeString(this.mFechaDeEstreno);
        dest.writeList(this.mGeneros);
        dest.writeString(this.mHomepage);
        dest.writeValue(this.mId);
        dest.writeStringList(this.mLenguajes);
        dest.writeString(this.mFechaDeUltimoCapitulo);
        dest.writeString(this.mNombre);
        dest.writeValue(this.mNumeroDeEpisodios);
        dest.writeValue(this.mNumeroDeTemporadas);
        dest.writeStringList(this.mPaisesDeOrigen);
        dest.writeString(this.mLenguaje);
        dest.writeString(this.mResumen);
        dest.writeValue(this.mPopularidad);
        dest.writeString(this.mPosterPath);
        dest.writeList(this.mTemporadas);
        dest.writeString(this.mStatus);
        dest.writeValue(this.mPuntajePromedio);
        dest.writeValue(this.mTotalVotos);
    }

    public Serie() {
    }

    protected Serie(Parcel in) {
        this.mBackDropPath = in.readString();
        this.mFechaDeEstreno = in.readString();
        this.mGeneros = new ArrayList<Genre>();
        in.readList(this.mGeneros, Genre.class.getClassLoader());
        this.mHomepage = in.readString();
        this.mId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mLenguajes = in.createStringArrayList();
        this.mFechaDeUltimoCapitulo = in.readString();
        this.mNombre = in.readString();
        this.mNumeroDeEpisodios = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mNumeroDeTemporadas = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mPaisesDeOrigen = in.createStringArrayList();
        this.mLenguaje = in.readString();
        this.mResumen = in.readString();
        this.mPopularidad = (Double) in.readValue(Double.class.getClassLoader());
        this.mPosterPath = in.readString();
        this.mTemporadas = new ArrayList<Season>();
        in.readList(this.mTemporadas, Season.class.getClassLoader());
        this.mStatus = in.readString();
        this.mPuntajePromedio = (Double) in.readValue(Double.class.getClassLoader());
        this.mTotalVotos = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Serie> CREATOR = new Parcelable.Creator<Serie>() {
        @Override
        public Serie createFromParcel(Parcel source) {
            return new Serie(source);
        }

        @Override
        public Serie[] newArray(int size) {
            return new Serie[size];
        }
    };
}
