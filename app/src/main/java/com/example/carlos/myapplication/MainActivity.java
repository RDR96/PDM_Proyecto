package com.example.carlos.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.carlos.myapplication.activity.LoginRegisterActivity;
import com.example.carlos.myapplication.fragment.ViewPager;

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
