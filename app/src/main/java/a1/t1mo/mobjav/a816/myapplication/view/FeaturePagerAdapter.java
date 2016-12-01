package a1.t1mo.mobjav.a816.myapplication.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import a1.t1mo.mobjav.a816.myapplication.utils.Tipo;
import a1.t1mo.mobjav.a816.myapplication.view.feature.FavoritosFragment;
import a1.t1mo.mobjav.a816.myapplication.view.feature.FeatureFragment;
import a1.t1mo.mobjav.a816.myapplication.view.feature.GridFragment;

/**
 * Created by dh-mob-tt on 31/10/16.
 */

public class FeaturePagerAdapter extends FragmentStatePagerAdapter {
    private GridFragment[] mArrayDeFragments;

    public FeaturePagerAdapter(FragmentManager fm) {
        super(fm);
        mArrayDeFragments = new GridFragment[3];
        mArrayDeFragments[0] = FeatureFragment.getFeatureFragment(Tipo.PELICULAS);
        mArrayDeFragments[1] = FeatureFragment.getFeatureFragment(Tipo.SERIES);
        mArrayDeFragments[2] = new FavoritosFragment();
    }

    public CharSequence getPageTitle(int position) {
        String titulo;
        switch (position) {
            case 0:
                titulo = "Peliculas";
                break;
            case 1:
                titulo = "Series";
                break;
            default:
                titulo = "Favoritos";
        }
        return titulo;
    }

    @Override
    public Fragment getItem(int position) {
        return mArrayDeFragments[position];
    }

    @Override
    public int getCount() {
        return mArrayDeFragments.length;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void redrawFragment(int posicion, int menuId) {
        mArrayDeFragments[posicion].redraw(menuId);
    }
}
