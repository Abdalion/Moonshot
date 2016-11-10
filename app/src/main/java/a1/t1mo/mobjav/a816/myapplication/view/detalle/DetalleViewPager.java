package a1.t1mo.mobjav.a816.myapplication.view.detalle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a1.t1mo.mobjav.a816.myapplication.controller.SerieController;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 09/11/2016.
 */

public class DetalleViewPager extends Fragment {
    private Integer mPosicion;
    private Integer mGenero;
    private Tipo mTipo;
    public static final String ARGUMENT_POSICION = "Posicion";
    public static final String ARGUMENT_GENERO = "Genero";
    public static final String ARGUMENT_TIPO = "Tipo";
    public static final Integer FAVORITOS_FLAG = 0;

    public enum Tipo {PELICULA, SERIE}

    public DetalleViewPager() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        mPosicion = bundle.getInt(ARGUMENT_POSICION);
        mGenero = bundle.getInt(ARGUMENT_GENERO);
        mTipo = (Tipo) bundle.getSerializable(ARGUMENT_TIPO);

        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        FragmentManager fragmentManager = getChildFragmentManager();
        if (mGenero == FAVORITOS_FLAG) {
            viewPager.setAdapter(new DetalleFavoritoAdapter(fragmentManager, getContext(), mTipo));
        } else {
            if (mTipo == Tipo.PELICULA) {
                viewPager.setAdapter(new DetallePeliculaAdapter(fragmentManager, getContext(), mGenero));
            } else {
                viewPager.setAdapter(new DetalleSerieAdapter(fragmentManager, getContext(), mGenero));
            }
        }
        viewPager.setCurrentItem(mPosicion);
        return view;
    }
}
