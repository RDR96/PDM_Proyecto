package com.example.carlos.myapplication.objects;

/**
 * Created by Rodrigo Corvera on 8/7/2018.
 */

public class Cancion {

    String titulo;
    String cantante;
    String localizacion;
    String album;
    String id;

    public Cancion(String titulo, String cantante, String localizacion, String album, String id) {
        this.titulo = titulo;
        this.cantante = cantante;
        this.localizacion = localizacion;
        this.album = album;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCantante() {
        return cantante;
    }

    public void setCantante(String cantante) {
        this.cantante = cantante;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }
}
