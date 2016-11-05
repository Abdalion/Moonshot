package a1.t1mo.mobjav.a816.myapplication.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.model.Genre;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.GeneroPelicula;
import a1.t1mo.mobjav.a816.myapplication.model.serie.GeneroSerie;
import a1.t1mo.mobjav.a816.myapplication.view.feature.pelicula.PeliculaFragment;
import a1.t1mo.mobjav.a816.myapplication.view.feature.serie.SerieFragment;

/**
 * Created by dh-mob-tt on 31/10/16.
 */

public class AdapterViewPagerFragment extends FragmentStatePagerAdapter {

    private Integer mGeneroPeliculas;
    private Integer mGeneroSeries;
    private List<Fragment> mListaDeFragments;
    private Fragment mPeliculaFragment;
    private Fragment mSerieFragment;

    public AdapterViewPagerFragment(FragmentManager fm) {
        super(fm);
        mListaDeFragments = new ArrayList<>();
        mGeneroPeliculas = Genre.PELICULA_ID.get(R.id.menu_peliculas_opcion_todas);
        mGeneroSeries = Genre.SERIE_ID.get(R.id.menu_series_opcion_todas);
        updateFragments();
    }

    public void cambiarGenero(MainActivity.PaginaActual paginaActual, int genero) {
        if (paginaActual == MainActivity.PaginaActual.PELICULAS) {
            mGeneroPeliculas = genero;
        } else {
            mGeneroSeries = genero;
        }
        updateFragments();
    }

    private void updateFragments() {
        Log.d("DEBUGGER", "LLEGADO A UPDATE FRAGMENTS");
        mListaDeFragments.clear();
        mPeliculaFragment = PeliculaFragment.obtenerFragment(mGeneroPeliculas);
        mSerieFragment = SerieFragment.obtenerFragment(mGeneroSeries);
        mListaDeFragments.add(mPeliculaFragment);
        mListaDeFragments.add(mSerieFragment);
        notifyDataSetChanged();
    }

    public CharSequence getPageTitle(int position){
        return this.mListaDeFragments.get(position).getTitulo();
    }

    @Override
    public Fragment getItem(int position) {
        return mListaDeFragments.get(position);
    }

    @Override
    public int getCount() {
        return mListaDeFragments.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
