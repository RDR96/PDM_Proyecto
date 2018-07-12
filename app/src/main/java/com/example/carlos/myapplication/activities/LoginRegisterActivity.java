package com.example.carlos.myapplication.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;
import android.widget.Toast;

import com.example.carlos.myapplication.Database.AppDatabase;
import com.example.carlos.myapplication.Database.Entidades.Usuario;
import com.example.carlos.myapplication.R;

import org.w3c.dom.Text;


/**
 * Created by Deryan Cruz on 6/9/2018.
 */

public class LoginRegisterActivity extends AppCompatActivity {

    private Button loginbtn;
    private  static Activity parentActivity;
    private TextView textoRegistro;
    private EditText username, password;
    private Button signInButton;
    AppDatabase appDatabase;
    public static Usuario usuarioActual;

    public static void newInstance(Activity parent) {
        parentActivity = parent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        checkIfUserConnect();
        getViews();
        setConfiguration();

        /*SharedPreferences Login = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor loginEditor = Login.edit();
        loginEditor.putBoolean("login",false);
        loginEditor.apply();
        loginbtn = findViewById(R.id.login);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
    }

    public void checkIfUserConnect(){
        appDatabase = AppDatabase.getDatabaseInstance(getApplicationContext());
        new Thread(){
            @Override
            public void run() {
                int verificacionUsuarioConectado = appDatabase.usuarioDao().verificarUsuarioConectado();




                if (verificacionUsuarioConectado == 1){
                    usuarioActual = appDatabase.usuarioDao().verificarSesion();
                    Intent intent = new Intent(LoginRegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }.start();

    }

    public void getViews(){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signInButton = findViewById(R.id.sign_in);
        textoRegistro = findViewById(R.id.boton_registro);
    }

    public void setConfiguration(){
        appDatabase = AppDatabase.getDatabaseInstance(getApplicationContext());
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usernameText = username.getText().toString();
                final String passwordText = password.getText().toString();
                if (!usernameText.equals("") && !passwordText.equals("")) {
                    new Thread(){
                        @Override
                        public void run() {
                            final int verificacionUsuario = appDatabase.usuarioDao().verificarUsuario(passwordText, usernameText);

                            if (verificacionUsuario == 1) {
                                usuarioActual = appDatabase.usuarioDao().obtenerUsuario(passwordText, usernameText);
                                usuarioActual.setLog(1);
                                appDatabase.usuarioDao().actualizarUsuario(usuarioActual);
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (verificacionUsuario == 1) {
                                        Toast.makeText(LoginRegisterActivity.this, R.string.welcome, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LoginRegisterActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(LoginRegisterActivity.this, R.string.no_user_found, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                        }
                    }.start();

                }else{
                    Toast.makeText(getApplicationContext(), R.string.fill_gaps, Toast.LENGTH_SHORT).show();
                }
            }
        });

        textoRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginRegisterActivity.this, ActivityRegistro.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
