package com.example.carlos.myapplication.Database.Entidades;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Rodrigo Corvera on 11/7/2018.
 */


@Entity(tableName = "usuario")
public class Usuario {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String password;

    private String email;

    private int log;

    public Usuario(int id, String name, String password, String email, int log) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.log = log;
    }

    @Ignore
    public Usuario(String name, String password, String email, int log) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.log = log;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLog() {
        return log;
    }

    public void setLog(int log) {
        this.log = log;
    }
}
