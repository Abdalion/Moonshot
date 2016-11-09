package a1.t1mo.mobjav.a816.myapplication.model.pelicula;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 31/10/2016.
 */

public class ListadoPeliculas extends RealmObject {

    @SerializedName("page")
    private Integer pagina;
    @SerializedName("results")
    private RealmList<Pelicula> peliculas;
    @SerializedName("total_results")
    private Integer totalPeliculas;
    @SerializedName("total_pages")
    private Integer totalPaginas;

    public Integer getTotalPaginas() {

        return totalPaginas;
    }

    public void setTotalPaginas(Integer totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public Integer getTotalPeliculas() {
        return totalPeliculas;
    }

    public void setTotalPeliculas(Integer totalPeliculas) {
        this.totalPeliculas = totalPeliculas;
    }

    public RealmList<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(RealmList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }



}
