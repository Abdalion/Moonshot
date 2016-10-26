package a1.t1mo.mobjav.a816.myapplication.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.model.Pelicula;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 25/10/2016.
 */

public class PeliculasAdapter extends RecyclerView.Adapter<PeliculasAdapter.PeliculaHolder>{
    private List<Pelicula> mPeliculas;
    private PeliculaFragment.Escuchable mListener;

    public PeliculasAdapter(List<Pelicula> peliculas) {
        mPeliculas = peliculas;
    }

    public void setListener(PeliculaFragment.Escuchable listener) {
        this.mListener = listener;
    }

    @Override
    public PeliculaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_pelicula, parent, false);
        return new PeliculaHolder(view);
    }

    @Override
    public void onBindViewHolder(PeliculaHolder holder, int position) {
        holder.bindPelicula(mPeliculas.get(position));
    }

    @Override
    public int getItemCount() {
        return mPeliculas.size();
    }


    public class PeliculaHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Pelicula mPelicula;
        private ImageView mImagen;

        public PeliculaHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mImagen = (ImageView) itemView.findViewById(R.id.img_pelicula);
        }

        private void bindPelicula(Pelicula pelicula) {
            mPelicula = pelicula;
            mImagen.setImageResource(mPelicula.getImagenId());
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v);
        }
    }
}

