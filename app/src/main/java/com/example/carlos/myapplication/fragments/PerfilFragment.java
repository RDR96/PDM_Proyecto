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
        fragmentPerfilCancionesFavoritasButton = view.findViewById(R.id.fragment_perfil_canciones_favoritas_button);
        fragmentPerfilMisCancionesButton = view.findViewById(R.id.fragment_perfil_mis_canciones_button);
        fragmentPerfilAlbumesButton = view.findViewById(R.id.fragment_perfil_albumes_button);
        fragmentPerfilPlaylists = view.findViewById(R.id.fragment_perfil_playlists_button);
        fragmentPerfilArtistasButton = view.findViewById(R.id.fragment_perfil_artistas_card);
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

        fragmentPerfilAlbumesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                levantaPantallas = new Intent(v.getContext(), AlbumesActivity.class);
                v.getContext().startActivity(levantaPantallas);
            }
        });

        fragmentPerfilPlaylists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                levantaPantallas = new Intent(v.getContext(), PlaylistActivity.class);
                v.getContext().startActivity(levantaPantallas);
            }
        });
        fragmentPerfilArtistasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                levantaPantallas = new Intent(v.getContext(), ArtistasActivity.class);
                v.getContext().startActivity(levantaPantallas);
            }
        });

    }

}
