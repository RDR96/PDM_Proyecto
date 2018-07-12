package com.example.carlos.myapplication.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.CardView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.carlos.myapplication.Database.AppDatabase;
import com.example.carlos.myapplication.activities.AlbumesActivity;
import com.example.carlos.myapplication.activities.ArtistasActivity;
import com.example.carlos.myapplication.activities.CancionesFavoritasActivity;
import com.example.carlos.myapplication.activities.LoginRegisterActivity;
import com.example.carlos.myapplication.activities.MainActivity;
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
    LinearLayout botonAjustes;
    LinearLayout botonCerrarSesion;
    AppDatabase appDatabase;
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
        appDatabase = AppDatabase.getDatabaseInstance(getContext());
        botonCerrarSesion = view.findViewById(R.id.cerrar_sesion);
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

        botonCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                View dialogElements = getLayoutInflater().inflate(R.layout.dialog_confirmacion_eliminar, null);
                Button yesButton = dialogElements.findViewById(R.id.button_yes_answer);
                Button noButton = dialogElements.findViewById(R.id.button_no_answer);
                final AlertDialog alert;
                mBuilder.setView(dialogElements);
                alert = mBuilder.create();
                alert.show();

                yesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new Thread(){
                            @Override
                            public void run() {
                                LoginRegisterActivity.usuarioActual.setLog(0);
                                appDatabase.usuarioDao().actualizarUsuario(LoginRegisterActivity.usuarioActual);
                                Intent intent = new Intent(getContext(), LoginRegisterActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                            }
                        }.start();
                    }
                });

                noButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.hide();
                        alert.dismiss();
                    }
                });


            }
        });

    }

}
