package a1.t1mo.mobjav.a816.myapplication.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import a1.t1mo.mobjav.a816.myapplication.utils.Tipo;
import a1.t1mo.mobjav.a816.myapplication.view.favoritos.FavoritosFragment;
import a1.t1mo.mobjav.a816.myapplication.view.feature.FeatureFragment;
import a1.t1mo.mobjav.a816.myapplication.view.feature.pelicula.PeliculaFragment;
import a1.t1mo.mobjav.a816.myapplication.view.feature.serie.SerieFragment;

/**
 * Created by dh-mob-tt on 31/10/16.
 */

public class FeaturePagerAdapter extends FragmentStatePagerAdapter {
    private FeatureFragment[] mListaDeFragments;
    private MainActivity mMainActivity;

    public FeaturePagerAdapter(FragmentManager fm, MainActivity activity) {
        super(fm);
        mMainActivity = activity;
        mListaDeFragments = new FeatureFragment[3];
        mListaDeFragments[0] = FeatureFragment.getFeatureFragment(Tipo.PELICULAS);
        mListaDeFragments[1] = FeatureFragment.getFeatureFragment(Tipo.SERIES);
        mListaDeFragments[2] = FeatureFragment.getFeatureFragment(Tipo.FAVORITOS);
    }

    public CharSequence getPageTitle(int position){
        return mMainActivity.getTipo().titulo;
    }

    @Override
    public Fragment getItem(int position) {
        return mListaDeFragments[position];
    }

    @Override
    public int getCount() {
        return mListaDeFragments.length;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void redrawFragment(Tipo tipo) {
        switch (tipo) {
            case PELICULAS:
                mListaDeFragments[0].redraw();
                break;
            case SERIES:
                mListaDeFragments[1].redraw();
                break;
            case FAVORITOS:
                mListaDeFragments[2].redraw();
        }
    }
}
