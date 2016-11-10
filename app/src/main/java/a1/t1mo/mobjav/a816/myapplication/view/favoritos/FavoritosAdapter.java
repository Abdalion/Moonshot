package a1.t1mo.mobjav.a816.myapplication.view.favoritos;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
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
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;


public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosAdapter.FavoritosHolder>
        implements Listener<List<Feature>> {

    private List<Feature> mFeaturesList;
    private PeliculaController mPeliculaController;
    private SerieController mSerieController;
    private FavoritosFragment.Escuchable mListener;
    private final static int FADE_DURATION = 300;

    public FavoritosAdapter(Context context, Integer genero) {
        if (genero == 0) {
            mPeliculaController = new PeliculaController(context);
            mPeliculaController.getFavoritos();
        } else {
            mSerieController = new SerieController(context);
            mSerieController.getFavoritos();
        }
    }

    @Override
    public void done(List<Feature> features) {
        mFeaturesList = features;
    }

    public void setListener(FavoritosFragment.Escuchable listener) {
        this.mListener = listener;
    }

    @Override
    public FavoritosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
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
        return 0;
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
            mListener.onClickItem(mFeature);
        }
    }

}
