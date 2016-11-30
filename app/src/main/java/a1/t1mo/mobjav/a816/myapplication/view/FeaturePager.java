package a1.t1mo.mobjav.a816.myapplication.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.utils.Tipo;

public class FeaturePager extends Fragment implements NavMenuListener {
    private CambioDePagina mCallback;
    private FeaturePagerAdapter mAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CambioDePagina) {
            mCallback = (CambioDePagina) context;
        }
    }

    public FeaturePager() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
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
                    tipo = Tipo.FAVORITOS_PELICULAS;
                mCallback.onCambioDePagina(tipo);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        return view;
    }

    public void redrawFragment(Tipo tipo) {
        mAdapter.redrawFragment(tipo);
    }

    @Override
    public void onMenuItemSelected(int id) {

    }
}
