package a1.t1mo.mobjav.a816.myapplication.view.feature.pelicula;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.view.feature.FeatureFragment;

public class PeliculaFragment extends FeatureFragment {
    private Escuchable escuchable;
    private RecyclerView recyclerView;

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
        PeliculaAdapter peliculaAdapter = new PeliculaAdapter(getContext(), bundle.getInt(ARGUMENT_GENERO));
        View view = inflater.inflate(R.layout.fragment_grilla, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_grilla);
        recyclerView.addItemDecoration(new SpacesItemDecoration(4));
        recyclerView.setHasFixedSize(true);
        peliculaAdapter.setListener(escuchable);
        recyclerView.setAdapter(peliculaAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        return view;
    }

    public CharSequence getTitulo(){
        return "Peliculas";
    }
}

