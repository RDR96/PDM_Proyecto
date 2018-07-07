package com.example.carlos.myapplication.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.CardView;

import com.example.carlos.myapplication.Activities.MisCancionesActivity;
import com.example.carlos.myapplication.R;
import com.example.carlos.myapplication.Activities.CancionesFavoritasActivity;

/**
 * Created by Deryan Cruz on 6/23/2018.
 */

public class PerfilFragment extends Fragment{
    //Espacio de declaración
    private View view;

    private CardView fragmentPerfilCancionesFavoritasButton;
    private CardView fragmentPerfilMisCancionesButton;
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
        fragmentPerfilCancionesFavoritasButton = view.findViewById(R.id.fragment_perfil_canciones_favoritas_button);
        fragmentPerfilMisCancionesButton = view.findViewById(R.id.fragment_perfil_mis_canciones_button);
        iniciarEventos();
    }

    private void iniciarEventos() {
        fragmentPerfilCancionesFavoritasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                levantaPantallas = new Intent(v.getContext(), CancionesFavoritasActivity.class);
                v.getContext().startActivity(levantaPantallas);
            }
        });

        fragmentPerfilMisCancionesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                levantaPantallas = new Intent(v.getContext(), MisCancionesActivity.class);
                v.getContext().startActivity(levantaPantallas);
            }
        });



    }

}
