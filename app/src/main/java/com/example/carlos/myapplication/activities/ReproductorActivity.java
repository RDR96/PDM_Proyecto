package com.example.carlos.myapplication.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.carlos.myapplication.R;
import com.example.carlos.myapplication.objects.Helpers;

public class ReproductorActivity extends AppCompatActivity {

    ImageView botonReproducir;
    String info[];
    MediaPlayer mediaPlayer;
    TextView textoArtista, textoCancion, textoAlbum, tiempoTranscurrido, tiempoRestante;
    SeekBar barraProgresoCancion;
    int tiempoTotal;
    int currentTime;
    ImageView botonRepetir;
    boolean isRepeating;
    boolean isPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            currentTime = savedInstanceState.getInt("currentPosition");
            isRepeating = savedInstanceState.getBoolean("isRepeating");
            isPlaying = savedInstanceState.getBoolean("isPlaying");
            //mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
        } else {
            currentTime = 0;
            isRepeating = false;
            isPlaying = false;
        }

        getViews();
        setConfiguration();
        getPlayer();
    }

    public void getViews(){
        botonReproducir = findViewById(R.id.boton_reproducir);
        barraProgresoCancion = findViewById(R.id.barra_progreso_cancion);
        tiempoTranscurrido = findViewById(R.id.tiempo_transcurrido);
        tiempoRestante = findViewById(R.id.tiempo_restante);
        botonRepetir = findViewById(R.id.boton_repetir_cancion);
    }

    public void getPlayer(){
        mediaPlayer = Helpers.getPlayer(getApplicationContext(), info[2]);
        barraProgresoCancion.setMax(mediaPlayer.getDuration());
        barraProgresoCancion.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                    barraProgresoCancion.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(info[2]));
        mediaPlayer.seekTo(currentTime);

        if (  ) {

        }
            if (!isPlaying) {
                mediaPlayer.start();
                isPlaying = true;
            } else {
                mediaPlayer.pause();
                isPlaying = false;
            }

        botonReproducir.setImageResource(R.drawable.ic_pause_button);
        textoCancion = findViewById(R.id.nombre_cancion);
        textoArtista = findViewById(R.id.nombre_artista);
        textoCancion.setText(info[0]);
        textoArtista.setText(info[1]);
        tiempoTotal = mediaPlayer.getDuration();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mediaPlayer != null){
                    try {
                        Message msg = new Message();
                        msg.what = mediaPlayer.getCurrentPosition();
                        handler.sendMessage(msg);

                        Thread.sleep(1000);
                    }catch (InterruptedException e){

                    }
                }
            }
        }).start();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            int currentPosition = msg.what;
            //Se actualiza la barra de posicion
            barraProgresoCancion.setProgress(currentPosition);
            String tiempoTrans = crearEtiquetaTiempo(currentPosition);

            tiempoTranscurrido.setText(tiempoTrans);

            String tiempoRest = crearEtiquetaTiempo(tiempoTotal-currentPosition);

            tiempoRestante.setText("-" + tiempoRest);

        }
    };

    public String crearEtiquetaTiempo(int time){
        String etiquetaTiempo = "";
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;
        etiquetaTiempo = min + ":";
        if (sec < 10) etiquetaTiempo += "0";
        etiquetaTiempo += sec;
        return etiquetaTiempo;

    }

    public void setConfiguration() {
        Intent intent = getIntent();
        datosCancion(intent);

        if (mediaPlayer != null) {
            if (isRepeating) {
                botonRepetir.setAlpha(1f);
                mediaPlayer.setLooping(true);
                isRepeating = true;
            } else{
                botonRepetir.setAlpha(0.3f);
                mediaPlayer.setLooping(false);
                isRepeating = false;
            }
        }


        botonRepetir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRepeating) {
                    botonRepetir.setAlpha(0.3f);
                    mediaPlayer.setLooping(false);
                    isRepeating = false;
                } else {
                    botonRepetir.setAlpha(1f);
                    mediaPlayer.setLooping(true);
                    isRepeating = true;
                }
            }
        });

        botonReproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer.isPlaying()) {
                    botonReproducir.setImageResource(R.drawable.ic_pause_button);
                    mediaPlayer.start();
                } else{
                    botonReproducir.setImageResource(R.drawable.ic_play_arrow);
                    mediaPlayer.pause();
                }
            }
        });
    }

    public void datosCancion(Intent intent){
        info = intent.getStringExtra(Intent.EXTRA_TEXT).split("-/");
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putBoolean("isRepeating", isRepeating);
        savedInstanceState.putBoolean("isPlaying", isPlaying);
        savedInstanceState.putInt("currentPosition",mediaPlayer.getCurrentPosition());
        // etc.
    }



}

