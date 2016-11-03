package a1.t1mo.mobjav.a816.myapplication.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import a1.t1mo.mobjav.a816.myapplication.model.GeneroPelicula;
import a1.t1mo.mobjav.a816.myapplication.model.GeneroSerie;

/**
 * Created by dh-mob-tt on 31/10/16.
 */

public class AdapterViewPagerFragment extends FragmentStatePagerAdapter {

    private Integer categoryID;
    ArrayList<Fragment> listaDeFragments;
    private Fragment fragmentaA;
    private Fragment fragmentaB;

    public AdapterViewPagerFragment(FragmentManager fm) {
        super(fm);
        listaDeFragments = new ArrayList<>();
        categoryID = GeneroPelicula.TODAS.id;
        updateFragments();
    }

    public void changeCategory(Integer id) {
        categoryID = id;
        updateFragments();
        Log.d("DEBUGGER", "LLEGADO A CHANGE CATEGORY");
    }

    private void updateFragments() {
        Log.d("DEBUGGER", "LLEGADO A UPDATE FRAGMENTS");
        listaDeFragments.clear();
        fragmentaA = PeliculaFragment.obtenerFragment(categoryID);
        fragmentaB = PeliculaFragment.obtenerFragment(categoryID);
        listaDeFragments.add(fragmentaA);
        listaDeFragments.add(fragmentaB);
        notifyDataSetChanged();
    }



    @Override
    public Fragment getItem(int position) {
        return listaDeFragments.get(position);
    }

    @Override
    public int getCount() {
        return listaDeFragments.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
