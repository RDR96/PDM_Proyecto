package com.example.carlos.myapplication.objects;

/**
 * Created by Deryan Cruz on 07/07/2018.
 */

public class Artista {

    private String nombreArtista;
    private String fotoArtista;

    public Artista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getFotoArtista() {
        return fotoArtista;
    }

    public void setFotoArtista(String fotoArtista) {
        this.fotoArtista = fotoArtista;
    }
}
