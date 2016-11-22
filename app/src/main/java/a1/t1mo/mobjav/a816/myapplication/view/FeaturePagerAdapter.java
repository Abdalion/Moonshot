package a1.t1mo.mobjav.a816.myapplication.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import a1.t1mo.mobjav.a816.myapplication.utils.Tipo;
import a1.t1mo.mobjav.a816.myapplication.view.feature.FeatureFragment;

/**
 * Created by dh-mob-tt on 31/10/16.
 */

public class FeaturePagerAdapter extends FragmentStatePagerAdapter {
    private FeatureFragment[] mArrayDeFragments;

    public FeaturePagerAdapter(FragmentManager fm) {
        super(fm);
        mArrayDeFragments = new FeatureFragment[3];
        mArrayDeFragments[0] = FeatureFragment.getFeatureFragment(Tipo.PELICULAS);
        mArrayDeFragments[1] = FeatureFragment.getFeatureFragment(Tipo.SERIES);
        mArrayDeFragments[2] = FeatureFragment.getFeatureFragment(Tipo.FAVORITOS);
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

    public void redrawFragment(Tipo tipo) {
        int index = 0;
        switch (tipo) {
            case PELICULAS:
                index = 0;
                break;
            case SERIES:
                index = 1;
                break;
            case FAVORITOS:
                index = 2;
        }
        mArrayDeFragments[index] = FeatureFragment.getFeatureFragment(tipo);
        notifyDataSetChanged();
        mArrayDeFragments[index].redraw();
    }
}
