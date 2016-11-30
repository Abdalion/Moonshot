package a1.t1mo.mobjav.a816.myapplication.view.favoritos;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import a1.t1mo.mobjav.a816.myapplication.view.feature.FeatureAdapter;

/**
 * Created by dh-mob-tt on 30/11/16.
 */
public class FavoritosFragment extends Fragment implements Listener<List<? extends Feature>> {
    private static final String STATE_MENU_ID = "Menu ID";
    private PeliculaController mPeliculaController;
    private SerieController mSerieController;
    private FeatureAdapter mAdapter;
    private int mMenuID;
    private SwipeRefreshLayout swipeRefresh;

    public FavoritosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mMenuID = savedInstanceState.getInt(STATE_MENU_ID);
        }

        mController.getFeatures(mMenuID, this);
        mAdapter = new FeatureAdapter();
        View view = inflater.inflate(R.layout.fragment_grilla, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_grilla);

        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.grilla_swipe_refresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mController.getFeatures(mMenuID, FeatureFragment.this);
                swipeRefresh.setRefreshing(false);
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_MENU_ID, mMenuID);
    }

    @Override
    public void done(List<? extends Feature> param) {
        mAdapter.setFeatures(param);
    }

    public void redraw(int menuId) {
        mMenuID = menuId;
        mController.getFeatures(mMenuID, this);
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