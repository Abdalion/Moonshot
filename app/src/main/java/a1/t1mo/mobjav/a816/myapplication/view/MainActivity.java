package a1.t1mo.mobjav.a816.myapplication.view;

import android.content.Intent;
import android.net.Uri;
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
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.data.PeliculaDAO;
import a1.t1mo.mobjav.a816.myapplication.data.SerieDAO;
import a1.t1mo.mobjav.a816.myapplication.utils.CambioDePagina;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;
import a1.t1mo.mobjav.a816.myapplication.view.detalle.DetalleViewPager;
import a1.t1mo.mobjav.a816.myapplication.view.feature.FeatureFragment;
import a1.t1mo.mobjav.a816.myapplication.view.login.LoginActivity;
import a1.t1mo.mobjav.a816.myapplication.view.login.facebook.FacebookUtils;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 */

public class MainActivity extends AppCompatActivity
        implements FeatureFragment.ListenerFeature, CambioDePagina, Listener<String> {
    private FragmentManager fragmentManager;
    private NavigationView navigationView;
    private ViewPagerFragment viewPagerFragment;
    private Toolbar toolbar;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);

        if(FacebookUtils.checkIfLogged()) {
            FacebookUtils.requestUserInfo("name", this);;
        }


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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
        if (pagina == ViewPagerFragment.PaginaActual.PELICULAS) {
            navigationView.inflateMenu(R.menu.menu_navigation_peliculas);
        } else if (pagina == ViewPagerFragment.PaginaActual.FAVORITOS) {
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
                .replace(R.id.main_contenedorDeFragment, fm)
                .addToBackStack("back")
                .commit();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public void done(String param) {
        Toast.makeText(MainActivity.this, "Bienvenido "+ param, Toast.LENGTH_SHORT).show();
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
