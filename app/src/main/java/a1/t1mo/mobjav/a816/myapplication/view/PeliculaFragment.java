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

public class PeliculaFragment extends Fragment implements View.OnClickListener {

    private Escuchable escuchable;

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
        final ArrayList<Pelicula> peliculas = peliculaController.getGenero(getContext(), genero);

        final View view = inflater.inflate(R.layout.fragment_pelicula, container, false);
        final RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_grillaDePeliculas);
        rv.setHasFixedSize(true);
        final PeliculasAdapter peliculasAdapter = new PeliculasAdapter(peliculas);
        peliculasAdapter.setListener(this);
        rv.setAdapter(peliculasAdapter);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        return view;
    }

    @Override
    public void onClick(View v) {
        escuchable.onClickItem(peliculas.get(rv.getChildAdapterPosition(view)));
    }

    public interface Escuchable {
        void onClickItem(Pelicula pelicula);
    }
}

