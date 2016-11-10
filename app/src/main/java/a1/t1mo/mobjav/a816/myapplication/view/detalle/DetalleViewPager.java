package a1.t1mo.mobjav.a816.myapplication.view.detalle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.view.MainActivity;
import a1.t1mo.mobjav.a816.myapplication.view.ViewPagerFragment;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 09/11/2016.
 */

public class DetalleViewPager extends Fragment {
    private DetalleAdapter mAdapter;

    public DetalleViewPager() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        FragmentManager fragmentManager = getChildFragmentManager();
        mAdapter = new DetalleAdapter(fragmentManager);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(mAdapter);
        return view;
    }
}
