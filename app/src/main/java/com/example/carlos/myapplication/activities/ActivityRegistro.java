package com.example.carlos.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.carlos.myapplication.R;

public class ActivityRegistro extends AppCompatActivity {

    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        passwordText =findViewById(R.id.activity_registro_contrasena);
        String password = passwordText.getText().toString();
        if (password.isEmpty() || password.length() < 8) {  passwordText.setError("La clave debe tener por lo menos 8 caracteres!");
        }

    }
}
