package a1.t1mo.mobjav.a816.myapplication.view.detalle;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.like.LikeButton;
import com.like.OnLikeListener;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.data.services.TmdbService;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;

public class DetallePelicula extends DetalleFeature {
    private Pelicula mPelicula;
    private DetalleFeature.Likeable mCallback;

    public DetallePelicula() {
        // Required empty public constructor
    }

    public static DetallePelicula getDetallePelicula(Pelicula pelicula, DetalleFeature.Likeable cb) {
        DetallePelicula detallePelicula = new DetallePelicula();
        detallePelicula.mPelicula = pelicula;
        detallePelicula.mCallback = cb;
        return detallePelicula;
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
                mCallback.onLike(mPelicula.getId());
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                mCallback.onUnlike(mPelicula.getId());
            }
        });

        TextView textViewNombre = (TextView) view.findViewById(R.id.fragment_detalle_titulo);
        textViewNombre.setText(mPelicula.getTitulo());

        TextView textView = (TextView) view.findViewById(R.id.fragment_detalle_fechaDeEstreno);
        textView.setText(mPelicula.getFechaDeEstreno());
//
//        TextView textViewDuracion =  (TextView) view.findViewById(R.id.fragment_detalle_duracion);
//        textViewDuracion.setText(Integer.toString(mPelicula.getDuracion()));

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
