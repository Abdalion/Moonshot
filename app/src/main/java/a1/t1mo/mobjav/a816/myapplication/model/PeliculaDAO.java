package a1.t1mo.mobjav.a816.myapplication.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 22/10/2016.
 */

public class PeliculaDAO {
    private final String TAG = this.getClass().getSimpleName();

    public static Pelicula getPelicula(Context context, Integer assetID) {
        Pelicula pelicula = null;

        AssetManager manager = context.getAssets();
        try {
            InputStream input = manager.open("news.xml");
        } catch (IOException e) {
            Log.e(TAG, "No existe el archivo", e);
            Toast.makeText(context, "No se encontro la pelicula", Toast.LENGTH_SHORT).show();
        }


        return pelicula;
    }

    private class SchemaOmdb implements JsonDeserializer<Pelicula> {

        @Override
        public Pelicula deserialize(JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context) throws JsonParseException {
            final JsonObject jsonObject = json.getAsJsonObject();

            final JsonElement jsonTitle = jsonObject.get("title");
            final String title = jsonTitle.getAsString();

            final String isbn10 = jsonObject.get("isbn-10").getAsString();
            final String isbn13 = jsonObject.get("isbn-13").getAsString();

            final JsonArray jsonAuthorsArray = jsonObject.get("authors").getAsJsonArray();
        }
    }
