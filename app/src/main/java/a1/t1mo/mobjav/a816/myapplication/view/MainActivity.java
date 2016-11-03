package a1.t1mo.mobjav.a816.myapplication.view;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import retrofit2.http.GET;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 */

public class MainActivity extends AppCompatActivity implements PeliculaFragment.Escuchable {
    FragmentManager fragmentManager;
    AdapterViewPagerFragment adapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        viewPagerSetup();
        navigationViewSetup();
        drawerButtonListener();

    }

    private void navigationViewSetup() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.main_navigationView);
        navigationView.setCheckedItem(R.id.menu_opcion_todas);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                onSelectItem(item);
                return true;
            }
        });
    }

    private void viewPagerSetup() {
        adapter = new AdapterViewPagerFragment(fragmentManager);
        viewPager = (ViewPager) findViewById(R.id.main_viewPager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                GeneroManager generoManager = GeneroManager.getGeneroManager();
                if(position == 0) {
                    generoManager.setPeliculaOSerie("PELICULA");
                }
                else
                    generoManager.setPeliculaOSerie("SERIE");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        adapter.changeCategory(GeneroPelicula.TODAS.id);
    }

    private void drawerButtonListener() {
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

        GeneroManager generoManager = GeneroManager.getGeneroManager();
        generoManager.getPeliculaOSerie();

        if(item.getItemId() == R.id.menu_opcion_todas) {
            Toast.makeText(MainActivity.this, "Todas las peliculas", Toast.LENGTH_SHORT).show();
            Log.d("DEBUGGER", "LLEGADO A SELECCIONAR TODAS");
            adapter.changeCategory(GeneroPelicula.TODAS.id);
        }
        else if(item.getItemId() == R.id.menu_opcion_accion) {
            Toast.makeText(MainActivity.this, "Peliculas de accion", Toast.LENGTH_SHORT).show();
            Log.d("DEBUGGER", "LLEGADO A SELECCIONAR ACCION");
            adapter.changeCategory(GeneroPelicula.ACTION.id);
        }
        adapter.notifyDataSetChanged();
    }

    private void onSwipe() {

    }

//        if(item.getItemId() == R.id.menu_opcion_todas) {
//            crearFragmentPeliculas(GeneroPelicula.TODAS.id);
//            Toast.makeText(MainActivity.this, "Se cargaron todas las peliculas", Toast.LENGTH_SHORT).show();
//        }
//        else if(item.getItemId() == R.id.menu_opcion_drama) {
//            crearFragmentPeliculas(GeneroPelicula.DRAMA.id);
//            Toast.makeText(MainActivity.this, "Se cargaron las peliculas de drama", Toast.LENGTH_SHORT).show();
//        }
//        else if(item.getItemId() == R.id.menu_opcion_thriller) {
//            crearFragmentPeliculas(GeneroPelicula.THRILLER.id);
//            Toast.makeText(MainActivity.this, "Se cargaron las peliculas de thriller", Toast.LENGTH_SHORT).show();
//        }
//        else if(item.getItemId() == R.id.menu_opcion_accion) {
//            crearFragmentPeliculas(GeneroPelicula.ACTION.id);
//            Toast.makeText(MainActivity.this, "Se cargaron las peliculas de accion", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    public void onClickItem(Pelicula pelicula) {
        DetalleFragment detalleFragment = DetalleFragment.getDetalleFragment(pelicula);
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_contenedorDeFragment, detalleFragment)
                .addToBackStack("back")
                .commit();
    }

//    public void crearFragmentPeliculas(Integer genero) {
//        PeliculaFragment peliculaFragment = PeliculaFragment.obtenerFragment(genero);
//        fragmentManager
//                .beginTransaction()
//                .replace(R.id.main_contenedorDeFragment, peliculaFragment)
//                .commit();
//    }
}
