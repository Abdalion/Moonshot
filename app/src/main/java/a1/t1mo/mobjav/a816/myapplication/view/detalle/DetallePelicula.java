package a1.t1mo.mobjav.a816.myapplication.view.detalle;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.like.LikeButton;
import com.like.OnLikeListener;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.data.services.TmdbService;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.view.MainActivity;

public class DetallePelicula extends DetalleFeature {
    private Pelicula pelicula;
    private DetalleFeature.Likeable activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (Likeable) activity;
    }

    public DetallePelicula() {
        // Required empty public constructor
    }

    public static DetallePelicula getDetalleFragment(Pelicula unaPelicula) {
        DetallePelicula detallePelicula = new DetallePelicula();
        detallePelicula.pelicula = unaPelicula;
        return detallePelicula;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);
        LikeButton likeButton = (LikeButton) view.findViewById(R.id.fav_button);
        if(isFavorito()) {
            likeButton.setLiked(true);
        }
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                activity.onLike(pelicula.getId());
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                activity.onUnlike(pelicula.getId());
            }
        });

        TextView textViewNombre = (TextView) view.findViewById(R.id.fragment_detalle_titulo);
        textViewNombre.setText(pelicula.getTitulo());

        TextView textView = (TextView) view.findViewById(R.id.fragment_detalle_fechaDeEstreno);
        textView.setText(pelicula.getFechaDeEstreno());
//
//        TextView textViewDuracion =  (TextView) view.findViewById(R.id.fragment_detalle_duracion);
//        textViewDuracion.setText(Integer.toString(pelicula.getDuracion()));

//        TextView textViewGenre =  (TextView) view.findViewById(R.id.fragment_detalle_genero);
//        textViewGenre.setText(pelicula.getGeneros());

        ImageView imageViewImagenId = (ImageView) view.findViewById(R.id.fragment_detalle_imagenId);
        Glide
            .with(getContext())
            .load(TmdbService.IMAGE_URL_W300 + pelicula.getBackdropPath())
            .fitCenter()
            .into(imageViewImagenId);

        TextView textViewLenguaje = (TextView) view.findViewById(R.id.fragment_detalle_lenguaje);
        textViewLenguaje.setText(pelicula.getLenguaje());

        TextView textViewTrama =  (TextView) view.findViewById(R.id.fragment_detalle_trama);
        textViewTrama.setText(pelicula.getResumen());

//        TextView textViewHomePage =  (TextView) view.findViewById(R.id.fragment_detalle_homepage);
//        textViewHomePage.setText(pelicula.getImdbId());

        return view;
    }

    private Boolean isFavorito() {
        return pelicula.isFavorito();
    }

}
