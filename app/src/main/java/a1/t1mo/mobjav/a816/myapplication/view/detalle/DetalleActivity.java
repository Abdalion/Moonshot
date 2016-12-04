package a1.t1mo.mobjav.a816.myapplication.view.detalle;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.controller.Controller;
import a1.t1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a1.t1mo.mobjav.a816.myapplication.controller.SerieController;
import a1.t1mo.mobjav.a816.myapplication.data.PeliculaDAO;
import a1.t1mo.mobjav.a816.myapplication.data.SerieDAO;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.utils.FavChange;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;
import a1.t1mo.mobjav.a816.myapplication.utils.Tipo;
import a1.t1mo.mobjav.a816.myapplication.utils.TipoDeFeature;
import a1.t1mo.mobjav.a816.myapplication.view.login.LoginActivity;

public class DetalleActivity extends AppCompatActivity implements Listener<List<? extends Feature>>, FavChange {
    private static final String ARGUMENT_TIPO = "Tipo";
    private static final String ARGUMENT_MENU_ID = "Menu ID";
    private static final String ARGUMENT_POSICION = "Posicion";
    private ViewPager mViewPager;
    private DetalleAdapter mAdapter;
    private Controller mController;

    public static Intent getIntent(Context context, Tipo tipo, int menuId, int posicion) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGUMENT_TIPO, tipo);
        bundle.putInt(ARGUMENT_MENU_ID, menuId);
        bundle.putInt(ARGUMENT_POSICION, posicion);
        Intent intent = new Intent(context, DetalleActivity.class);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Bundle bundle = getIntent().getExtras();
        int menuId = bundle.getInt(ARGUMENT_MENU_ID);
        int posicion = bundle.getInt(ARGUMENT_POSICION);
        Tipo tipo = (Tipo) bundle.getSerializable(ARGUMENT_TIPO);

        //todo: Tagged controller para ahorrarnos crear un objeto?
        if (tipo == Tipo.PELICULAS) {
            mController = new PeliculaController();
            mAdapter = new DetallePeliculaAdapter(getSupportFragmentManager());
        } else {
            mController = new SerieController();
            mAdapter = new DetalleSerieAdapter(getSupportFragmentManager());
        }

        if (menuId == R.id.menu_favoritos_opcion_series || menuId == R.id.menu_favoritos_opcion_peliculas) {
            mAdapter.setFeatures(mController.getFavoritos());
        } else {
            mController.getFeatures(menuId, this);
        }

        mViewPager = (ViewPager) findViewById(R.id.viewpager_detalle);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(posicion);
    }

    @Override
    public void onFavChange(int id, boolean isFav) {
        if(mController instanceof PeliculaController) {
            ((PeliculaController) mController).setFavorito(id, isFav);
        }
        else {
            ((SerieController) mController).setFavorito(id, isFav);
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

    @Override
    public void done(List<? extends Feature> param) {
        mAdapter.setFeatures(param);
    }
}
