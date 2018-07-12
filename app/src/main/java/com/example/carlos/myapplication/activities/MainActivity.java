package com.example.carlos.myapplication.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.carlos.myapplication.Database.AppDatabase;
import com.example.carlos.myapplication.Database.Entidades.Cancion;
import com.example.carlos.myapplication.fragments.FavoritosFragment;
import com.example.carlos.myapplication.fragments.ViewPager;
import com.example.carlos.myapplication.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Espacio de declaración de Variables y Objetos
    Fragment miFragment=null;
    public static ArrayList<Cancion> favorites;
    //LifeCycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO Implementar LOGIN
        final AppDatabase appDatabase = AppDatabase.getDatabaseInstance(getApplicationContext());
        favorites = new ArrayList<>();
        new Thread(){
            @Override
            public void run() {
                favorites = (ArrayList<Cancion>) appDatabase.favoritasDao().obtenerFavoritos();
                Log.d("Longitud", String.valueOf(favorites.size()));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        FavoritosFragment.llenarLista(getApplicationContext());
                    }
                });


            }
        }.start();

        //login();
        firstStart();
    }
    //Developer Methods
    private void firstStart() {
        inicializarControles();
        miFragment = new ViewPager();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,miFragment)
                .commit();
    }

    public void fillFavoritesFragment(){

    }

    private void  inicializarControles(){

    }

    private void login() {
        boolean loged;
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(this);
        loged = sh.getBoolean("loged",false);
        if(!loged){
            Intent login = new Intent(getApplicationContext(),LoginRegisterActivity.class);
        }
    }

}