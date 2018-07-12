package com.example.carlos.myapplication.Database.Entidades;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Rodrigo Corvera on 11/7/2018.
 */

@Entity(tableName = "canciones_favoritas")
public class Cancion{

    @PrimaryKey
    @NonNull
    private String id;
    private String titulo;
    private String cantante;
    private String localizacion;
    private String album;

    public Cancion(@NonNull String id, String titulo, String cantante, String localizacion, String album) {
        this.id = id;
        this.titulo = titulo;
        this.cantante = cantante;
        this.localizacion = localizacion;
        this.album = album;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
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

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
