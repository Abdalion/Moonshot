package a1.t1mo.mobjav.a816.myapplication.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a1.t1mo.mobjav.a816.myapplication.model.Pelicula;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleFragment extends Fragment {


    public DetalleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);

        PeliculaController peliculaController = new PeliculaController();

        Bundle bundle = getArguments();

        String assets = bundle.getString("assets");
        String generos = bundle.getString("generos");
        Integer imgIds = bundle.getInt("imgIds");

        Pelicula pelicula;
        pelicula = peliculaController.getPelicula(getActivity(),assets,imgIds,generos);

        TextView textViewNombre =  (TextView) view.findViewById(R.id.fragment_detalle_titulo);
        textViewNombre.setText(pelicula.getTitulo());

/*        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        TextView textView = (TextView) view.findViewById(R.id.fragment_detalle_fechaDeEstreno);
        textView.setText(df.format(pelicula.getFechaDeEstreno()));*/


        TextView textView1Duracion =  (TextView) view.findViewById(R.id.fragment_detalle_duracion);
        textView1Duracion.setText(pelicula.getDuracion());

        TextView textViewGenre =  (TextView) view.findViewById(R.id.fragment_detalle_genero);
        textViewGenre.setText(pelicula.getGeneros());

        ImageView imageViewImagenId =  (ImageView) view.findViewById(R.id.fragment_detalle_imagenId);
        imageViewImagenId.setImageResource(pelicula.getImagenId());

        TextView textViewLenguaje =  (TextView) view.findViewById(R.id.fragment_detalle_lenguaje);
        textViewLenguaje.setText(pelicula.getLenguaje());

        TextView textViewDirector =  (TextView) view.findViewById(R.id.fragment_detalle_director);
        textViewDirector.setText(pelicula.getDirector());

        TextView textViewActores =  (TextView) view.findViewById(R.id.fragment_detalle_actores);
        textViewActores.setText(pelicula.getActores());

        TextView textViewTrama =  (TextView) view.findViewById(R.id.fragment_detalle_trama);
        textViewTrama.setText(pelicula.getTrama());



        return view;

    }

}
