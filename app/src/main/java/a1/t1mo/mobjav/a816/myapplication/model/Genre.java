package a1.t1mo.mobjav.a816.myapplication.model;

import android.util.ArrayMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

import a1.t1mo.mobjav.a816.myapplication.R;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 29/10/2016.
 */

/**
 * Clase utilizada para deserializar los JSON
 */
public class Genre extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("name")
    @Expose
    public String nombre;

    public static final Map<Integer, String> PELICULA_ID = crearDiccionarioPelicula();
    public static final Map<Integer, String> SERIE_ID = crearDiccionarioSerie();


    private static Map<Integer, String> crearDiccionarioPelicula() {
        Map<Integer, String> diccionarioPelicula = new ArrayMap<>(10);
        diccionarioPelicula.put(R.id.menu_peliculas_opcion_todas, "-1");
        diccionarioPelicula.put(R.id.menu_peliculas_opcion_accion, "28");
        diccionarioPelicula.put(R.id.menu_peliculas_opcion_aventura, "12");
        diccionarioPelicula.put(R.id.menu_peliculas_opcion_scifi, "878");
        diccionarioPelicula.put(R.id.menu_peliculas_opcion_comedia, "35");
        diccionarioPelicula.put(R.id.menu_peliculas_opcion_crimen, "80");
        diccionarioPelicula.put(R.id.menu_peliculas_opcion_drama, "18");
        diccionarioPelicula.put(R.id.menu_peliculas_opcion_horror, "27");
        diccionarioPelicula.put(R.id.menu_peliculas_opcion_romance, "10749");
        diccionarioPelicula.put(R.id.menu_peliculas_opcion_thriller, "53");
        return diccionarioPelicula;
    }

    private static Map<Integer, String> crearDiccionarioSerie() {
        Map<Integer, String> diccionarioSerie = new ArrayMap<>(10);
        diccionarioSerie.put(R.id.menu_series_opcion_todas, "-1");
        diccionarioSerie.put(R.id.menu_series_opcion_accion, "10759");
        diccionarioSerie.put(R.id.menu_series_opcion_scifi, "10765");
        diccionarioSerie.put(R.id.menu_series_opcion_comedia, "35");
        diccionarioSerie.put(R.id.menu_series_opcion_crimen, "80");
        diccionarioSerie.put(R.id.menu_series_opcion_documental, "99");
        diccionarioSerie.put(R.id.menu_series_opcion_drama, "18");
        diccionarioSerie.put(R.id.menu_series_opcion_infantil, "10762");
        diccionarioSerie.put(R.id.menu_series_opcion_misterio, "9648");
        diccionarioSerie.put(R.id.menu_series_opcion_reality, "10764");
        return diccionarioSerie;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
