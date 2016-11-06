package a1.t1mo.mobjav.a816.myapplication.view;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;
import a1.t1mo.mobjav.a816.myapplication.view.feature.DetalleFeature;
import a1.t1mo.mobjav.a816.myapplication.view.feature.FeatureFragment;
import a1.t1mo.mobjav.a816.myapplication.view.feature.pelicula.DetallePelicula;
import a1.t1mo.mobjav.a816.myapplication.view.feature.serie.DetalleSerie;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 */

public class MainActivity extends AppCompatActivity implements FeatureFragment.Escuchable {
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
    public void onClickItem(Feature feature) {
        DetalleFeature detalle;
        if (feature instanceof Pelicula) {
            detalle = DetallePelicula.getDetalleFragment((Pelicula) feature);
        } else {
            detalle = DetalleSerie.getDetalleSerie((Serie) feature);
        }
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_contenedorDeFragment, detalle)
                .addToBackStack("back")
                .commit();
    }
}
