package com.example.carlos.myapplication.objects;

import com.example.carlos.myapplication.Database.Entidades.Usuario;

/**
 * Created by Rodrigo Corvera on 11/7/2018.
 */

public class RespuestaUsuario {

    private boolean success;

    private Usuario usuario;

    public RespuestaUsuario(boolean success, Usuario usuario) {
        this.success = success;
        this.usuario = usuario;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
