package a1.t1mo.mobjav.a816.myapplication.data.services;

import a1.t1mo.mobjav.a816.myapplication.model.pelicula.ListadoPeliculas;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.model.serie.ListadoSeries;
import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    String IMAGE_URL_W300 = "http://image.tmdb.org/t/p/w300";
    String API_KEY = "f705c06489826188c47e25f982e97f17";

    @GET("movie/{movie_id}")
    Call<Pelicula> getPelicula(@Path("movie_id") Integer id);

    @GET("movie/popular")
    Call<ListadoPeliculas> getPeliculasPopulares();

    @GET("movie/top_rated")
    Call<ListadoPeliculas> getPeliculasMejorRankeadas();

    @GET("genre/{genre_id}/movies")
    Call<ListadoPeliculas> getPeliculasPorGenero(@Path("genre_id") String genero);

    @GET("tv/{tv_id}")
    Call<Serie> getSerie(@Path("tv_id") Integer id);

    @GET("tv/popular")
    Call<ListadoSeries> getSeriesPopulares();

    @GET("tv/top_rated")
    Call<ListadoSeries> getSeriesMejorRankeadas();

    @GET("discover/tv")
    Call<ListadoSeries> getSeriesPorGenero(@Query("with_genres") String genero);
}
