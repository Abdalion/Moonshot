package a1.t1mo.mobjav.a816.myapplication.view.favoritos;

import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a1.t1mo.mobjav.a816.myapplication.controller.SerieController;
import a1.t1mo.mobjav.a816.myapplication.data.services.TmdbService;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.model.Genre;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.view.feature.pelicula.PeliculaAdapter;
import a1.t1mo.mobjav.a816.myapplication.view.feature.pelicula.PeliculaFragment;

public class FavoritosAdapter extends PeliculaAdapter{

    private List<Feature> mFeaturesList;
    private PeliculaController mPeliculaController;
    private SerieController mSerieController;

    public FavoritosAdapter(Integer genero) {
        if (genero == 0) {
            mPeliculaController = new PeliculaController();
            mPeliculaController.getFavoritos();
        } else {
            mSerieController = new SerieController();
            mSerieController.getFavoritos();
        }
    }
}
