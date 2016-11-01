package a1.t1mo.mobjav.a816.myapplication.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.data.services.TmdbService;
import a1.t1mo.mobjav.a816.myapplication.model.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.model.Serie;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 25/10/2016.
 */

public class SerieAdapter extends RecyclerView.Adapter<SerieAdapter.SerieHolder>
        implements Listener<List<Serie>> {

    private List<Serie> mSeries;
    private SerieFragment.Escuchable mListener;

    public void setListener(SerieFragment.Escuchable listener) {
        this.mListener = listener;
    }

    @Override
    public SerieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R., parent, false);
        return new SerieHolder(view);
    }

    @Override
    public void onBindViewHolder(SerieHolder holder, int position) {
        holder.bindSerie(mSeries.get(position));
    }

    @Override
    public int getItemCount() {
        return mSeries == null ? 0 : mSeries.size();
    }

    @Override
    public void done(List<Serie> series) {
        mSeries = series;
        notifyDataSetChanged();
    }


    public class SerieHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Serie mSerie;
        private ImageView mImagen;

        public SerieHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mImagen = (ImageView) itemView.findViewById(R.id.img_pelicula);
        }

        private void bindSerie(Serie serie) {
            mSerie = serie;
            Glide.with(mImagen.getContext())

                    .load(TmdbService.IMAGE_URL_W154 + serie.getPosterPath())
                    .fitCenter()
                    .into(mImagen);

        }

        @Override
        public void onClick(View v) {
            mListener.onClickItem(mSeries);
        }
    }
}