package com.example.carlos.myapplication.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carlos.myapplication.R;
import com.example.carlos.myapplication.Activities.FavoritosActivity;

/**
 * Created by Deryan Cruz on 6/23/2018.
 */

public class PerfilFragment extends Fragment{
    //Espacio de declaración
    private View view;

    private TextView fav;
    private Intent levantaPantallas;
    //Fin


    public PerfilFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_perfil, container, false);
        iniciarControles();
        return view;
    }

    private void iniciarControles() {
        fav = view.findViewById(R.id.canciones_favoritas);
        iniciarEventos();
    }

    private void iniciarEventos() {
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                levantaPantallas = new Intent(v.getContext(), FavoritosActivity.class);
                v.getContext().startActivity(levantaPantallas);
            }
        });
    }

}
