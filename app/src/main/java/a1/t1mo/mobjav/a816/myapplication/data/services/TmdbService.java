package a1.t1mo.mobjav.a816.myapplication.data.services;

import java.util.List;
import java.util.Map;

import a1.t1mo.mobjav.a816.myapplication.model.ListadoPeliculas;
import a1.t1mo.mobjav.a816.myapplication.model.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.model.Serie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 29/10/2016.
 */

public interface TmdbService {
    String BASE_URL = "https://api.themoviedb.org/3/";
    String IMAGE_URL_W154 = "http://image.tmdb.org/t/p/w154";
    String IMAGE_URL_W185 = "http://image.tmdb.org/t/p/w185";
    String API_KEY = "f705c06489826188c47e25f982e97f17";

    @GET("movie/{movie_id}")
    Call<Pelicula> getPelicula(@Path("movie_id") Integer id);

    @GET("movie/popular")
    Call<ListadoPeliculas> getPeliculasPopulares();

    @GET("movie/top_rated")
    Call<ListadoPeliculas> getPeliculasMejorRankeadas();

    @GET("genre/{genre_id}/movies")
    Call<ListadoPeliculas> getPeliculasPorGenero(@Path("genre_id") Integer genero);

    @GET("tv/{tv_id}")
    Call<Serie> getSerie(@Path("tv_id") Integer id);

    @GET("tv/popular")
    Call<List<Serie>> getSeriesPopulares();

    @GET("tv/top_rated")
    Call<List<Serie>> getSeriesMejorRankeadas();

    @GET("discover/tv")
    Call<List<Serie>> getSeriesPorGenero(@Query("with_genres") Integer genero);
}
