package a1.t1mo.mobjav.a816.myapplication.view.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.controller.Controller;
import a1.t1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a1.t1mo.mobjav.a816.myapplication.controller.SerieController;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;
import a1.t1mo.mobjav.a816.myapplication.utils.Tipo;
import a1.t1mo.mobjav.a816.myapplication.view.detalle.DetalleActivity;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 04/11/2016.
 */

public class FeatureFragment extends GridFragment implements Listener<List<? extends Feature>>, View.OnClickListener {
    private static final String ARGUMENT_TIPO = "Tipo";
    private static final String STATE_MENU_ID = "Menu ID";
    private Controller mController;
    private RecyclerView mRecyclerView;
    private FeatureAdapter mAdapter;
    private int mMenuID;
    private Tipo mTipo;
    private SwipeRefreshLayout mSwipeRefresh;
    private boolean mLoading = false;
    private int mPastVisiblesItems, mVisibleItemCount, mTotalItemCount;

    public static FeatureFragment getFeatureFragment(Tipo tipo) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGUMENT_TIPO, tipo);
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

        if (savedInstanceState != null) {
            mMenuID = savedInstanceState.getInt(STATE_MENU_ID);
            mTipo = (Tipo) savedInstanceState.getSerializable(ARGUMENT_TIPO);
        } else {
            Bundle bundle = getArguments();
            mTipo = (Tipo) bundle.getSerializable(ARGUMENT_TIPO);
        }

        if (mTipo == Tipo.PELICULAS) {
            mController = new PeliculaController(getContext());
            mMenuID = R.id.menu_peliculas_opcion_todas;
        } else {
            mController = new SerieController(getContext());
            mMenuID = R.id.menu_series_opcion_todas;
        }

        mController.getFeatures(mMenuID, this);
        mAdapter = new FeatureAdapter(this);
        View view = inflater.inflate(R.layout.fragment_grilla, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_grilla);

        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.grilla_swipe_refresh);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefresh.setRefreshing(true);
                mController.getFeatures(mMenuID, FeatureFragment.this);
            }
        });

        mRecyclerView.addItemDecoration(new SpacesItemDecoration(4));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    mVisibleItemCount = gridLayoutManager.getChildCount();
                    mTotalItemCount = gridLayoutManager.getItemCount();
                    mPastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition();
                    if (!mLoading && !mController.isLastPage()) {
                        if ((mVisibleItemCount + mPastVisiblesItems) >= mTotalItemCount) {
                            mLoading = true;
                            Log.v("...", "Last Item Wow !");
                            mController.getNextPage(mMenuID, FeatureFragment.this);
                        }
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_MENU_ID, mMenuID);
        outState.putSerializable(ARGUMENT_TIPO, mTipo);
    }

    @Override
    public void done(List<? extends Feature> param) {
        mAdapter.setFeatures(param);
        mLoading = false;
        if (mSwipeRefresh.isRefreshing()) mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void onClick(View view) {
        Intent intent = DetalleActivity.getIntent(getContext(), mTipo, mMenuID,
                mRecyclerView.getChildAdapterPosition(view));
        startActivity(intent);
    }

    public void redraw(int menuId) {
        mMenuID = menuId;
        mController.getFeatures(mMenuID, this);
    }
}
