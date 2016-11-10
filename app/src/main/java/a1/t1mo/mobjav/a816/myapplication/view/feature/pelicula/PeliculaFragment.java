package a1.t1mo.mobjav.a816.myapplication.view.feature.pelicula;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.view.feature.FeatureFragment;

public class PeliculaFragment extends FeatureFragment {
    private RecyclerView recyclerView;
    private ListenerFeature mListenerPelicula;

    public static PeliculaFragment obtenerFragment(Integer genero) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENT_GENERO, genero);
        PeliculaFragment fragment = new PeliculaFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            mListenerPelicula = (ListenerFeature) context;
        }
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
        peliculaAdapter.setListener(mListenerPelicula);
        recyclerView.setAdapter(peliculaAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        return view;
    }

    public CharSequence getTitulo(){
        return "Peliculas";
    }

    public interface ListenerPelicula {
        void onClickPelicula(Integer posicion, Integer genero);
    }
}

