package a1.t1mo.mobjav.a816.myapplication.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.controller.Controller;
import a1.t1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a1.t1mo.mobjav.a816.myapplication.controller.SerieController;
import a1.t1mo.mobjav.a816.myapplication.data.PeliculaDAO;
import a1.t1mo.mobjav.a816.myapplication.data.SerieDAO;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;
import a1.t1mo.mobjav.a816.myapplication.utils.CambioDePagina;
import a1.t1mo.mobjav.a816.myapplication.utils.FavChange;
import a1.t1mo.mobjav.a816.myapplication.utils.Tipo;
import a1.t1mo.mobjav.a816.myapplication.view.detalle.DetallePager;
import a1.t1mo.mobjav.a816.myapplication.view.feature.FeatureFragment;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 */

public class MainActivity extends AppCompatActivity
        implements FeatureFragment.ListenerFeature, CambioDePagina, FavChange,
        Controller.ListenerSeries, Controller.ListenerPeliculas {

    private NavigationView navigationView;
    private FeaturePager mFeaturePager;
    private List<Pelicula> mPeliculas;
    private List<Serie> mSeries;
    private List<? extends Feature> mFavoritos;
    private Tipo mTipo = Tipo.PELICULAS;
    private SerieController mSerieController;
    private PeliculaController mPeliculaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Carga inicial de peliculas y series
        mPeliculaController = new PeliculaController(this);
        mPeliculaController.getPeliculasPopulares(new Controller.ListenerPeliculas() {
            @Override
            public void onFinish(List<Pelicula> peliculas) {
                mPeliculas = peliculas;
                if (mSeries != null) {
                    startPager();
                }
            }
        });
        mFavoritos = mPeliculaController.getFavoritos();
        mSerieController = new SerieController(this);
        mSerieController.getSeriesPopulares(new Controller.ListenerSeries() {
            @Override
            public void onDone(List<Serie> series) {
                mSeries = series;
                if (mPeliculas != null) {
                    startPager();
                }
            }
        });

//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
//        if (FacebookUtils.checkIfLogged()) {
//            FacebookUtils.requestUserInfo("name", this);
//        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationViewSetup();

    }

    public void startPager() {
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
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
                mFeaturePager.redrawFragment(Tipo.FAVORITOS);
        }
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
        Log.d("Main", "Cambio a pagina de " + mTipo.titulo);
    }

    @Override
    public void onClickFeature(Integer posicion) {
        commitFragment(DetallePager.getDetallePager(posicion));
    }

    private void commitFragment(Fragment fm) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_contenedorDeFragment, fm)
                .addToBackStack("back")
                .commit();
    }

    @Override
    public void onFinish(List<Pelicula> peliculas) {
        mPeliculas = peliculas;
        Log.d("Main", "Cargadas las peliculas. Cantidad = " + mPeliculas.size());
        mFeaturePager.redrawFragment(Tipo.PELICULAS);
    }

    @Override
    public void onDone(List<Serie> series) {
        mSeries = series;
        Log.d("Main", "Cargadas las series. Cantidad = " + mSeries.size());
        mFeaturePager.redrawFragment(Tipo.SERIES);
    }

    @Override
    public void onFavChange(int id, boolean isFav) {
        if (mTipo == Tipo.PELICULAS) {
            mPeliculaController.setFavorito(id, isFav);
        } else {
            mSerieController.setFavorito(id, isFav);
        }
    }

    public Tipo getTipo() {
        return mTipo;
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
