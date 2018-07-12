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

    public static String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /* Retorna un hash MD5 a partir de un texto */
    public static String md5(String txt) {
        return Helpers.getHash(txt, "MD5");
    }

    /* Retorna un hash SHA1 a partir de un texto */
    public static String sha1(String txt) {
        return Helpers.getHash(txt, "SHA1");
    }


}
