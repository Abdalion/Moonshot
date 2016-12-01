package a1.t1mo.mobjav.a816.myapplication.view.feature;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.data.services.TmdbService;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 16/11/2016.
 */

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.FeatureHolder> {
    private List<? extends Feature> mFeatures = null;
    private final static int FADE_DURATION = 300;
    private View.OnClickListener mListener;

    public FeatureAdapter(View.OnClickListener listener) {
        mListener = listener;
    }

    @Override
    public FeatureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_feature, parent, false);
        return new FeatureHolder(view);
    }

    @Override
    public void onBindViewHolder(FeatureHolder holder, int position) {
        if (mFeatures != null && !mFeatures.isEmpty()) {
            holder.bindFeature(mFeatures.get(position));
            setScaleAnimation(holder.itemView);
        }
    }

    @Override
    public int getItemCount() {
        return mFeatures == null ? 0 : mFeatures.size();
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    public void setFeatures(List<? extends Feature> features) {
        mFeatures = features;
        notifyDataSetChanged();
    }

    public class FeatureHolder extends RecyclerView.ViewHolder {
        private ImageView mImagen;

        public FeatureHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(mListener);
            mImagen = (ImageView) itemView.findViewById(R.id.img_feature);
        }

        private void bindFeature(Feature feature) {
            Glide
                .with(mImagen.getContext())
                .load(TmdbService.IMAGE_URL_W154 + feature.getPosterPath())
                .fitCenter()
                .into(mImagen);
        }
    }
}

