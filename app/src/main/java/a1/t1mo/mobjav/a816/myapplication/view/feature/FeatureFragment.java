package a1.t1mo.mobjav.a816.myapplication.view.feature;

import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import a1.t1mo.mobjav.a816.myapplication.model.Feature;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 04/11/2016.
 */

public abstract class FeatureFragment extends Fragment{
    public static final String ARGUMENT_GENERO = "Genero";

    public abstract CharSequence getTitulo();

    public interface Escuchable {
        void onClickItem(Feature feature);
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space;
            } else {
                outRect.top = 0;
            }
        }
    }
}
