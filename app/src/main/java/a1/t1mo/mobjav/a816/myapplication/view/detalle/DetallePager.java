package a1.t1mo.mobjav.a816.myapplication.view.detalle;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.view.MainActivity;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 09/11/2016.
 */

public class DetallePager extends Fragment {
    private MainActivity mMainActivity;
    public static final String ARGUMENT_POSICION = "Posicion";

    public static DetallePager getDetallePager(Integer posicion) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENT_POSICION, posicion);
        DetallePager pager = new DetallePager();
        pager.setArguments(bundle);
        return pager;
    }

    public DetallePager() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mMainActivity = (MainActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        Integer posicion = bundle.getInt(ARGUMENT_POSICION);
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        switch (mMainActivity.getTipo()) {
            case PELICULAS:
                viewPager.setAdapter(new DetallePeliculaAdapter(getChildFragmentManager(),
                        mMainActivity.getPeliculas()));
                break;
            case SERIES:
                viewPager.setAdapter(new DetalleSerieAdapter(getChildFragmentManager(),
                        mMainActivity.getSeries()));
                break;
            case FAVORITOS:
                if (mMainActivity.getFavoritos().get(0) instanceof Pelicula) { // BAD VOODOO
                    viewPager.setAdapter(new DetallePeliculaAdapter(getChildFragmentManager(),
                            mMainActivity.getPeliculas()));
                } else {
                    viewPager.setAdapter(new DetalleSerieAdapter(getChildFragmentManager(),
                            mMainActivity.getSeries()));
                }
        }
        viewPager.setCurrentItem(posicion);
        return view;
    }
}
