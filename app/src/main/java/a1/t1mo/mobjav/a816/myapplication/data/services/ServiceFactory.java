package a1.t1mo.mobjav.a816.myapplication.data.services;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import a1.t1mo.mobjav.a816.myapplication.model.RealmString;
import io.realm.RealmList;
import io.realm.RealmObject;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 03/11/2016.
 */

public final class ServiceFactory {
    public static TmdbService getTmdbService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TmdbService.BASE_URL)
                .client(getInterceptor())
                .addConverterFactory(GsonConverterFactory.create(getGsonInstance()))
                .build();
        return retrofit.create(TmdbService.class);
    }

    private static OkHttpClient getInterceptor() {
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
        return client;
    }

    /**
     * Parser __especial__ para leer objetos RealmObject con GSON
     */
    private static Gson getGsonInstance() {
        return new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .registerTypeAdapter(new TypeToken<RealmList<RealmString>>() {}.getType(),
                                        new RealmStringDeserializer())
                .create();
    }

    /**
     * Deserializador para que no explote GSON al leer atributos de Tipo List<String>
     */
    private static class RealmStringDeserializer implements
            JsonDeserializer<RealmList<RealmString>> {

        @Override
        public RealmList<RealmString> deserialize(JsonElement json, Type typeOfT,
                      JsonDeserializationContext context) throws JsonParseException {

            RealmList<RealmString> realmStrings = new RealmList<>();
            JsonArray stringList = json.getAsJsonArray();

            for (JsonElement stringElement : stringList) {
                realmStrings.add(new RealmString(stringElement.getAsString()));
            }

            return realmStrings;
        }
    }
}
