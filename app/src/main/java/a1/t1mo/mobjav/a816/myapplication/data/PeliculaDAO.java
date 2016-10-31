package a1.t1mo.mobjav.a816.myapplication.data;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.data.services.TmdbService;
import a1.t1mo.mobjav.a816.myapplication.model.ListadoPeliculas;
import a1.t1mo.mobjav.a816.myapplication.model.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 22/10/2016.
 */

public class PeliculaDAO {
    private static final String TAG = PeliculaDAO.class.getSimpleName();
    private TmdbService mTmdbService;

    public PeliculaDAO() {
        mTmdbService = getTmdbService();
    }

    public void getPelicula(Integer id, final Listener<Pelicula> listener) {
        mTmdbService.getPelicula(id).enqueue(new Callback<Pelicula>() {
            @Override
            public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                if (response.isSuccessful()) {
                    listener.done(response.body());
                } else {
                    Log.e(TAG, "El servidor respondio con el codigo " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Pelicula> call, Throwable t) {
                Log.e(TAG, "No se pudo obtener la pelicula.");
            }
        });
    }

    public void getPeliculasPopulares(final Listener<List<Pelicula>> listener) {
        mTmdbService.getPeliculasPopulares(null).enqueue(new Callback<ListadoPeliculas>() {
            @Override
            public void onResponse(Call<ListadoPeliculas> call, Response<ListadoPeliculas> response) {
                if (response.isSuccessful()) {
                    listener.done(response.body().getPeliculas());
                } else {
                    Log.e(TAG, "El servidor respondio con el codigo " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ListadoPeliculas> call, Throwable t) {
                Log.e(TAG, "No se pudo obtener la pelicula.");
            }
        });
    }

    public void getPeliculasPorGenero(Integer id, final Listener<List<Pelicula>> listener) {
        mTmdbService.getPeliculasPorGenero(id).enqueue(new Callback<ListadoPeliculas>() {
            @Override
            public void onResponse(Call<ListadoPeliculas> call, Response<ListadoPeliculas> response) {
                if (response.isSuccessful()) {
                    listener.done(response.body().getPeliculas());
                } else {
                    Log.e(TAG, "El servidor respondio con el codigo " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ListadoPeliculas> call, Throwable t) {
                Log.e(TAG, "No se pudo obtener la pelicula.");
            }
        });
    }

    private TmdbService getTmdbService() {
        // Creamos un Interceptor para inyectar el query api_key=API_KEY en todos los requests
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();
                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", TmdbService.API_KEY)
                        .build();
                Request.Builder requestBuilder = original.newBuilder().url(url);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TmdbService.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(TmdbService.class);
    }
}
