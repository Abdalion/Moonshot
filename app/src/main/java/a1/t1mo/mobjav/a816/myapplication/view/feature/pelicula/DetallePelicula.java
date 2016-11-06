package a1.t1mo.mobjav.a816.myapplication.view.feature.pelicula;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.data.services.TmdbService;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.view.feature.DetalleFeature;

public class DetallePelicula extends DetalleFeature{

    private Pelicula pelicula;

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

//        Bundle bundle = getArguments();

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

}
