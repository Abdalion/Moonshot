package a1.t1mo.mobjav.a816.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.data.PeliculaDAO;
import a1.t1mo.mobjav.a816.myapplication.data.SerieDAO;
import a1.t1mo.mobjav.a816.myapplication.utils.Tipo;
import a1.t1mo.mobjav.a816.myapplication.view.login.CropCircleTransform;
import a1.t1mo.mobjav.a816.myapplication.view.login.LoginActivity;

import static a1.t1mo.mobjav.a816.myapplication.utils.General.isNot;
import static a1.t1mo.mobjav.a816.myapplication.utils.UserActions.getCurrentUser;
import static a1.t1mo.mobjav.a816.myapplication.utils.UserActions.isUserLogged;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 */

public class MainActivity extends AppCompatActivity implements CambioDePagina {

    private static boolean CONFIRM_LEAVE;
    private NavigationView navigationView;
    private FeaturePager mFeaturePager;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);

        CONFIRM_LEAVE = false;
        if (isUserLogged()) {
            firebaseUser = getCurrentUser();
        }

        if (isUserLogged()) {
            Toast.makeText(this, "Welcome " + firebaseUser.getDisplayName() + "!", Toast.LENGTH_SHORT).show();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_drawerLayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mFeaturePager = new FeaturePager();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_contenedorDeFragment, mFeaturePager)
                .addToBackStack("back")
                .commit();
        navigationView = (NavigationView) findViewById(R.id.main_navigationView);
        navigationView.setCheckedItem(R.id.menu_peliculas_opcion_todas);
        navigationView.setNavigationItemSelectedListener(mFeaturePager);
        navigationViewSetup();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            if (CONFIRM_LEAVE) {
                finish();
            } else {
                Toast.makeText(MainActivity.this, R.string.confirm_leave, Toast.LENGTH_SHORT).show();
                CONFIRM_LEAVE = true;
            }
        } else {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if (isUserLogged()) {
            inflater.inflate(R.menu.menu_settings, menu);
        } else {
            inflater.inflate(R.menu.menu_navigation_toolbar_opciones, menu);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.guest && isNot(isUserLogged())) {
            startActivity(new Intent(this, LoginActivity.class));
        } else if (id == R.id.menu_configuration) {
            Toast.makeText(this, "Configuration", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void navigationViewSetup() {
        if (isUserLogged()) {
            View headerLayout = navigationView.getHeaderView(0);
            TextView textView = (TextView) headerLayout.findViewById(R.id.nombreDePersona);
            textView.setText(firebaseUser.getDisplayName());

            final ImageView imageView = (ImageView) headerLayout.findViewById(R.id.imageViewPersona);
            Glide.with(this).load(firebaseUser.getPhotoUrl()).bitmapTransform(new CropCircleTransform(this)).into(imageView);
        } else {
            View headerLayout = navigationView.getHeaderView(0);
            TextView textView = (TextView) headerLayout.findViewById(R.id.nombreDePersona);
            textView.setText("Usuario no Registrado");
        }
    }
    @Override
    public void onCambioDePagina(Tipo tipo) {
        navigationView.getMenu().clear();
        if (tipo == Tipo.PELICULAS) {
            navigationView.inflateMenu(R.menu.menu_navigation_peliculas);
        } else if (tipo == Tipo.SERIES) {
            navigationView.inflateMenu(R.menu.menu_navigation_series);
        } else {
            navigationView.inflateMenu(R.menu.menu_navigation_favoritos);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PeliculaDAO.closeRealm();
        SerieDAO.closeRealm();
    }
}
