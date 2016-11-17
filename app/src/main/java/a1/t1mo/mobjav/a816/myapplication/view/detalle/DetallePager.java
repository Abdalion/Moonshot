package a1.t1mo.mobjav.a816.myapplication.view.detalle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.utils.Tipo;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 09/11/2016.
 */

public class DetallePager extends Fragment {
    public static final String ARGUMENT_TIPO = "Tipo";
    public static final String ARGUMENT_POSICION = "Posicion";

    public static DetallePager getDetallePager(Tipo tipo, Integer posicion) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGUMENT_TIPO, tipo);
        bundle.putInt(ARGUMENT_POSICION, posicion);
        DetallePager pager = new DetallePager();
        pager.setArguments(bundle);
        return pager;
    }

    public DetallePager() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        Integer posicion = bundle.getInt(ARGUMENT_POSICION);
        Tipo tipo = (Tipo) bundle.getSerializable(ARGUMENT_TIPO);
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new DetallePagerAdapter(getChildFragmentManager(), tipo));
        viewPager.setCurrentItem(posicion);
        return view;
    }
}
