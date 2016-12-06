package a1.t1mo.mobjav.a816.myapplication.view.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.controller.Controller;
import a1.t1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a1.t1mo.mobjav.a816.myapplication.controller.SerieController;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;
import a1.t1mo.mobjav.a816.myapplication.utils.Tipo;
import a1.t1mo.mobjav.a816.myapplication.view.detalle.DetalleActivity;

import static a1.t1mo.mobjav.a816.myapplication.utils.UserActions.isUserLogged;

/**
 * Created by dh-mob-tt on 30/11/16.
 */
public class FavoritosFragment extends GridFragment
        implements View.OnClickListener, Listener<List<? extends Feature>> {

    private static final String STATE_MENU_ID = "Menu ID";
    private Controller mController;
    private RecyclerView mRecyclerView;
    private FavoritosAdapter mAdapter;
    private int mMenuID;

    public FavoritosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grilla, container, false);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.grilla_swipe_refresh);
        swipeRefreshLayout.setEnabled(false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_grilla);

        if(isUserLogged()) {
            if (savedInstanceState != null) {
                mMenuID = savedInstanceState.getInt(STATE_MENU_ID);
            } else {
                mMenuID = R.id.menu_favoritos_opcion_peliculas;
            }

            mRecyclerView.addItemDecoration(new SpacesItemDecoration(4));
            mRecyclerView.setHasFixedSize(true);
            mAdapter = new FavoritosAdapter(this);
            mRecyclerView.setAdapter(mAdapter);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
            mRecyclerView.setLayoutManager(gridLayoutManager);

            mController = getController(mMenuID);
            mController.getFavoritos(this);
        }
        else {
            mRecyclerView.setVisibility(View.GONE);
            TextView textView = (TextView) view.findViewById(R.id.grilla_texto_favoritos);
            textView.setVisibility(View.VISIBLE);
        }

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
                mController.getPaginaActual(), mRecyclerView.getChildAdapterPosition(view));
        startActivity(intent);
    }

    public void redraw(int menuId) {
        mMenuID = menuId;
        mController = getController(mMenuID);
        mController.getFavoritos(this);
    }

    private Tipo getTipo() {
        return (mMenuID == R.id.menu_favoritos_opcion_peliculas ? Tipo.PELICULAS : Tipo.SERIES);
    }

    public Controller getController(int menuID) {
        if (menuID == R.id.menu_favoritos_opcion_peliculas) {
            return new PeliculaController(getContext());
        } else {
            return new SerieController(getContext());
        }
    }

    @Override
    public void done(List<? extends Feature> param) {
        mAdapter.setFeatures(param);
    }
}