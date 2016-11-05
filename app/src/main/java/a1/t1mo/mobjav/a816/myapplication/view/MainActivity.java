package a1.t1mo.mobjav.a816.myapplication.view;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.GeneroPelicula;
import a1.t1mo.mobjav.a816.myapplication.model.serie.GeneroSerie;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;
import a1.t1mo.mobjav.a816.myapplication.view.feature.pelicula.DetalleFragment;
import a1.t1mo.mobjav.a816.myapplication.view.feature.pelicula.PeliculaFragment;
import a1.t1mo.mobjav.a816.myapplication.view.feature.serie.DetalleSerie;
import a1.t1mo.mobjav.a816.myapplication.view.feature.serie.SerieFragment;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 */

public class MainActivity extends AppCompatActivity
        implements PeliculaFragment.Escuchable, SerieFragment.Escuchable {
    FragmentManager fragmentManager;
    AdapterViewPagerFragment adapter;
    NavigationView navigationView;
    PaginaActual mPaginaActual;

    protected enum PaginaActual {PELICULAS, SERIES}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        mPaginaActual = PaginaActual.PELICULAS;
        navigationViewSetup();
        viewPagerSetup();
    }

    private void navigationViewSetup() {
        navigationView = (NavigationView) findViewById(R.id.main_navigationView);
        navigationView.setCheckedItem(R.id.menu_peliculas_opcion_todas);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                adapter.cambiarGenero(mPaginaActual, item.getItemId());
                return true;
            }
        });
    }

    private void viewPagerSetup() {
        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_contenedorDeFragment, viewPagerFragment)
                .addToBackStack("back")
                .commit();

    }

    @Override
    public void onClickItem(Pelicula pelicula) {
        DetalleFragment detalleFragment = DetalleFragment.getDetalleFragment(pelicula);
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_contenedorDeFragment, detalleFragment)
                .addToBackStack("back")
                .commit();
    }

    @Override
    public void onClickItem(Serie serie) {
        DetalleSerie detalleSerie = DetalleSerie.getDetalleSerie(serie);
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_contenedorDeFragment, detalleSerie)
                .addToBackStack("back")
                .commit();
    }
}
