package com.example.carlos.myapplication.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.carlos.myapplication.adapters.PlaylistAdapter;
import com.example.carlos.myapplication.objects.Playlist;
import com.example.carlos.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class PlaylistActivity extends AppCompatActivity {

    List<Playlist> listDatos;
    RecyclerView PlaylistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarplaylist);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        listDatos = new ArrayList<>();
        PlaylistAdapter = findViewById(R.id.recycler_view);
        PlaylistAdapter.setHasFixedSize(true);
        PlaylistAdapter.setLayoutManager(new LinearLayoutManager(this));
        llenarDatos();
        PlaylistAdapter adapter = new PlaylistAdapter(this,listDatos);
        PlaylistAdapter.setAdapter(adapter);
    }

    private void llenarDatos() {
        listDatos.add(new Playlist("Nombre playlist","autor","album"));
        listDatos.add(new Playlist("Nombre playlist1","autor1","album1"));
        listDatos.add(new Playlist("Nombre playlist","autor","album"));
        listDatos.add(new Playlist("Nombre playlist1","autor1","album1"));
        listDatos.add(new Playlist("Nombre playlist","autor","album"));
        listDatos.add(new Playlist("Nombre playlist1","autor1","album1"));
        listDatos.add(new Playlist("Nombre playlist","autor","album"));
        listDatos.add(new Playlist("Nombre playlist1","autor1","album1"));
    }
}
