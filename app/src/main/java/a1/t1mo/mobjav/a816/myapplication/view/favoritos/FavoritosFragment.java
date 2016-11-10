package a1.t1mo.mobjav.a816.myapplication.view.favoritos;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.view.feature.FeatureFragment;

public class FavoritosFragment extends FeatureFragment {
    RecyclerView recyclerView;
    ListenerFeature mListenerFavoritos;

    public FavoritosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            mListenerFavoritos = (ListenerFeature) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        FavoritosAdapter favoritosAdapter = new FavoritosAdapter(getContext(), bundle.getInt(ARGUMENT_GENERO));
        View view = inflater.inflate(R.layout.fragment_grilla, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_grilla);
        recyclerView.addItemDecoration(new SpacesItemDecoration(4));
        recyclerView.setHasFixedSize(true);
        favoritosAdapter.setListener(mListenerFavoritos);
        recyclerView.setAdapter(favoritosAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        return view;
    }

    public static FavoritosFragment obtenerFragment(Integer tipo) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENT_GENERO, tipo);
        FavoritosFragment fragment = new FavoritosFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public CharSequence getTitulo() {
        return "Favoritos";
    }
}
