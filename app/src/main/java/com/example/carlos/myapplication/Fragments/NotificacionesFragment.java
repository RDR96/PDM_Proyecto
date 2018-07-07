package com.example.carlos.myapplication.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carlos.myapplication.Adapters.NotificacionesAdapter;
import com.example.carlos.myapplication.Objects.Notificaciones;
import com.example.carlos.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Deryan Cruz on 6/23/2018.
 */

public class NotificacionesFragment extends Fragment {

    private View view;
    ArrayList<Notificaciones> listDatos;
    RecyclerView recyclerViewNotificaciones;

    public NotificacionesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recycler_view, container, false);

        listDatos = new ArrayList<>();
        recyclerViewNotificaciones = view.findViewById(R.id.recycler_view);
        recyclerViewNotificaciones.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarLista();

        NotificacionesAdapter adapter = new NotificacionesAdapter(listDatos);
        recyclerViewNotificaciones.setAdapter(adapter);

        return view;
    }

    private void llenarLista() {

        listDatos.add(new Notificaciones("Etiqueta", "Usuario", "Te han etiquetado", "Nombre Cancion", "Nombre del artista", "Nombre del album", "Hace 10 min"));
        listDatos.add(new Notificaciones("Etiqueta2", "Usuario2", "Te han etiquetado 2", "Duality", "Slipknot", "Duality", "Hace 1 hora"));

    }
}
