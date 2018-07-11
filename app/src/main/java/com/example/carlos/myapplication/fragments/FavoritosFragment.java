package com.example.carlos.myapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carlos.myapplication.adapters.FavoritosAdapter;
import com.example.carlos.myapplication.objects.Cancion;
import com.example.carlos.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Deryan Cruz on 6/23/2018.
 */

public class FavoritosFragment extends Fragment {

    private View view;
    ArrayList<Cancion> listDatos;
    RecyclerView recyclerViewNotificaciones;

    public FavoritosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recycler_view, container, false);

        listDatos = new ArrayList<>();
        recyclerViewNotificaciones = view.findViewById(R.id.recycler_view);
        recyclerViewNotificaciones.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarLista();

        FavoritosAdapter adapter = new FavoritosAdapter(listDatos);
        recyclerViewNotificaciones.setAdapter(adapter);

        return view;
    }

    private void llenarLista() {

        listDatos.add(new Cancion("Título cancion", "Cantante", "Localización", "Album", "id Canción"));

    }
}
