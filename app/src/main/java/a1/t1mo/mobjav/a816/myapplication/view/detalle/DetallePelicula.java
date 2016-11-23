package a1.t1mo.mobjav.a816.myapplication.view.detalle;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.like.LikeButton;
import com.like.OnLikeListener;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.data.services.TmdbService;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.utils.FavChange;
import a1.t1mo.mobjav.a816.myapplication.utils.TipoDeFeature;

public class DetallePelicula extends Fragment {
    private Pelicula mPelicula;
    private FavChange mFavCallback;

    public static DetallePelicula getDetalle(Pelicula pelicula) {
        DetallePelicula detallePelicula = new DetallePelicula();
        detallePelicula.mPelicula = pelicula;
        return detallePelicula;
    }

    public DetallePelicula() {
        // Required empty public constructor
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
        LikeButton likeButton = (LikeButton) view.findViewById(R.id.fav_button);
        if(mPelicula.isFavorito()) {
            likeButton.setLiked(true);
        }
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                mFavCallback.onFavChange(mPelicula.getId(), true, TipoDeFeature.PELICULA);
                Snackbar.make(getView(), "Agregado a favoritos", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                mFavCallback.onFavChange(mPelicula.getId(), false, TipoDeFeature.PELICULA);
                Snackbar.make(getView(), "Eliminado de favoritos", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        TextView textViewRating = (TextView) view.findViewById(R.id.fragment_detalle_tv_rating);
        textViewRating.setText(mPelicula.getPuntajePromedio().toString());

        TextView textViewNombre = (TextView) view.findViewById(R.id.fragment_detalle_titulo);
        textViewNombre.setText(mPelicula.getTitulo());

        TextView textView = (TextView) view.findViewById(R.id.fragment_detalle_fechaDeEstreno);
        textView.setText(mPelicula.getFechaDeEstreno());

        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.fragment_detalle_ratingBar);
        ratingBar.setRating(mPelicula.getPuntajePromedio().floatValue());


        TextView textViewDuracion =  (TextView) view.findViewById(R.id.fragment_detalle_duracion);
        if (mPelicula.getDuracion() == null){
            textViewDuracion.setText("N/A");
        }
        else {
            textViewDuracion.setText(Integer.toString(mPelicula.getDuracion()));
        }
//        TextView textViewGenre =  (TextView) view.findViewById(R.id.fragment_detalle_genero);
//        textViewGenre.setText(mPelicula.getGeneros());

        ImageView imageViewImagenId = (ImageView) view.findViewById(R.id.fragment_detalle_imagenId);
        Glide
            .with(getContext())
            .load(TmdbService.IMAGE_URL_W300 + mPelicula.getBackdropPath())
            .fitCenter()
            .into(imageViewImagenId);

        TextView textViewLenguaje = (TextView) view.findViewById(R.id.fragment_detalle_lenguaje);
        textViewLenguaje.setText(mPelicula.getLenguaje());

        TextView textViewTrama =  (TextView) view.findViewById(R.id.fragment_detalle_trama);
        textViewTrama.setText(mPelicula.getResumen());

//        TextView textViewHomePage =  (TextView) view.findViewById(R.id.fragment_detalle_homepage);
//        textViewHomePage.setText(mPelicula.getImdbId());

        return view;
    }
}
