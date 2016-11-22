package a1.t1mo.mobjav.a816.myapplication.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.utils.Tipo;

public class FeaturePager extends Fragment {
    private MainActivity mainActivity;
    private FeaturePagerAdapter mAdapter;
    private LayoutInflater mInflater;
    private ViewGroup mContainer;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mainActivity = (MainActivity) context;
        }
    }

    public FeaturePager() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_pager, mContainer, false);
        mAdapter = new FeaturePagerAdapter(getChildFragmentManager());
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(mAdapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Tipo tipo;
                if (position == 0)
                    tipo = Tipo.PELICULAS;
                else if (position == 1)
                    tipo = Tipo.SERIES;
                else
                    tipo = Tipo.FAVORITOS;
                mainActivity.onCambioDePagina(tipo);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        return view;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onClickFavoriteEvent(MainActivity.ClickFavoriteEvent event) {
        redrawFragment(Tipo.FAVORITOS);
    };

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void redrawFragment(Tipo tipo) {
        mAdapter.redrawFragment(tipo);
    }
}
