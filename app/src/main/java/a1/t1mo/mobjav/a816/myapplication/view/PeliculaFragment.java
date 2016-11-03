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
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;

public class PeliculaFragment extends Fragment {
    private Escuchable escuchable;
    private RecyclerView recyclerView;
    public static final String ARGUMENT_GENERO = "Genero de Peliculas";

    public static PeliculaFragment obtenerFragment(Integer genero) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENT_GENERO, genero);
        PeliculaFragment fragment = new PeliculaFragment();
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

        Bundle bundle = getArguments();
        PeliculaAdapter peliculaAdapter = new PeliculaAdapter(bundle.getInt(ARGUMENT_GENERO));
        View view = inflater.inflate(R.layout.fragment_pelicula, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_grillaDePeliculas);
        recyclerView.addItemDecoration(new SpacesItemDecoration(3));
        recyclerView.setHasFixedSize(true);
        peliculaAdapter.setListener(escuchable);
        recyclerView.setAdapter(peliculaAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        return view;
    }

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
    public CharSequence getTitulo(){
        return "Peliculas";
    }
}

