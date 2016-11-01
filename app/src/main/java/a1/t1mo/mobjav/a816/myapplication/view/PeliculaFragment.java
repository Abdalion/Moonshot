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

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a1.t1mo.mobjav.a816.myapplication.model.GeneroPelicula;
import a1.t1mo.mobjav.a816.myapplication.model.Pelicula;

public class PeliculaFragment extends Fragment{
    private List<Pelicula> mPeliculas;
    private Escuchable escuchable;
    private RecyclerView recyclerView;

    public static PeliculaFragment getPeliculaFragment(List<Pelicula> peliculas) {
        PeliculaFragment fragment = new PeliculaFragment();
        fragment.mPeliculas = peliculas;
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

        final PeliculaAdapter peliculaAdapter = new PeliculaAdapter();
        final PeliculaController peliculaController = new PeliculaController();

        Integer genero = getArguments().getInt("genero");
        if (genero == GeneroPelicula.TODAS.id) {
            peliculaController.getPeliculasPopulares(peliculaAdapter);
        } else {
            peliculaController.getPeliculasPorGenero(genero, peliculaAdapter);
        }

        final View view = inflater.inflate(R.layout.fragment_pelicula, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_grillaDePeliculas);
        recyclerView.addItemDecoration(new SpacesItemDecoration(4));
        recyclerView.setHasFixedSize(true);
        peliculaAdapter.setListener(escuchable);
        recyclerView.setAdapter(peliculaAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        return view;
    }

/*    private class ListenerRecetas implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            int posicion = recyclerView.getChildAdapterPosition(view);
            Pelicula peliculaClickeada = listaDePeliculas.get(posicion);
            escuchable.onClickItem(peliculaClickeada);

        }
    }*/

    public interface Escuchable {
        void onClickItem(Pelicula pelicula);
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

