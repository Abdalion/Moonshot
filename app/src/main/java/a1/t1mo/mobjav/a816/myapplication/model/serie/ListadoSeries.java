package a1.t1mo.mobjav.a816.myapplication.model.serie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 03/11/2016.
 */

public class ListadoSeries extends RealmObject {

    @SerializedName("page")
    private Integer pagina;
    @SerializedName("results")
    private RealmList<Serie> series;
    @SerializedName("total_results")
    private Integer totalSeries;

    public Integer getTotalPaginas() {
        return mTotalPaginas;
    }

    public void setTotalPaginas(Integer totalPaginas) {
        mTotalPaginas = totalPaginas;
    }

    public Integer getTotalSeries() {
        return totalSeries;
    }

    public void setTotalSeries(Integer totalSeries) {
        this.totalSeries = totalSeries;
    }

    public RealmList<Serie> getSeries() {
        return series;
    }

    public void setSeries(RealmList<Serie> series) {
        this.series = series;
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }

    @SerializedName("total_pages")
    private Integer mTotalPaginas;

}