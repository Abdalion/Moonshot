package a1.t1mo.mobjav.a816.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 03/11/2016.
 */

public class ListadoSeries {

    @SerializedName("page")
    private Integer mPagina;
    @SerializedName("results")
    private List<Serie> mSeries = new ArrayList<Serie>();
    @SerializedName("total_results")
    private Integer mTotalSeries;

    public Integer getTotalPaginas() {
        return mTotalPaginas;
    }

    public void setTotalPaginas(Integer totalPaginas) {
        mTotalPaginas = totalPaginas;
    }

    public Integer getTotalSeries() {
        return mTotalSeries;
    }

    public void setTotalSeries(Integer totalSeries) {
        mTotalSeries = totalSeries;
    }

    public List<Serie> getSeries() {
        return mSeries;
    }

    public void setSeries(List<Serie> series) {
        mSeries = series;
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