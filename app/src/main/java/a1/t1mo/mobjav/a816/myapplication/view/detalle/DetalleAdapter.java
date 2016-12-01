package a1.t1mo.mobjav.a816.myapplication.view.detalle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.model.Feature;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 01/12/2016.
 */

public abstract class DetalleAdapter extends FragmentStatePagerAdapter {
    public DetalleAdapter(FragmentManager fm) {
        super(fm);
    }

    public abstract void setFeatures(List<? extends Feature> features);
}
