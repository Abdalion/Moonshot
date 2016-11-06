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
public class ViewPagerFragment extends Fragment {

    MainActivity mainActivity;
    Bundle bundle = getArguments();
    MainActivity.PaginaActual paginaActual = (MainActivity.PaginaActual) bundle.getSerializable("paginaActual");

    public interface CallBackCambioGenero {
        public void callBackCambioGenero();
    }

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
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        FragmentManager fragmentManager = getChildFragmentManager();
        AdapterViewPagerFragment adapter = new AdapterViewPagerFragment(fragmentManager);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        adapter.cambiarGenero(paginaActual, GeneroPelicula.TODAS.id);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mainActivity.callBackCambioGenero();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        return view;
    }

}
