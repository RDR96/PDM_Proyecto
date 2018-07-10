package com.example.carlos.myapplication.objects;

/**
 * Created by Rodrigo Corvera on 8/7/2018.
 */

public class Cancion {

    String titulo;
    String cantante;
    String localizacion;

    public Cancion(String titulo, String cantante, String localizacion) {
        this.titulo = titulo;
        this.cantante = cantante;
        this.localizacion = localizacion;
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
