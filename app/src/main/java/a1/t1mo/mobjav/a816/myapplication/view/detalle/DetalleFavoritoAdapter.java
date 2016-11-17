package a1.t1mo.mobjav.a816.myapplication.view.detalle;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a1.t1mo.mobjav.a816.myapplication.controller.SerieController;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 10/11/2016.
 */

public class DetalleFavoritoAdapter extends FragmentStatePagerAdapter
        implements DetalleFeature.Likeable {
    private List<? extends Feature> mListaDeFavoritos;
    private PeliculaController mPeliculaController;
    private SerieController mSerieController;
    private DetallePager.Tipo mTipo;

    public DetalleFavoritoAdapter(FragmentManager fm, Context ctx, DetallePager.Tipo tipo) {
        super(fm);
        mTipo = tipo;
        if (mTipo == DetallePager.Tipo.PELICULA) {
            mPeliculaController = new PeliculaController(ctx);
            mListaDeFavoritos = mPeliculaController.getFavoritos();
        } else {
            mSerieController = new SerieController(ctx);
            mListaDeFavoritos = mSerieController.getFavoritos();
        }
    }

    @Override
    public Fragment getItem(int position) {
        if (mTipo == DetallePager.Tipo.PELICULA) {
            return DetallePelicula.getDetallePelicula((Pelicula) (mListaDeFavoritos.get(position)), this);
        } else {
            return DetalleSerie.getDetalleSerie((Serie) (mListaDeFavoritos.get(position)), this);
        }
    }

    @Override
    public int getCount() {
        return mListaDeFavoritos != null ? mListaDeFavoritos.size() : 0;
    }

    @Override
    public void onLike(Integer featureID) {
        if (mTipo == DetallePager.Tipo.PELICULA) {
            mPeliculaController.agregarAFavoritos(featureID);
        } else {
            mSerieController.agregarAFavoritos(featureID);
        }
    }

    @Override
    public void onUnlike(Integer featureID) {
        if (mTipo == DetallePager.Tipo.PELICULA) {
            mPeliculaController.quitarDeFavoritos(featureID);
        } else {
            mSerieController.quitarDeFavoritos(featureID);
        }
    }
}