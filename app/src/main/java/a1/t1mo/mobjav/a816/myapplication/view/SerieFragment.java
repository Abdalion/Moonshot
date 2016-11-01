package a1.t1mo.mobjav.a816.myapplication.view;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.controller.SerieController;
import a1.t1mo.mobjav.a816.myapplication.model.GeneroSerie;
import a1.t1mo.mobjav.a816.myapplication.model.Serie;

public class SerieFragment extends Fragment{
    private Escuchable escuchable;
    private RecyclerView recyclerView;

    public static SerieFragment getSerieFragment (Integer genero) {
        Bundle bundle = new Bundle();
        bundle.putInt("genero", genero);
        SerieFragment fragment = new SerieFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        escuchable = (Escuchable) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final SerieAdapter serieAdapter = new SerieAdapter();
        final SerieController SerieController = new SerieController();

        Integer genero = getArguments().getInt("genero");
        if (genero == GeneroSerie.TODAS.id) {
            SerieController.getSeriesPopulares(serieAdapter);
        } else {
            SerieController.getSeriesPorGenero(genero, serieAdapter);
        }

        final View view = inflater.inflate(R.layout.fragment_serie, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_grillaDeSeries);
        recyclerView.addItemDecoration(new SpacesItemDecoration(4));
        recyclerView.setHasFixedSize(true);
        serieAdapter.setListener(escuchable);
        recyclerView.setAdapter(serieAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        return view;
    }

/*    private class ListenerRecetas implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            int posicion = recyclerView.getChildAdapterPosition(view);
            Serie peliculaClickeada = listaDeSerie.get(posicion);
            escuchable.onClickItem(peliculaClickeada);

        }
    }*/

    public interface Escuchable {
        void onClickItem(Serie serie);
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
}


