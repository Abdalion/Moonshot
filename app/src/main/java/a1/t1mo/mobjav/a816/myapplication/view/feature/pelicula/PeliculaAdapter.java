package a1.t1mo.mobjav.a816.myapplication.view.feature.pelicula;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a1.t1mo.mobjav.a816.myapplication.data.services.TmdbService;
import a1.t1mo.mobjav.a816.myapplication.model.Genre;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.GeneroPelicula;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 25/10/2016.
 */

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaHolder>
        implements Listener<List<Pelicula>>{

    private List<Pelicula> mPeliculas;
    private PeliculaController mPeliculaController;
    private PeliculaFragment.Escuchable mListener;
    private final static int FADE_DURATION = 300;

    public PeliculaAdapter(Context context, Integer genero) {
        mPeliculaController = new PeliculaController(context);
        if (genero == Genre.PELICULA_ID.get(R.id.menu_peliculas_opcion_todas)) {
            mPeliculaController.getPeliculasPopulares(this);
        } else {
            mPeliculaController.getPeliculasPorGenero(Genre.PELICULA_ID.get(genero), this);
        }
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
        if (mPeliculas != null && !mPeliculas.isEmpty()) {
            holder.bindPelicula(mPeliculas.get(position));
            setScaleAnimation(holder.itemView);
        }

    }
    //SI LAS PELIS TARDAN MUCHO EN CARGAR ESTO SE SACA.
    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    @Override
    public int getItemCount() {
        return mPeliculas == null ? 0 : mPeliculas.size();
    }

    @Override
    public void done(List<Pelicula> peliculas) {
        mPeliculas = peliculas;
        notifyDataSetChanged();
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
            Glide
                .with(mImagen.getContext())
                .load(TmdbService.IMAGE_URL_W154 + pelicula.getPosterPath())
                .fitCenter()
                .into(mImagen);
        }

        @Override
        public void onClick(View v) {
            mListener.onClickItem(mPelicula);
        }
    }
}

