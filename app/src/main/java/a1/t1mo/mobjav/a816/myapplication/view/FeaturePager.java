package a1.t1mo.mobjav.a816.myapplication.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.utils.Tipo;

public class FeaturePager extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    private CambioDePagina mCallback;
    private ViewPager mViewPager;
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
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            public void transformPage(View page, float position) {
                page.setRotationY(position * -60); // animation style... change as you want..
            }
        });
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                mCallback.onCambioDePagina(tipo);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mAdapter.redrawFragment(mViewPager.getCurrentItem(), item.getItemId());
        Log.d(getClass().getSimpleName(), "Current Page: " + mViewPager.getCurrentItem() +
                " Menu item: " + item.getTitle());
        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.main_drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
