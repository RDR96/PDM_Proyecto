package com.example.carlos.myapplication.activities;

import android.content.Intent;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.carlos.myapplication.Database.AppDatabase;
import com.example.carlos.myapplication.Database.Entidades.Cancion;
import com.example.carlos.myapplication.R;
import com.example.carlos.myapplication.fragments.InicioFragment;
import com.example.carlos.myapplication.objects.Helpers;

import java.util.ArrayList;
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
    ImageView botonFavorito;
    boolean isRepeating;
    boolean isPlaying;
    boolean firstTimePlaying;
    Random ramdomNumber;
    Cancion currentSong;
    int currentPosition;
    TranslateAnimation slide;
    AppDatabase appDatabase;
    ArrayList<Cancion> listaCanciones;

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
            currentPosition = savedInstanceState.getInt("songPosition");
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
        botonFavorito = findViewById(R.id.boton_favorito);
    }

    public void getPlayer(){

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

        if(Integer.parseInt(info[5]) == 1) {
            listaCanciones = InicioFragment.listaCanciones;
        } else {
            listaCanciones = MainActivity.favorites;
        }

        appDatabase = AppDatabase.getDatabaseInstance(getApplicationContext());

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

                if (slide != null) {
                    slide.cancel();
                }


                if (isPlaying) {
                    isPlaying = false;
                } else{
                    isPlaying = true;
                }
                nextSong();
                checkIfFavorite();
            }
        });


        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTime = 0;

                if (slide != null) {
                    slide.cancel();
                }

                if (isPlaying) {
                    isPlaying = false;
                } else{
                    isPlaying = true;
                }

                nextSong();
                checkIfFavorite();
            }
        });


        checkIfFavorite();


        botonFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new Thread(){
                    public void run(){
                        final int resultado = appDatabase.favoritasDao().verificarFavorito(listaCanciones.get(currentPosition).getId());

                        if(resultado == 1) {
                            appDatabase.favoritasDao().quitarFavorito(listaCanciones.get(currentPosition).getId());
                        } else{
                            appDatabase.favoritasDao().insertarFavorito(listaCanciones.get(currentPosition));
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (resultado == 1) {
                                    botonFavorito.setAlpha(0.3f);
                                } else {
                                    botonFavorito.setAlpha(1f);
                                }
                            }
                        });
                    }
                }.start();
            }
        });

    }

    public void checkIfFavorite(){
        new Thread(){
            public void run(){
                final int resultado = appDatabase.favoritasDao().verificarFavorito(listaCanciones.get(currentPosition).getId());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (resultado == 1) {
                            botonFavorito.setAlpha(1f);
                        } else {
                            botonFavorito.setAlpha(0.3f);
                        }
                    }
                });
            }
        }.start();
    }

    public void datosCancion(Intent intent){
        info = intent.getStringExtra(Intent.EXTRA_TEXT).split("-/");
        if (firstTimePlaying) {
            currentPosition = Integer.parseInt(info[4]);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putBoolean("isRepeating", isRepeating);
        savedInstanceState.putBoolean("isPlaying", false);
        savedInstanceState.putInt("currentPosition",mediaPlayer.getCurrentPosition());
        savedInstanceState.putInt("songPosition", currentPosition);
        // etc.
    }


    //VERSION ORIGINAL


    public void playBackMusic() {
        try {
            mediaPlayer = Helpers.getPlayer(getApplicationContext(), listaCanciones.get(currentPosition).getLocalizacion());
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

                if (!isPlaying) {
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
            moveTextHorizontal(listaCanciones.get(currentPosition).getTitulo());
            //textoCancion.setText(InicioFragment.listaCanciones.get(currentPosition).getTitulo());
            textoArtista.setText(listaCanciones.get(currentPosition).getCantante());
            textoAlbum.setText(listaCanciones.get(currentPosition).getAlbum());
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
        int numOfSong = listaCanciones.size();

        if (!isRepeating) {
            Random rand = new Random();
            int randomNumber = rand.nextInt(numOfSong);
            while (randomNumber == currentPosition){
                randomNumber = rand.nextInt(numOfSong);
            }
            currentPosition = randomNumber;
            currentSong = listaCanciones.get(currentPosition);
        } else {
            currentSong = listaCanciones.get(currentPosition);
        }
        playBackMusic();
    }

    public void moveTextHorizontal(String texto){
        textoCancion.setText(texto);
        Paint textPaint = textoCancion.getPaint();
        String text = textoCancion.getText().toString();//get text
        int width = Math.round(textPaint.measureText(text));//measure the text size
        ViewGroup.LayoutParams params =  textoCancion.getLayoutParams();
        params.width = width;
        textoCancion.setLayoutParams(params); //refine

        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;

        //this is optional. do not scroll if text is shorter than screen width
        //remove this won't effect the scroll
        if (width <= screenWidth) {
            //All text can fit in screen.
            return;
        }
        //set the animation
        TranslateAnimation slide = new TranslateAnimation(0, -width, 0, 0);
        slide.setDuration(10000);
        slide.setStartOffset(2000);
        slide.setRepeatCount(Animation.INFINITE);
        slide.setRepeatMode(Animation.RESTART);
        slide.setFillAfter(true);
        slide.setFillBefore(true);
        slide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        slide.setInterpolator(new LinearInterpolator());
        textoCancion.startAnimation(slide);
    }




}

