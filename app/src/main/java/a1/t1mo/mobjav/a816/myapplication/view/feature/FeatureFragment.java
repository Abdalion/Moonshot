package a1.t1mo.mobjav.a816.myapplication.view.feature;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.utils.Tipo;
import a1.t1mo.mobjav.a816.myapplication.view.detalle.DetalleViewPager;
import a1.t1mo.mobjav.a816.myapplication.view.feature.pelicula.PeliculaAdapter;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 04/11/2016.
 */

public class FeatureFragment extends Fragment {
    private static final String ARGUMENT_GENERO = "Genero";
    private static final String ARGUMENT_TIPO = "Tipo";
    ListenerFeature mListener;
    RecyclerView mRecyclerView;
    Tipo.Tipo mTipo;
    Integer mGenero;

    public static FeatureFragment obtenerFragment(Tipo.Tipo tipo, Integer genero) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENT_GENERO, genero);
        bundle.putSerializable(ARGUMENT_TIPO, tipo);
        FeatureFragment fragment = new FeatureFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public FeatureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            mListener = (ListenerFeature) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        mGenero = bundle.getInt(ARGUMENT_GENERO);
        mTipo = (Tipo.Tipo) bundle.getSerializable(ARGUMENT_TIPO);
        PeliculaAdapter peliculaAdapter = new PeliculaAdapter(getContext()));
        View view = inflater.inflate(R.layout.fragment_grilla, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_grilla);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(4));
        mRecyclerView.setHasFixedSize(true);
        peliculaAdapter.setListener(mListenerPelicula);
        mRecyclerView.setAdapter(peliculaAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView mRecyclerView, int dx, int dy) {
                super.onScrolled(FeatureFragment.mRecyclerView, dx, dy);

            }
        });
        return view;
    }

    public CharSequence getTitulo() {
        return mTipo.titulo;
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space;
            } else {
                outRect.top = 0;
            }
        }
    }

    public interface ListenerFeature {
        void onClickFeature(Integer posicion, Integer genero, DetalleViewPager.Tipo tipo);
    }
}
