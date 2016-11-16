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
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a1.t1mo.mobjav.a816.myapplication.controller.SerieController;
import a1.t1mo.mobjav.a816.myapplication.data.PeliculaDAO;
import a1.t1mo.mobjav.a816.myapplication.data.SerieDAO;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;
import a1.t1mo.mobjav.a816.myapplication.utils.CambioDePagina;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;
import a1.t1mo.mobjav.a816.myapplication.utils.Tipo;
import a1.t1mo.mobjav.a816.myapplication.view.detalle.DetalleViewPager;
import a1.t1mo.mobjav.a816.myapplication.view.feature.FeatureFragment;
import a1.t1mo.mobjav.a816.myapplication.view.login.facebook.FacebookUtils;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 */

public class MainActivity extends AppCompatActivity
        implements FeatureFragment.ListenerFeature, CambioDePagina,
        Listener<List<? extends Feature>> {
    private FragmentManager fragmentManager;
    private NavigationView navigationView;
    private FeaturePager mFeaturePager;
    private List<Pelicula> mPeliculas;
    private List<Serie> mSeries;
    private List<? extends Feature> mFavoritos;
    private Tipo mTipo;
    private SerieController mSerieController;
    private PeliculaController mPeliculaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPeliculaController = new PeliculaController(this);
        mSerieController = new SerieController(this);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        if (FacebookUtils.checkIfLogged()) {
            FacebookUtils.requestUserInfo("name", this);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        fragmentManager = getSupportFragmentManager();
        navigationViewSetup();
        mFeaturePager = new FeaturePager();
        commitFragment(mFeaturePager);
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
                getListaDeFeatures(item.getItemId());
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_drawerLayout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void getListaDeFeatures(int itemId) {
        switch (mTipo) {
            case PELICULAS:
                mPeliculaController.getPeliculas(itemId, this);
                break;
            case SERIES:
                mSerieController.getSeries(itemId, this);
                break;
            case FAVORITOS:
                if (itemId == R.id.menu_favoritos_opcion_peliculas) {
                    mFavoritos = mPeliculaController.getFavoritos();
                } else {
                    mFavoritos = mSerieController.getFavoritos();
                }
                break;
        }
        mFeaturePager.redrawFragment();
    }

    @Override
    public void onCambioDePagina(Tipo tipo) {
        navigationView.getMenu().clear();
        mTipo = tipo;
        if (mTipo == Tipo.PELICULAS) {
            navigationView.inflateMenu(R.menu.menu_navigation_peliculas);
        } else if (mTipo == Tipo.SERIES) {
            navigationView.inflateMenu(R.menu.menu_navigation_series);
        } else {
            navigationView.inflateMenu(R.menu.menu_navigation_favoritos);
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
                .add(R.id.main_contenedorDeFragment, fm)
                .addToBackStack("back")
                .commit();
    }

    @Override
    public void done(List<? extends Feature> featureList) {
        switch (mTipo) {
            case PELICULAS:
                mPeliculas = (List<Pelicula>) featureList;
                break;
            case SERIES:
                mSerieController.getSeries(itemId, this);
                break;
            case FAVORITOS:
                if (itemId == R.id.menu_favoritos_opcion_peliculas) {
                    mFavoritos = mPeliculaController.getFavoritos();
                } else {
                    mFavoritos = mSerieController.getFavoritos();
                }
                break;
        }
    }

    public interface CallBackCambioGenero {
        void callBackCambioGenero(int id);
    }

    public List<? extends Feature> getFavoritos() {
        return mFavoritos;
    }

    public List<Serie> getSeries() {
        return mSeries;
    }

    public List<Pelicula> getPeliculas() {
        return mPeliculas;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PeliculaDAO.closeRealm();
        SerieDAO.closeRealm();
    }
}
