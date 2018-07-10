package com.example.carlos.myapplication.activities;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlos.myapplication.R;
import com.example.carlos.myapplication.fragments.InicioFragment;
import com.example.carlos.myapplication.objects.Cancion;
import com.example.carlos.myapplication.objects.Helpers;

import java.util.Random;

public class ReproductorActivity extends AppCompatActivity {

    ImageView botonReproducir, botonAnterior, botonSiguiente;
    String info[];
    MediaPlayer mediaPlayer;
    TextView textoArtista, textoCancion, textoAlbum, tiempoTranscurrido, tiempoRestante;
    SeekBar barraProgresoCancion;
    int tiempoTotal;
    int currentTime;
    ImageView botonRepetir;
    boolean isRepeating;
    boolean isPlaying;
    boolean firstTimePlaying;
    Random ramdomNumber;
    Cancion currentSong;
    int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            currentTime = savedInstanceState.getInt("currentPosition");
            isRepeating = savedInstanceState.getBoolean("isRepeating");
            isPlaying = savedInstanceState.getBoolean("isPlaying");
            firstTimePlaying = false;

            //mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
        } else {
            firstTimePlaying = true;
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
        botonAnterior = findViewById(R.id.boton_anterior);
        botonSiguiente = findViewById(R.id.boton_siguiente);
        barraProgresoCancion = findViewById(R.id.barra_progreso_cancion);
        tiempoTranscurrido = findViewById(R.id.tiempo_transcurrido);
        tiempoRestante = findViewById(R.id.tiempo_restante);
        botonRepetir = findViewById(R.id.boton_repetir_cancion);
    }

    public void getPlayer(){
        if (firstTimePlaying) {
            currentPosition = Integer.parseInt(info[4]);
        }
        playBackMusic();


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
                    isPlaying = true;
                } else{
                    botonReproducir.setImageResource(R.drawable.ic_play_arrow);
                    mediaPlayer.pause();
                    isPlaying = false;
                }
            }
        });


        botonAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTime = 0;
                isPlaying = false;
                nextSong();
            }
        });

        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTime = 0;
                isPlaying = false;
                nextSong();
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

    public void playBackMusic() {
        try {
            mediaPlayer = Helpers.getPlayer(getApplicationContext(),InicioFragment.listaCanciones.get(currentPosition).getLocalizacion());
            /*mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(InicioFragment.listaCanciones.get(currentPosition).getLocalizacion());*/
            mediaPlayer.start();


            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    currentTime = 0;
                    isPlaying = false;
                    nextSong();
                }
            });


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

            if ( firstTimePlaying ) {
                if (!isPlaying) {
                    mediaPlayer.start();
                    isPlaying = true;
                    botonReproducir.setImageResource(R.drawable.ic_pause_button);
                } else {
                    mediaPlayer.pause();
                    isPlaying = false;
                    botonReproducir.setImageResource(R.drawable.ic_play_arrow);
                }
            } else{
                if (isPlaying) {
                    mediaPlayer.start();
                    isPlaying = true;
                    botonReproducir.setImageResource(R.drawable.ic_pause_button);
                } else {
                    //mediaPlayer.pause();
                    botonReproducir.setImageResource(R.drawable.ic_play_arrow);
                    isPlaying = false;
                }
            }

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




            textoCancion = findViewById(R.id.nombre_cancion);
            textoArtista = findViewById(R.id.nombre_artista);
            textoAlbum = findViewById(R.id.nombre_album);
            textoCancion.setText(info[0]);
            textoArtista.setText(info[1]);
            textoAlbum.setText(info[3]);
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

            isPlaying = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nextSong(){
        int numOfSong = InicioFragment.listaCanciones.size();

            if (!isRepeating) {
                Random rand = new Random();
                int randomNumber = rand.nextInt(numOfSong);
                while (randomNumber == currentPosition){
                    randomNumber = rand.nextInt(numOfSong);
                }
                currentPosition = randomNumber;
                currentSong = InicioFragment.listaCanciones.get(currentPosition);
            } else {
                currentSong = InicioFragment.listaCanciones.get(currentPosition);
            }

            playBackMusic();

    }





}

