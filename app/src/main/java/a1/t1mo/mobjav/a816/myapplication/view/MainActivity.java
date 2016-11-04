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

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.GeneroPelicula;
import a1.t1mo.mobjav.a816.myapplication.model.serie.GeneroSerie;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;
import a1.t1mo.mobjav.a816.myapplication.view.feature.pelicula.DetalleFragment;
import a1.t1mo.mobjav.a816.myapplication.view.feature.pelicula.PeliculaFragment;
import a1.t1mo.mobjav.a816.myapplication.view.feature.serie.DetalleSerie;
import a1.t1mo.mobjav.a816.myapplication.view.feature.serie.SerieFragment;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 */

public class MainActivity extends AppCompatActivity
        implements PeliculaFragment.Escuchable, SerieFragment.Escuchable {
    FragmentManager fragmentManager;
    AdapterViewPagerFragment adapter;
    ViewPager viewPager;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        navigationViewSetup();
        viewPagerSetup();
        drawerButtonListener();
    }

    private void navigationViewSetup() {
        navigationView = (NavigationView) findViewById(R.id.main_navigationView);
        navigationView.setCheckedItem(R.id.menu_peliculas_opcion_todas);

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
        adapter.changeCategory(GeneroPelicula.TODAS.id, GeneroSerie.TODAS.id);
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
                else {
                    generoManager.setPeliculaOSerie("SERIE");
                }

                generoManager.updateNavigationMenu(navigationView);
                Toast.makeText(MainActivity.this, generoManager.getPeliculaOSerie(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
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
        generoManager.onNavigationGenreClick(item.getItemId(), adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onClickItem(Pelicula pelicula) {
        DetalleFragment detalleFragment = DetalleFragment.getDetalleFragment(pelicula);
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_contenedorDeFragment, detalleFragment)
                .addToBackStack("back")
                .commit();
    }

    @Override
    public void onClickItem(Serie serie) {
        DetalleSerie detalleSerie = DetalleSerie.getDetalleSerie(serie);
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_contenedorDeFragment, detalleSerie)
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
