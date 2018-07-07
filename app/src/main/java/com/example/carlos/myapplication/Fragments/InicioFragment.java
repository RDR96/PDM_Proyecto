package com.example.carlos.myapplication.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carlos.myapplication.Adapters.InicioAdapter;
import com.example.carlos.myapplication.Objects.Inicio;
import com.example.carlos.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Deryan Cruz on 6/23/2018.
 */

public class InicioFragment extends Fragment {

    private View view;
    ArrayList<Inicio> listDatos;
    RecyclerView recyclerViewInicio;

    public InicioFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recycler_view, container, false);

        listDatos = new ArrayList<>();
        recyclerViewInicio = view.findViewById(R.id.recycler_view);
        recyclerViewInicio.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarlista();

        InicioAdapter adapter_inicio = new InicioAdapter(listDatos);
        recyclerViewInicio.setAdapter(adapter_inicio);

        return view;
    }

    private void llenarlista() {

        listDatos.add(new Inicio("John", "Nombre Cancion 1", "Artista 1", "Album 1"));
        listDatos.add(new Inicio("Carlos", "Nombre Cancion 2", "Artista 2", "Album 2"));
        listDatos.add(new Inicio("Didier", "Nombre Cancion 3", "Artista 3", "Album 3"));
        listDatos.add(new Inicio("Carlos", "Nombre Cancion 4", "Artista 4", "Album 4"));
        listDatos.add(new Inicio("Rodrigo", "Nombre Cancion 5", "Artista 5", "Album 5"));
    }
}
