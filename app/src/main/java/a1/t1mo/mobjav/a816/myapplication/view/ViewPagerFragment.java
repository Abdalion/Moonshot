package a1.t1mo.mobjav.a816.myapplication.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.GeneroPelicula;
import a1.t1mo.mobjav.a816.myapplication.model.serie.GeneroSerie;

public class ViewPagerFragment extends Fragment {

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

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                GeneroManager generoManager = GeneroManager.getGeneroManager();
                if(position == 0) {
                    generoManager.setPeliculaOSerie("PELICULA");
                }
                else {
                    generoManager.setPeliculaOSerie("SERIE");
                }
                generoManager.updateNavigationMenu(navigationView);
                Toast.makeText(getContext(), generoManager.getPeliculaOSerie(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        return view;
    }

}
