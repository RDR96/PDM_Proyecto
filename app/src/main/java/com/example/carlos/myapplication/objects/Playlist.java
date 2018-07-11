package com.example.carlos.myapplication.objects;

/**
 * Created by Deryan Cruz on 07/07/2018.
 */

public class Playlist {
    private String nombrePlaylist;
    private String autorPlaylist;
    private String albumPlaylist;
    private String imagePlaylist;

    public Playlist(String nombrePlaylist, String autorPlaylist, String albumPlaylist) {
        this.nombrePlaylist = nombrePlaylist;
        this.autorPlaylist = autorPlaylist;
        this.albumPlaylist = albumPlaylist;
    }

    public String getNombrePlaylist() {
        return nombrePlaylist;
    }

    public void setNombrePlaylist(String nombrePlaylist) {
        this.nombrePlaylist = nombrePlaylist;
    }

    public String getAutorPlaylist() {
        return autorPlaylist;
    }

    public void setAutorPlaylist(String autorPlaylist) {
        this.autorPlaylist = autorPlaylist;
    }

    public String getAlbumPlaylist() {
        return albumPlaylist;
    }

    public void setAlbumPlaylist(String albumPlaylist) {
        this.albumPlaylist = albumPlaylist;
    }

    public String getImagePlaylist() {
        return imagePlaylist;
    }

    public void setImagePlaylist(String imagePlaylist) {
        this.imagePlaylist = imagePlaylist;
    }
}
