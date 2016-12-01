package a1.t1mo.mobjav.a816.myapplication.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
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
import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.like.LikeButton;
import com.like.OnLikeListener;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.data.PeliculaDAO;
import a1.t1mo.mobjav.a816.myapplication.data.SerieDAO;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;
import a1.t1mo.mobjav.a816.myapplication.utils.Tipo;
import a1.t1mo.mobjav.a816.myapplication.utils.TipoDeFeature;
import a1.t1mo.mobjav.a816.myapplication.view.detalle.DetallePager;
import a1.t1mo.mobjav.a816.myapplication.view.feature.FeatureFragment;
import a1.t1mo.mobjav.a816.myapplication.view.login.CropCircleTransform;
import a1.t1mo.mobjav.a816.myapplication.view.login.LoginActivity;
import a1.t1mo.mobjav.a816.myapplication.view.login.facebook.FacebookUtils;

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
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);

        CONFIRM_LEAVE = false;

        user = FirebaseAuth.getInstance().getCurrentUser();
        if(isNot(user == null)) {
            Toast.makeText(this, "Welcome " + user.getDisplayName() + "!", Toast.LENGTH_SHORT).show();
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
                .add(R.id.main_contenedorDeFragment, mFeaturePager)
                .addToBackStack("back")
                .commit();
        navigationView = (NavigationView) findViewById(R.id.main_navigationView);
        navigationView.setCheckedItem(R.id.menu_peliculas_opcion_todas);
        navigationView.setNavigationItemSelectedListener(mFeaturePager);
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

    private void navigationViewSetup() {
        navigationView = (NavigationView) findViewById(R.id.main_navigationView);

            navigationView.setCheckedItem(R.id.menu_peliculas_opcion_todas);

            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    getListaDeFeatures(item.getItemId());
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_drawerLayout);
                    drawer.closeDrawer(GravityCompat.START);
                    int id = item.getItemId();
                    if (id==R.id.logout_nav){
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(MainActivity.this, "Logged out!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,MainActivity.class));
                    }

                    return true;
                }
            });

        if (isNot(user == null)) {
            View headerLayout = navigationView.getHeaderView(0);
            TextView textView = (TextView) headerLayout.findViewById(R.id.nombreDePersona);
            textView.setText(user.getDisplayName());

            final ImageView imageView = (ImageView) headerLayout.findViewById(R.id.imageViewPersona);
            Glide.with(this).load(user.getPhotoUrl()).bitmapTransform(new CropCircleTransform(this)).into(imageView);
        }
        else{
            View headerLayout = navigationView.getHeaderView(0);
            TextView textView = (TextView) headerLayout.findViewById(R.id.nombreDePersona);
            textView.setText("Usuario no Registrado");
        }


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if(isNot(user == null)) {
            inflater.inflate(R.menu.menu_settings, menu);
        }
        else {
            inflater.inflate(R.menu.menu_navigation_toolbar_opciones, menu);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.guest) {
            if(user == null) {
                startActivity(new Intent(this,LoginActivity.class));
            }
        }
        else if(id == R.id.menu_configuration) {
            Toast.makeText(this, "Configuration", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
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
    public void onFavChange(int id, boolean isFav, TipoDeFeature tipoDeFeature) {
        if (tipoDeFeature.equals(TipoDeFeature.PELICULA)) {
            mPeliculaController.setFavorito(id, isFav);
        } else {
            mSerieController.setFavorito(id, isFav);
        }
    }

    @Override
    public void favNotLogued() {
        startActivity(new Intent(this, LoginActivity.class));
    }
    protected void onDestroy() {
        super.onDestroy();
        PeliculaDAO.closeRealm();
        SerieDAO.closeRealm();
    }

}
