package com.example.carlos.myapplication.fragments;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.carlos.myapplication.adapters.InicioAdapter;
import com.example.carlos.myapplication.objects.Cancion;
import com.example.carlos.myapplication.objects.Helpers;
import com.example.carlos.myapplication.objects.Inicio;
import com.example.carlos.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Deryan Cruz on 6/23/2018.
 */

public class InicioFragment extends Fragment {

    private View view;
    public static ArrayList<Cancion> listaCanciones;
    RecyclerView recyclerViewInicio;
    Button botonObtenerCanciones;

    public InicioFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recycler_view, container, false);

        listaCanciones = new ArrayList<>();
        recyclerViewInicio = view.findViewById(R.id.recycler_view);
        recyclerViewInicio.setLayoutManager(new LinearLayoutManager(getContext()));



        if (Helpers.checkVersion5_1()) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                listaCanciones = new ArrayList<>();
                requestPermissionAndContinue();
            } else{
                getMusic();
            }

        } else {
            getMusic();
        }



        InicioAdapter adapter_inicio = new InicioAdapter(getContext(),listaCanciones);
        recyclerViewInicio.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewInicio.setAdapter(adapter_inicio);

        return view;
    }

    //Función que pide permisos al usuario, en este caso: READ_CONTACTS. De esta manera es posible recuperar los contactos del cel.
    private void requestPermissionAndContinue(){
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)){
                Log.e("texto", "permission denied, show dialog");
            }else{
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
            }
        }else{
            getMusic();
        }
    }

    //Esta función se ejecuta justamente después de que se ha llamado a la funcion requestPermissions(). Se verifica
    //el permiso que llamó a la función identificandolo con un código
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 2) {
            if(permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE) && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getMusic();
            }else{
                //redirect to settings page or ask permission again
            }
        }

    }

    public void getMusic(){
        ContentResolver contentResolver = getContext().getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(songUri,null,null,null,null);
        int counter = 0;
        if(cursor != null && cursor.moveToFirst()){
            int tituloCancion = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int artistaCancion = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int localizacionCancion = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            int albumCancion = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
            int idCancion = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
            do{
                String tituloActual = cursor.getString(tituloCancion);
                String artistaActual = cursor.getString(artistaCancion);
                String localizacionActual = cursor.getString(localizacionCancion);
                String album = cursor.getString(albumCancion);
                String id = cursor.getString(idCancion);
                listaCanciones.add(new Cancion(tituloActual,artistaActual,localizacionActual,album, id));
            }while(cursor.moveToNext());
        }
        setList();
    }

    public void setList(){
        InicioAdapter adapter_inicio = new InicioAdapter(getContext(),listaCanciones);
        recyclerViewInicio.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewInicio.setAdapter(adapter_inicio);
    }

    /*private void llenarlista() {

        listDatos.add(new Inicio("John", "Nombre Cancion 1", "Artista 1", "Album 1"));
        listDatos.add(new Inicio("Carlos", "Nombre Cancion 2", "Artista 2", "Album 2"));
        listDatos.add(new Inicio("Didier", "Nombre Cancion 3", "Artista 3", "Album 3"));
        listDatos.add(new Inicio("Carlos", "Nombre Cancion 4", "Artista 4", "Album 4"));
        listDatos.add(new Inicio("Rodrigo", "Nombre Cancion 5", "Artista 5", "Album 5"));
    }*/
}
