package a1.t1mo.mobjav.a816.myapplication.view.detalle;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.like.LikeButton;
import com.like.OnLikeListener;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.data.services.TmdbService;
import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;
import a1.t1mo.mobjav.a816.myapplication.utils.FavChange;
import a1.t1mo.mobjav.a816.myapplication.utils.TipoDeFeature;

public class DetalleSerie extends Fragment {
    private Serie mSerie;
    private FavChange mFavCallback;

    public DetalleSerie() {
        // Required empty public constructor
    }

    public static DetalleSerie getDetalle(Serie serie) {
        DetalleSerie detalleSerie = new DetalleSerie();
        detalleSerie.mSerie = serie;
        return detalleSerie;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FavChange) {
            mFavCallback = (FavChange) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_view);
        toolbar.setNavigationIcon(R.drawable.left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitleEnabled(false);


        LikeButton likeButton = (LikeButton) view.findViewById(R.id.fav_button);
        if(mSerie.isFavorito()) {
            likeButton.setLiked(true);
        }

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(FirebaseAuth.getInstance().getCurrentUser() == null) {
                    mFavCallback.favNotLogued();
                    view.cancelPendingInputEvents();
                }
            }
        });
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                if(FirebaseAuth.getInstance().getCurrentUser() != null) {
                    mFavCallback.onFavChange(mSerie.getId(), true, TipoDeFeature.SERIE);
                    Snackbar.make(getView(), "Agregado A Favoritos", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                if(FirebaseAuth.getInstance().getCurrentUser() != null) {
                    mFavCallback.onFavChange(mSerie.getId(), false, TipoDeFeature.SERIE);
                    Snackbar.make(getView(), "Eliminado De Favoritos", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        TextView textViewRating = (TextView) view.findViewById(R.id.fragment_detalle_tv_rating);
        textViewRating.setText(mSerie.getPuntajePromedio().toString());

        TextView textViewNombre = (TextView) view.findViewById(R.id.fragment_detalle_titulo);
        textViewNombre.setText(mSerie.getNombre());

        TextView textView = (TextView) view.findViewById(R.id.fragment_detalle_fechaDeEstreno);
        textView.setText(mSerie.getFechaDeEstreno());

        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.fragment_detalle_ratingBar);
        ratingBar.setRating(mSerie.getPuntajePromedio().floatValue());


//        TextView textViewDuracion =  (TextView) view.findViewById(R.id.fragment_detalle_duracion);
//        if (mSerie.get() == null){
//            textViewDuracion.setText("N/A");
//        }
//        else {
//            textViewDuracion.setText(Integer.toString(mSerie.getDuracion()));
//        }
//        TextView textViewDuracion =  (TextView) view.findViewById(R.id.fragment_detalle_duracion);
//        textViewDuracion.setText(mSerie.getDuracion().toString());

//        TextView textViewGenre =  (TextView) view.findViewById(R.id.fragment_detalle_genero);
//        textViewGenre.setText(mSerie.getGeneros());

        ImageView imageViewImagenId = (ImageView) view.findViewById(R.id.backdrop);
        Glide
                .with(getContext())
                .load(TmdbService.IMAGE_URL_W185 + mSerie.getBackdropPath())
                .fitCenter()
                .into(imageViewImagenId);

        TextView textViewLenguaje = (TextView) view.findViewById(R.id.fragment_detalle_lenguaje);
        textViewLenguaje.setText(mSerie.getLenguaje());

        TextView textViewTrama =  (TextView) view.findViewById(R.id.fragment_detalle_trama);
        textViewTrama.setText(mSerie.getResumen());

        return view;
    }

}