package com.example.carlos.myapplication.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carlos.myapplication.R;
import com.example.carlos.myapplication.Adapters.ViewPagerAdapter;
import com.example.carlos.myapplication.Util.Util;

/**
 * Created by Deryan Cruz on 6/22/2018.
 */

public class ViewPager extends Fragment {


    View vista;
    private AppBarLayout appBar;
    private TabLayout pestanas;
    private android.support.v4.view.ViewPager viewPager;
    private static String dato;

    public ViewPager() {
    }

    public static ViewPager newInstance(String param) {
        ViewPager fragment = new ViewPager();
        dato = param;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vista=inflater.inflate(R.layout.view_pager, container, false);

        if(Util.rotacion==0){
            View parent= (View) container.getParent();

            if(appBar==null){
                appBar=  parent.findViewById(R.id.appBar);
                pestanas=new TabLayout(getActivity());
                pestanas.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));

                appBar.addView(pestanas);

                viewPager=  vista.findViewById(R.id.idViewPagerInformacion);

                llenarViewPager(viewPager);
                viewPager.addOnPageChangeListener(new android.support.v4.view.ViewPager.SimpleOnPageChangeListener(){
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }
                });
                pestanas.setupWithViewPager(viewPager);
                pestanas.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
                pestanas.getTabAt(1).setIcon(R.drawable.ic_person_black_24dp);
                pestanas.getTabAt(2).setIcon(R.drawable.ic_notifications_black_24dp);
                pestanas.getTabAt(3).setIcon(R.drawable.ic_search_black_24dp);
            }
            pestanas.setTabGravity(TabLayout.GRAVITY_FILL);
        }else{
            Util.rotacion=1;
        }

        return vista;

    }

    private void llenarViewPager(android.support.v4.view.ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new InicioFragment(),"Inicio");
        adapter.addFragment(new PerfilFragment(),"Perfil");
        adapter.addFragment(new NotificacionesFragment(),"Notificaciones");
        adapter.addFragment(new BuscarFragment(),"Burcar");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(Util.rotacion==0){
            appBar.removeView(pestanas);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
