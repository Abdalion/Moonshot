package a1.t1mo.mobjav.a816.myapplication.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 22/10/2016.
 */

public class PeliculaDAO {
    private static final String TAG = PeliculaDAO.class.getSimpleName();

    public Pelicula getPelicula(Context context, String asset, Integer imgId, String generos) {
        Pelicula pelicula = null;
        AssetManager manager = context.getAssets();
        try {
            InputStreamReader reader = new InputStreamReader(manager.open(asset + ".json"));
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Pelicula.class, new SchemaOmdb());
            Gson parser = gsonBuilder.create();
            pelicula = parser.fromJson(reader, Pelicula.class);
        } catch (IOException e) {
            Log.e(TAG, "No existe el archivo", e);
            Toast.makeText(context, "No se encontro la pelicula", Toast.LENGTH_SHORT).show();
        } catch (JsonParseException jsonEx) {
            Log.e(TAG, "Error de parseo", jsonEx);
            Toast.makeText(context, "Error de parseo", Toast.LENGTH_SHORT).show();
        }
        pelicula.setImagenId(imgId);
        pelicula.setGeneros(generos);
        return pelicula;
    }

    private class SchemaOmdb implements JsonDeserializer<Pelicula> {

        @Override
        public Pelicula deserialize(JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            Pelicula pelicula = new Pelicula();
            pelicula.setImdbId(jsonObject.get("imdbID").getAsString());
            pelicula.setActores(jsonObject.get("Actors").getAsString());
            pelicula.setDirector(jsonObject.get("Director").getAsString());
            pelicula.setTitulo(jsonObject.get("Title").getAsString());
            pelicula.setTrama(jsonObject.get("Plot").getAsString());
            pelicula.setLenguaje(jsonObject.get("Language").getAsString());
            try {
                pelicula.setImdbRating(Float.parseFloat(jsonObject.get("imdbRating").getAsString()));
            }
            catch(Exception e) {
                pelicula.setImdbRating(0.0f);
                e.printStackTrace();
            }
            pelicula.setDuracion(jsonObject.get("Runtime").getAsString());
            SimpleDateFormat formato = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
            try {
                pelicula.setFechaDeEstreno(formato.parse(jsonObject.get("Released").getAsString()));
            } catch (ParseException e) {
                Log.e(TAG, "Error al parsear la fecha", e);
            }
            return pelicula;
        }
    }
}
