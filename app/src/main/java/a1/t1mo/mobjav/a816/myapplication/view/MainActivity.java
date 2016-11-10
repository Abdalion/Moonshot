package a1.t1mo.mobjav.a816.myapplication.view;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.data.PeliculaDAO;
import a1.t1mo.mobjav.a816.myapplication.data.SerieDAO;
import a1.t1mo.mobjav.a816.myapplication.utils.CambioDePagina;
import a1.t1mo.mobjav.a816.myapplication.view.detalle.DetalleViewPager;
import a1.t1mo.mobjav.a816.myapplication.view.feature.FeatureFragment;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 */

public class MainActivity extends AppCompatActivity
        implements FeatureFragment.ListenerFeature, CambioDePagina {
    private FragmentManager fragmentManager;
    private NavigationView navigationView;
    private ViewPagerFragment viewPagerFragment;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        fragmentManager = getSupportFragmentManager();
        navigationViewSetup();
        viewPagerFragment = new ViewPagerFragment();
        commitFragment(viewPagerFragment);
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void navigationViewSetup() {
        navigationView = (NavigationView) findViewById(R.id.main_navigationView);
        navigationView.setCheckedItem(R.id.menu_peliculas_opcion_todas);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                viewPagerFragment.callBackCambioGenero(item.getItemId());

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_drawerLayout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
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

    @Override
    public void onClickFeature(Integer posicion, Integer genero, DetalleViewPager.Tipo tipo) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetalleViewPager.ARGUMENT_TIPO, tipo);
        bundle.putInt(DetalleViewPager.ARGUMENT_POSICION, posicion);
        bundle.putInt(DetalleViewPager.ARGUMENT_GENERO, genero);
        DetalleViewPager detalle = new DetalleViewPager();
        detalle.setArguments(bundle);
        commitFragment(detalle);
    }

    private void commitFragment(Fragment fm) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_contenedorDeFragment, fm)
                .addToBackStack("back")
                .commit();
    }

    public interface CallBackCambioGenero {
        void callBackCambioGenero(int id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PeliculaDAO.closeRealm();
        SerieDAO.closeRealm();
    }
}
