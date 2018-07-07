package com.example.carlos.myapplication.Objects;

public class Inicio {

    String usuario;
    String nombre_Cancion;
    String artista;
    String album;

    public Inicio(String usuario, String nombre_Cancion, String artista, String album) {
        this.usuario = usuario;
        this.nombre_Cancion = nombre_Cancion;
        this.artista = artista;
        this.album = album;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre_Cancion() {
        return nombre_Cancion;
    }

    public void setNombre_Cancion(String nombre_Cancion) {
        this.nombre_Cancion = nombre_Cancion;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
