package com.example.carlos.myapplication.Objects;

public class Notificaciones {

    String etiqueta;
    String usuario;
    String te_han_etiquetado;
    String nombre_cancion;
    String Artista;
    String Album;
    String tiempo;

    public Notificaciones(String etiqueta, String usuario, String te_han_etiquetado, String nombre_cancion, String artista, String album, String tiempo) {
        this.etiqueta = etiqueta;
        this.usuario = usuario;
        this.te_han_etiquetado = te_han_etiquetado;
        this.nombre_cancion = nombre_cancion;
        this.Artista = artista;
        this.Album = album;
        this.tiempo = tiempo;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTe_han_etiquetado() {
        return te_han_etiquetado;
    }

    public void setTe_han_etiquetado(String te_han_etiquetado) {
        this.te_han_etiquetado = te_han_etiquetado;
    }

    public String getNombre_cancion() {
        return nombre_cancion;
    }

    public void setNombre_cancion(String nombre_cancion) {
        this.nombre_cancion = nombre_cancion;
    }

    public String getArtista() {
        return Artista;
    }

    public void setArtista(String artista) {
        this.Artista = artista;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        this.Album = album;
    }

    public String getTiempo(){
        return  tiempo;
    }

    public void setTiempo(String tiempo){
        this.tiempo = tiempo;
    }
}
