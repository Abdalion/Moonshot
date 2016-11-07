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
import a1.t1mo.mobjav.a816.myapplication.utils.CambioDePagina;
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

public class MainActivity extends AppCompatActivity
        implements FeatureFragment.Escuchable, CambioDePagina {
    FragmentManager fragmentManager;
    NavigationView navigationView;
    ViewPagerFragment viewPagerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        navigationViewSetup();
        viewPagerSetup();
    }

    private void navigationViewSetup() {
        navigationView = (NavigationView) findViewById(R.id.main_navigationView);
        navigationView.setCheckedItem(R.id.menu_peliculas_opcion_todas);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                viewPagerFragment.callBackCambioGenero(item.getItemId());
                return true;
            }
        });
    }

    private void viewPagerSetup() {
        viewPagerFragment = new ViewPagerFragment();
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_contenedorDeFragment, viewPagerFragment)
                .addToBackStack("back")
                .commit();
    }

    @Override
    public void onClickItem(Feature feature) {
/*        DetalleFeature detalle;
        if (feature instanceof Pelicula) {
            detalle = DetallePelicula.getDetalleFragment((Pelicula) feature);
        } else {
            detalle = DetalleSerie.getDetalleSerie((Serie) feature);
        }
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_contenedorDeFragment, detalle)
                .addToBackStack("back")
                .commit();*/
    }

    @Override
    public void onCambioDePagina(ViewPagerFragment.PaginaActual pagina) {
        navigationView.getMenu().clear();
        if(pagina == ViewPagerFragment.PaginaActual.PELICULAS) {
            navigationView.inflateMenu(R.menu.menu_navigation_peliculas);
        }
        else {
            navigationView.inflateMenu(R.menu.menu_navigation_series);
        }
    }

    public interface CallBackCambioGenero {
        public void callBackCambioGenero(int id);
    }
}
