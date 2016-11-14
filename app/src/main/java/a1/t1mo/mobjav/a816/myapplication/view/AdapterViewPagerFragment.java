package a1.t1mo.mobjav.a816.myapplication.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.model.Genre;
import a1.t1mo.mobjav.a816.myapplication.view.favoritos.FavoritosFragment;
import a1.t1mo.mobjav.a816.myapplication.view.feature.FeatureFragment;
import a1.t1mo.mobjav.a816.myapplication.view.feature.pelicula.PeliculaFragment;
import a1.t1mo.mobjav.a816.myapplication.view.feature.serie.SerieFragment;

/**
 * Created by dh-mob-tt on 31/10/16.
 */

public class AdapterViewPagerFragment extends FragmentStatePagerAdapter {
    private Integer mGeneroPeliculas;
    private Integer mGeneroSeries;
    private Integer mTipoFavoritos;
    private List<FeatureFragment> mListaDeFragments;
    private PeliculaFragment mPeliculaFragment;
    private SerieFragment mSerieFragment;
    private FavoritosFragment mFavoritosFragment;

    public AdapterViewPagerFragment(FragmentManager fm) {
        super(fm);
        mListaDeFragments = new ArrayList<>();
        mGeneroPeliculas = Genre.PELICULA_ID.get(R.id.menu_peliculas_opcion_todas);
        mGeneroSeries = Genre.SERIE_ID.get(R.id.menu_series_opcion_todas);
        mTipoFavoritos = R.id.menu_favoritos_opcion_peliculas;
        updateFragments();
    }

    public void cambiarGenero(ViewPagerFragment.PaginaActual pagina, int genero) {
        if (pagina == ViewPagerFragment.PaginaActual.PELICULAS) {
            mGeneroPeliculas = genero;
        } else if(pagina == ViewPagerFragment.PaginaActual.SERIES){
            mGeneroSeries = genero;
        } else {
            mTipoFavoritos = genero;
        }
        updateFragments();
    }

    private void updateFragments() {
        mListaDeFragments.clear();
        mPeliculaFragment = PeliculaFragment.obtenerFragment(mGeneroPeliculas);
        mSerieFragment = SerieFragment.obtenerFragment(mGeneroSeries);
        mFavoritosFragment = FavoritosFragment.obtenerFragment(mTipoFavoritos);
        mListaDeFragments.add(mPeliculaFragment);
        mListaDeFragments.add(mSerieFragment);
        mListaDeFragments.add(mFavoritosFragment);
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
