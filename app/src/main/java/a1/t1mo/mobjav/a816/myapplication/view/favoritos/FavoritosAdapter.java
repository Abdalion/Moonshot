package a1.t1mo.mobjav.a816.myapplication.view.favoritos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dh-mob-tt on 30/11/16.
 */
public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosAdapter.FavoritoHolder> {

    @Override
    public FavoritoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(FavoritoHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FavoritoHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public FavoritoHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
