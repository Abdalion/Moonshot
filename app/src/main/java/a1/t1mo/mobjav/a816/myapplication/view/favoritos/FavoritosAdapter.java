package a1.t1mo.mobjav.a816.myapplication.view.favoritos;

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
import a1.t1mo.mobjav.a816.myapplication.controller.SerieController;
import a1.t1mo.mobjav.a816.myapplication.data.services.TmdbService;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;
import a1.t1mo.mobjav.a816.myapplication.view.detalle.DetalleViewPager;
import a1.t1mo.mobjav.a816.myapplication.view.feature.FeatureFragment;

import static a1.t1mo.mobjav.a816.myapplication.view.detalle.DetalleViewPager.FAVORITOS_FLAG;


public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosAdapter.FavoritosHolder> {

    private List<? extends Feature> mFeaturesList;
    private PeliculaController mPeliculaController;
    private SerieController mSerieController;
    private FeatureFragment.ListenerFeature mListener;
    private final static int FADE_DURATION = 300;

    public FavoritosAdapter(Context context, Integer genero) {
        if (genero == R.id.menu_favoritos_opcion_peliculas) {
            mPeliculaController = new PeliculaController(context);
            mFeaturesList = mPeliculaController.getFavoritos();
            Log.d("Favoritos Adapter", "Cargue " + mFeaturesList.size() + " peliculas favoritas ");
        } else {
            mSerieController = new SerieController(context);
            mFeaturesList = mSerieController.getFavoritos();
            Log.d("Favoritos Adapter", "Cargue " + mFeaturesList.size() + " series favoritas ");
        }
    }

    public void setListener(FeatureFragment.ListenerFeature listener) {
        this.mListener = listener;
    }

    @Override
    public FavoritosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_pelicula, parent, false);
        return new FavoritosHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoritosHolder holder, int position) {
        if (mFeaturesList != null && !mFeaturesList.isEmpty()) {
            holder.bindFeature(mFeaturesList.get(position));
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
        return mFeaturesList == null ? 0 : mFeaturesList.size();
    }

    public class FavoritosHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Feature mFeature;
        private ImageView mImagen;

        public FavoritosHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mImagen = (ImageView) itemView.findViewById(R.id.img_pelicula);
        }

        private void bindFeature(Feature feature) {
            mFeature = feature;
            Glide
                    .with(mImagen.getContext())
                    .load(TmdbService.IMAGE_URL_W154 + feature.getPosterPath())
                    .fitCenter()
                    .into(mImagen);
        }

        @Override
        public void onClick(View v) {
            DetalleViewPager.Tipo tipo = mFeature instanceof Serie ? DetalleViewPager.Tipo.SERIE : DetalleViewPager.Tipo.PELICULA;
            mListener.onClickFeature(getLayoutPosition(), FAVORITOS_FLAG, tipo);
        }
    }

}
