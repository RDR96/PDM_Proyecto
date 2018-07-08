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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        getViews();
        setConfiguration();
        getPlayer();
    }

    public void getViews(){
        botonReproducir = findViewById(R.id.boton_reproducir);
        barraProgresoCancion = findViewById(R.id.barra_progreso_cancion);
        tiempoTranscurrido = findViewById(R.id.tiempo_transcurrido);
        tiempoRestante = findViewById(R.id.tiempo_restante);
    }

    public void getPlayer(){
        mediaPlayer = Helpers.getPlayer(getApplicationContext(), info[2]);
        mediaPlayer.seekTo(0);
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
        mediaPlayer.start();
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
}

