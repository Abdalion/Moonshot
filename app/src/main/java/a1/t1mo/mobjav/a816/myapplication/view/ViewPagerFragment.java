package a1.t1mo.mobjav.a816.myapplication.view;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.GeneroPelicula;
import a1.t1mo.mobjav.a816.myapplication.model.serie.GeneroSerie;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends Fragment implements MainActivity.CallBackCambioGenero {
    MainActivity mainActivity;
    PaginaActual paginaActual;
    AdapterViewPagerFragment adapter;

    public enum PaginaActual {PELICULAS, SERIES, FAVORITOS}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;
    }

    public ViewPagerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        paginaActual = PaginaActual.PELICULAS;
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
                if(position == 0)
                    paginaActual = PaginaActual.PELICULAS;
                else if(position == 1)
                    paginaActual = PaginaActual.SERIES;
                else if(position == 2)
                    paginaActual = PaginaActual.FAVORITOS;

                mainActivity.onCambioDePagina(paginaActual);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        return view;
    }

    @Override
    public void callBackCambioGenero(int id) {
        adapter.cambiarGenero(paginaActual, id);
    }
}
