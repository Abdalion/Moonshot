package a1.t1mo.mobjav.a816.myapplication.view.detalle;

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
import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;

public class DetalleSerie extends DetalleFeature {
    private Serie mSerie;
    private DetalleFeature.Likeable mCallback;

    public DetalleSerie() {
        // Required empty public constructor
    }

    public static DetalleSerie getDetalleSerie(Serie serie, DetalleFeature.Likeable cb) {
        DetalleSerie detalleSerie = new DetalleSerie();
        detalleSerie.mSerie = serie;
        detalleSerie.mCallback = cb;
        return detalleSerie;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);

        LikeButton likeButton = (LikeButton) view.findViewById(R.id.fav_button);
        if(mSerie.isFavorito()) {
            likeButton.setLiked(true);
        }
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                mCallback.onLike(mSerie.getId());
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                mCallback.onUnlike(mSerie.getId());
            }
        });

        TextView textViewNombre = (TextView) view.findViewById(R.id.fragment_detalle_titulo);
        textViewNombre.setText(mSerie.getNombre());

        TextView textView = (TextView) view.findViewById(R.id.fragment_detalle_fechaDeEstreno);
        textView.setText(mSerie.getFechaDeEstreno());

//        TextView textViewDuracion =  (TextView) view.findViewById(R.id.fragment_detalle_duracion);
//        textViewDuracion.setText(mSerie.getDuracion().toString());

//        TextView textViewGenre =  (TextView) view.findViewById(R.id.fragment_detalle_genero);
//        textViewGenre.setText(mSerie.getGeneros());

        ImageView imageViewImagenId = (ImageView) view.findViewById(R.id.fragment_detalle_imagenId);
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