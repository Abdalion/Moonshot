package a1.t1mo.mobjav.a816.myapplication.view.feature;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.controller.Controller;
import a1.t1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a1.t1mo.mobjav.a816.myapplication.controller.SerieController;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;
import a1.t1mo.mobjav.a816.myapplication.utils.Tipo;
import a1.t1mo.mobjav.a816.myapplication.view.MainActivity;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 04/11/2016.
 */

public class FeatureFragment extends Fragment implements Listener<List<? extends Feature>> {
    private static final String ARGUMENT_TIPO = "Tipo";
    public static final String ARGUMENT_MENU_ID = "Menu ID";
    private Controller mController;
    private FeatureAdapter mAdapter;
    private SwipeRefreshLayout swipeRefresh;

    public static FeatureFragment getFeatureFragment(Tipo tipo, int menuId) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGUMENT_TIPO, tipo);
        bundle.putInt(ARGUMENT_MENU_ID, menuId);
        FeatureFragment fragment = new FeatureFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public FeatureFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        Tipo tipo = (Tipo) bundle.getSerializable(ARGUMENT_TIPO);
        if (tipo == Tipo.PELICULAS || tipo == Tipo.FAVORITOS_PELICULAS) {
            mController = new PeliculaController();
        } else {
            mController = new SerieController();
        }
        mController.getFeatures(bundle.getInt(ARGUMENT_MENU_ID), this);
        mAdapter = new FeatureAdapter();
        View view = inflater.inflate(R.layout.fragment_grilla, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_grilla);

        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.grilla_swipe_refresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mMainActivity.downloadFeatures(swipeRefresh);
            }
        });

        recyclerView.addItemDecoration(new SpacesItemDecoration(4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
        return view;
    }

    @Override
    public void done(List<? extends Feature> param) {
        mAdapter.setFeatures(param);
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
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space;
            } else {
                outRect.top = 0;
            }
        }
    }
}
