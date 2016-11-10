package a1.t1mo.mobjav.a816.myapplication.view.detalle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a1.t1mo.mobjav.a816.myapplication.R;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 09/11/2016.
 */

public class DetalleViewPager extends Fragment implements DetalleAdapterCallback {
    private Integer mPosicion;
    private Integer mGenero;
    private Tipo mTipo;
    private ViewPager mViewPager;
    public static final String ARGUMENT_POSICION = "Posicion";
    public static final String ARGUMENT_GENERO = "Genero";
    public static final String ARGUMENT_TIPO = "Tipo";
    public static final Integer FAVORITOS_FLAG = 0;
    public enum Tipo {PELICULA, SERIE;}

    public DetalleViewPager() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        mPosicion = bundle.getInt(ARGUMENT_POSICION);
        mGenero = bundle.getInt(ARGUMENT_GENERO);
        mTipo = (Tipo) bundle.getSerializable(ARGUMENT_TIPO);

        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        FragmentManager fragmentManager = getChildFragmentManager();
        if (mGenero == FAVORITOS_FLAG) {
            mViewPager.setAdapter(new DetalleFavoritoAdapter(fragmentManager, getContext(), mTipo));
            mViewPager.setCurrentItem(mPosicion);
        } else {
            if (mTipo == Tipo.PELICULA) {
                mViewPager.setAdapter(new DetallePeliculaAdapter(fragmentManager, this, getContext(), mGenero));
            } else {
                mViewPager.setAdapter(new DetalleSerieAdapter(fragmentManager, this, getContext(), mGenero));
            }
        }
        return view;
    }

    @Override
    public void onFinish() {
        mViewPager.setCurrentItem(mPosicion);
        mViewPager.getAdapter().notifyDataSetChanged();
    }
}
