package com.example.carlos.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carlos.myapplication.Database.AppDatabase;
import com.example.carlos.myapplication.Database.Entidades.Usuario;
import com.example.carlos.myapplication.R;

public class ActivityRegistro extends AppCompatActivity {

    EditText usernameTextfield, emailTextfield, passwordTextfield;
    Button botonRegistro;
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        getViews();
        setConfiguration();


    }

    public void getViews(){
        usernameTextfield = findViewById(R.id.username_textfield);
        emailTextfield = findViewById(R.id.email_textfield);
        passwordTextfield = findViewById(R.id.password_texfield);
        botonRegistro = findViewById(R.id.button_register);
    }


    public void setConfiguration(){
        appDatabase = AppDatabase.getDatabaseInstance(getApplicationContext());
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameText = usernameTextfield.getText().toString();
                final String emailText = emailTextfield.getText().toString();
                final String passwordText = passwordTextfield.getText().toString();

                if (!usernameText.equals("") && !emailText.equals("") && !passwordText.equals("")) {
                    final Usuario usuario = new Usuario(usernameText, passwordText, emailText, 0);

                    new Thread(){
                        @Override
                        public void run() {
                            final int verificarUsuario = appDatabase.usuarioDao().verificarUsuario(emailText, passwordText);
                            if (verificarUsuario != 1) {
                                appDatabase.usuarioDao().insertarUsuario(usuario);
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(verificarUsuario == 1) {
                                        Toast.makeText(ActivityRegistro.this, R.string.user_duplicate, Toast.LENGTH_SHORT).show();;
                                    } else {
                                        Toast.makeText(ActivityRegistro.this, R.string.successfull_sing_up, Toast.LENGTH_SHORT).show();;
                                        Intent intent = new Intent(ActivityRegistro.this, LoginRegisterActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        }
                    }.start();


                }
            }
        });

    }

}
