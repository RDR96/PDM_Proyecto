package com.example.carlos.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.CardView;

import com.example.carlos.myapplication.activities.AlbumesActivity;
import com.example.carlos.myapplication.activities.ArtistasActivity;
import com.example.carlos.myapplication.activities.CancionesFavoritasActivity;
import com.example.carlos.myapplication.activities.MisCancionesActivity;
import com.example.carlos.myapplication.activities.PlaylistActivity;
import com.example.carlos.myapplication.R;

/**
 * Created by Deryan Cruz on 6/23/2018.
 */

public class PerfilFragment extends Fragment{
    //Espacio de declaración
    private View view;

    private CardView fragmentPerfilCancionesFavoritasButton;
    private CardView fragmentPerfilMisCancionesButton;
    private CardView fragmentPerfilAlbumesButton;
    private CardView fragmentPerfilPlaylists;
    private CardView fragmentPerfilArtistasButton;
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
        fragmentPerfilPlaylists = view.findViewById(R.id.fragment_perfil_playlists_button);
        iniciarEventos();
    }

    private void iniciarEventos() {

        fragmentPerfilPlaylists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                levantaPantallas = new Intent(v.getContext(), PlaylistActivity.class);
                v.getContext().startActivity(levantaPantallas);
            }
        });

    }

}
