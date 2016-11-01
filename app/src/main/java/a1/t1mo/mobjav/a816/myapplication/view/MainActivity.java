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

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a1.t1mo.mobjav.a816.myapplication.model.GeneroPelicula;
import a1.t1mo.mobjav.a816.myapplication.model.ListadoPeliculas;
import a1.t1mo.mobjav.a816.myapplication.model.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 */

public class MainActivity extends AppCompatActivity
        implements PeliculaFragment.Escuchable, Listener<List<Pelicula>> {

    FragmentManager fragmentManager;
    PeliculaController mPeliculaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        mPeliculaController = new PeliculaController();
        mPeliculaController.getPeliculasPopulares(this);

        FragmentAdapterViewPager fragmentAdapter = new FragmentAdapterViewPager(fragmentManager);
        ViewPager viewPager = (ViewPager)findViewById(R.id.activity_main_pager);
        viewPager.setAdapter(fragmentAdapter);

        NavigationView navigationView = (NavigationView) findViewById(R.id.main_navigationView);
        navigationView.setCheckedItem(R.id.menu_opcion_todas);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                onSelectItem(item);
                return true;
            }
        });

        Button button = (Button) findViewById(R.id.main_botonDrawer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_drawerLayout);
                drawer.openDrawer(Gravity.LEFT);
            }
        });
    }


    private void onSelectItem(MenuItem item) {
        if(item.getItemId() == R.id.menu_opcion_todas) {
            mPeliculaController.getPeliculasPopulares(this);
            Toast.makeText(MainActivity.this, "Se cargaron todas las peliculas", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.menu_opcion_drama) {
            mPeliculaController.getPeliculasPorGenero(GeneroPelicula.DRAMA.id, this);
            Toast.makeText(MainActivity.this, "Se cargaron las peliculas de drama", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.menu_opcion_thriller) {
            mPeliculaController.getPeliculasPorGenero(GeneroPelicula.THRILLER.id, this);
            Toast.makeText(MainActivity.this, "Se cargaron las peliculas de thriller", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.menu_opcion_accion) {
            mPeliculaController.getPeliculasPorGenero(GeneroPelicula.ACTION.id, this);
            Toast.makeText(MainActivity.this, "Se cargaron las peliculas de accion", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClickItem(Pelicula pelicula) {
        DetalleFragment detalleFragment = DetalleFragment.getDetalleFragment(pelicula);
        fragmentManager.beginTransaction().replace(R.id.main_contenedorDeFragment, detalleFragment).addToBackStack("back").commit();
    }

    @Override
    public void done(List<Pelicula> peliculas) {
        PeliculaFragment peliculaFragment = PeliculaFragment.getPeliculaFragment(peliculas);
        fragmentManager.beginTransaction().replace(R.id.main_contenedorDeFragment, peliculaFragment).commit();
    }
}
