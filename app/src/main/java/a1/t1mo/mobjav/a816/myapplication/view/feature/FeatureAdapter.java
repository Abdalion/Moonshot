package a1.t1mo.mobjav.a816.myapplication.view.feature;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a1.t1mo.mobjav.a816.myapplication.data.services.TmdbService;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.model.Genre;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;
import a1.t1mo.mobjav.a816.myapplication.view.detalle.DetalleViewPager;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 16/11/2016.
 */

public class FeatureAdapter extends RecyclerView.Adapter<a1.t1mo.mobjav.a816.myapplication.view.feature.pelicula.FeatureAdapter.PeliculaHolder>
        implements Listener<List<Pelicula>> {

    private List<Feature> mFeatures;
    private PeliculaController mPeliculaController;
    private FeatureFragment.ListenerFeature mListener;
    private Integer mGenero;
    private final static int FADE_DURATION = 300;

    public FeatureAdapter(Context context, Integer genero) {
        mPeliculaController = new PeliculaController(context);
        mGenero = genero;
        if (genero == Genre.PELICULA_ID.get(R.id.menu_peliculas_opcion_todas)) {
            mPeliculaController.getPeliculasPopulares(this);
        } else {
            mPeliculaController.getPeliculasPorGenero(Genre.PELICULA_ID.get(genero), this);
        }
    }

    public void setListener(FeatureFragment.ListenerFeature listener) {
        this.mListener = listener;
    }

    @Override
    public a1.t1mo.mobjav.a816.myapplication.view.feature.pelicula.FeatureAdapter.PeliculaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_pelicula, parent, false);
        return new a1.t1mo.mobjav.a816.myapplication.view.feature.pelicula.FeatureAdapter.PeliculaHolder(view);
    }

    @Override
    public void onBindViewHolder(a1.t1mo.mobjav.a816.myapplication.view.feature.pelicula.FeatureAdapter.PeliculaHolder holder, int position) {
        if (mFeatures != null && !mFeatures.isEmpty()) {
            holder.bindPelicula(mFeatures.get(position));
            setScaleAnimation(holder.itemView);
        }

    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    @Override
    public int getItemCount() {
        return mFeatures == null ? 0 : mFeatures.size();
    }

    @Override
    public void done(List<Pelicula> peliculas) {
        mFeatures = peliculas;
        notifyDataSetChanged();
    }

    public class PeliculaHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private ImageView mImagen;

        public PeliculaHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mImagen = (ImageView) itemView.findViewById(R.id.img_pelicula);
        }

        private void bindPelicula(Pelicula pelicula) {
            Glide
                    .with(mImagen.getContext())
                    .load(TmdbService.IMAGE_URL_W154 + pelicula.getPosterPath())
                    .fitCenter()
                    .into(mImagen);
        }

        @Override
        public void onClick(View v) {
            Log.d("Pelicula Adapter", "Posicion " + getLayoutPosition());
            mListener.onClickFeature(getLayoutPosition(), mGenero, DetalleViewPager.Tipo.PELICULA);
        }
    }
}

