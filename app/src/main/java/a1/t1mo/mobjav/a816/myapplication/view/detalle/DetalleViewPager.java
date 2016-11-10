package a1.t1mo.mobjav.a816.myapplication.view.detalle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.view.AdapterViewPagerFragment;
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
        paginaActual = ViewPagerFragment.PaginaActual.PELICULAS;
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        FragmentManager fragmentManager = getChildFragmentManager();
        adapter = new AdapterViewPagerFragment(fragmentManager);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                paginaActual = (paginaActual == ViewPagerFragment.PaginaActual.PELICULAS) ? ViewPagerFragment.PaginaActual.SERIES : ViewPagerFragment.PaginaActual.PELICULAS;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        return view;
    }
}
