package a1.t1mo.mobjav.a816.myapplication.view.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.controller.Controller;
import a1.t1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a1.t1mo.mobjav.a816.myapplication.controller.SerieController;
import a1.t1mo.mobjav.a816.myapplication.utils.Tipo;
import a1.t1mo.mobjav.a816.myapplication.view.detalle.DetalleActivity;

/**
 * Created by dh-mob-tt on 30/11/16.
 */
public class FavoritosFragment extends GridFragment implements View.OnClickListener {
    private static final String STATE_MENU_ID = "Menu ID";
    private Controller mController;
    private RecyclerView mRecyclerView;
    private FavoritosAdapter mAdapter;
    private int mMenuID;
    private SwipeRefreshLayout mSwipeRefresh;

    public FavoritosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mMenuID = savedInstanceState.getInt(STATE_MENU_ID);
        }
        else {
            mMenuID = R.id.menu_favoritos_opcion_peliculas;
        }

        View view = inflater.inflate(R.layout.fragment_grilla, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_grilla);

        mController = getController(mMenuID);
        mAdapter = new FavoritosAdapter(this, mController.getFavoritos());

        mRecyclerView.addItemDecoration(new SpacesItemDecoration(4));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.grilla_swipe_refresh);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mController.getFavoritos();
                mSwipeRefresh.setRefreshing(false);
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_MENU_ID, mMenuID);
    }

    @Override
    public void onClick(View view) {
        Intent intent = DetalleActivity.getIntent(getContext(), getTipo(), mMenuID,
                mRecyclerView.getChildAdapterPosition(view));
        startActivity(intent);
    }

    public void redraw(int menuId) {
        mMenuID = menuId;
        mController = getController(mMenuID);
        mAdapter.setFeatures(mController.getFavoritos());
    }

    private Tipo getTipo() {
        return (mMenuID == R.id.menu_favoritos_opcion_peliculas ? Tipo.PELICULAS : Tipo.SERIES);
    }

    public Controller getController(int menuID) {
        if (menuID == R.id.menu_favoritos_opcion_peliculas) {
            return new PeliculaController();
        } else {
            return new SerieController();
        }
    }
}