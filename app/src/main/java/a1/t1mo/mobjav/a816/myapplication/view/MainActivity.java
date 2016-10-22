package a1.t1mo.mobjav.a816.myapplication.view;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import a1.t1mo.mobjav.a816.myapplication.R;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = (NavigationView) findViewById(R.id.main_navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                onSelectItem(item);
                return true;
            }
        });

    }

    private void onSelectItem(MenuItem item) {
        if(item.getItemId() == R.id.menu_opcion_accion) {
            //Pedir fragment con las peliculas de accion cargadas.
            Toast.makeText(MainActivity.this, "Se cargaron las peliculas de accion", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.menu_opcion_comedia) {
            //Pedir fragment con las peliculas de comedia cargadas.
            Toast.makeText(MainActivity.this, "Se cargaron las peliculas de comedia", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.menu_opcion_terror) {
            //Pedir fragment con las peliculas de terror cargadas.
            Toast.makeText(MainActivity.this, "Se cargaron las peliculas de terror", Toast.LENGTH_SHORT).show();
        }
    }

}
