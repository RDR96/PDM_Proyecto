package com.example.carlos.myapplication.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.carlos.myapplication.fragments.ViewPager;
import com.example.carlos.myapplication.R;

public class MainActivity extends AppCompatActivity {
    //Espacio de declaración de Variables y Objetos
    Fragment miFragment=null;

    //LifeCycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO Implementar LOGIN
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
