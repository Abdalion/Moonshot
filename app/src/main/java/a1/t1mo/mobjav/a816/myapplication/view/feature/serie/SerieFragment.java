package a1.t1mo.mobjav.a816.myapplication.view.feature.serie;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.view.feature.FeatureFragment;

public class SerieFragment extends FeatureFragment {
    private ListenerFeature mListenerSerie;
    private RecyclerView recyclerView;

    public static SerieFragment obtenerFragment(Integer genero) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENT_GENERO, genero);
        SerieFragment fragment = new SerieFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            mListenerSerie = (ListenerFeature) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        SerieAdapter serieAdapter = new SerieAdapter(getContext(), bundle.getInt(ARGUMENT_GENERO));
        View view = inflater.inflate(R.layout.fragment_grilla, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_grilla);
        recyclerView.addItemDecoration(new SpacesItemDecoration(4));
        recyclerView.setHasFixedSize(true);
        serieAdapter.setListener(mListenerSerie);
        recyclerView.setAdapter(serieAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        return view;
    }

    public CharSequence getTitulo(){
        return "Series";
    }
}


