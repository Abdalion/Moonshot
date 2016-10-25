package a1.t1mo.mobjav.a816.myapplication.view;

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

public class PeliculaFragment extends Fragment {

    public static PeliculaFragment getPeliculaFragment(String genero) {
        Bundle bundle = new Bundle();
        bundle.putString("genero", genero);
        PeliculaFragment fragment = new PeliculaFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String genero = getArguments().getString("genero");
        PeliculaController peliculaController = new PeliculaController();
        ArrayList<Pelicula> peliculas = peliculaController.getGenero(getContext(), genero);

        View view = inflater.inflate(R.layout.fragment_pelicula, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_grillaDePeliculas);
        rv.setHasFixedSize(true);
        rv.setAdapter(new PeliculasAdapter(peliculas));
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        return view;
    }

}
