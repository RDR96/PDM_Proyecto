package com.example.carlos.myapplication.Activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.carlos.myapplication.R;


/**
 * Created by Deryan Cruz on 6/9/2018.
 */

public class LoginRegisterActivity extends AppCompatActivity {

    private Button loginbtn;
    private  static Activity parentActivity;

    public static void newInstance(Activity parent) {
        parentActivity = parent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        SharedPreferences Login = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor loginEditor = Login.edit();
        loginEditor.putBoolean("login",false);
        loginEditor.apply();
        loginbtn = findViewById(R.id.login);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        parentActivity.finish();
    }


}
