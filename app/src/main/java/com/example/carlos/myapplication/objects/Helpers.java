package com.example.carlos.myapplication.objects;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;

/**
 * Created by Rodrigo Corvera on 7/7/2018.
 */

public class Helpers {

    static MediaPlayer mediaPlayer;

    public static MediaPlayer getPlayer(Context context, String path) {

        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(context, Uri.parse(path));
        }else{
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(context, Uri.parse(path));
        }

        return mediaPlayer;
    }


    public static boolean checkVersion5_1(){
        double version = java.lang.Double.parseDouble(new String(Build.VERSION.RELEASE).replaceAll("(\\d+[.]\\d+)(.*)", "$1"));
        if (version >= 5.1) {
            return true;
        } else{
            return false;
        }
    }


}
