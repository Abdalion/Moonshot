package a1.t1mo.mobjav.a816.myapplication.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import a1.t1mo.mobjav.a816.myapplication.model.GeneroPelicula;
import a1.t1mo.mobjav.a816.myapplication.model.GeneroSerie;

/**
 * Created by dh-mob-tt on 31/10/16.
 */
public class FragmentAdapterViewPager extends FragmentStatePagerAdapter {

    ArrayList<Fragment> listaDeFragments;

    public FragmentAdapterViewPager(FragmentManager fm) {
        super(fm);
        listaDeFragments = new ArrayList<>();


        listaDeFragments.add(PeliculaFragment.getPeliculaFragment(GeneroPelicula.TODAS.id));
        listaDeFragments.add(SerieFragment.getSerieFragment(GeneroSerie.TODAS.id));



    }

    @Override
    public Fragment getItem(int position) {
        return listaDeFragments.get(position);
    }

    @Override
    public int getCount() {
        return listaDeFragments.size();
    }
}
