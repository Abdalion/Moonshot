package a1.t1mo.mobjav.a816.myapplication.model.pelicula;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 31/10/2016.
 */

public class ListadoPeliculas {

    @SerializedName("page")
    private Integer mPagina;
    @SerializedName("results")
    private List<Pelicula> mPeliculas = new ArrayList<Pelicula>();
    @SerializedName("total_results")
    private Integer mTotalPeliculas;

    public Integer getTotalPaginas() {

        return mTotalPaginas;
    }

    public void setTotalPaginas(Integer totalPaginas) {
        mTotalPaginas = totalPaginas;
    }

    public Integer getTotalPeliculas() {
        return mTotalPeliculas;
    }

    public void setTotalPeliculas(Integer totalPeliculas) {
        mTotalPeliculas = totalPeliculas;
    }

    public List<Pelicula> getPeliculas() {
        return mPeliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        mPeliculas = peliculas;
    }

    public Integer getPagina() {
        return mPagina;
    }

    public void setPagina(Integer pagina) {
        mPagina = pagina;
    }

    @SerializedName("total_pages")
    private Integer mTotalPaginas;

}
