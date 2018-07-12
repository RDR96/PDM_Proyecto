package com.example.carlos.myapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carlos.myapplication.Database.Entidades.Cancion;
import com.example.carlos.myapplication.activities.MainActivity;
import com.example.carlos.myapplication.adapters.FavoritosAdapter;
import com.example.carlos.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Deryan Cruz on 6/23/2018.
 */

public class FavoritosFragment extends Fragment {

    private View view;
    ArrayList<Cancion> listDatos;
    static RecyclerView recyclerViewNotificaciones;

    public FavoritosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = LayoutInflater.from(container.getContext()).inflate(R.layout.recycler_view, container, false);


        recyclerViewNotificaciones = view.findViewById(R.id.recycler_view);
        recyclerViewNotificaciones.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarLista(getContext());

        FavoritosAdapter adapter = new FavoritosAdapter(getContext(),MainActivity.favorites);
        recyclerViewNotificaciones.setAdapter(adapter);

        return view;
    }

    public static void llenarLista(Context context) {

        if (recyclerViewNotificaciones != null) {
            recyclerViewNotificaciones.setLayoutManager(new LinearLayoutManager(context));
            FavoritosAdapter adapter = new FavoritosAdapter(context, MainActivity.favorites);
            recyclerViewNotificaciones.setAdapter(adapter);
        }

    }
}
