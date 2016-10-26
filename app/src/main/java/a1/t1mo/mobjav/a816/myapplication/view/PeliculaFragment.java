package a1.t1mo.mobjav.a816.myapplication.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a1.t1mo.mobjav.a816.myapplication.model.Pelicula;

public class PeliculaFragment extends Fragment{
    private Escuchable escuchable;
    private RecyclerView recyclerView;
    private ArrayList<Pelicula> listaDePeliculas;

    public static PeliculaFragment getPeliculaFragment(String genero) {
        Bundle bundle = new Bundle();
        bundle.putString("genero", genero);
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
        String genero = getArguments().getString("genero");
        final PeliculaController peliculaController = new PeliculaController();
        listaDePeliculas = peliculaController.getGenero(getContext(), genero);

        final View view = inflater.inflate(R.layout.fragment_pelicula, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_grillaDePeliculas);
        recyclerView.setHasFixedSize(true);
        final PeliculasAdapter peliculasAdapter = new PeliculasAdapter(listaDePeliculas);
        peliculasAdapter.setListener(escuchable);
        recyclerView.setAdapter(peliculasAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        return view;
    }

    private class ListenerRecetas implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            int posicion = recyclerView.getChildAdapterPosition(view);
            Pelicula peliculaClickeada = listaDePeliculas.get(posicion);
            escuchable.onClickItem(peliculaClickeada);

        }
    }

    public interface Escuchable {
        void onClickItem(Pelicula pelicula);
    }
}

