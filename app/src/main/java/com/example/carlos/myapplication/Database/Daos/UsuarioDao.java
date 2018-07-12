package com.example.carlos.myapplication.Database.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.carlos.myapplication.Database.Entidades.Usuario;

/**
 * Created by Rodrigo Corvera on 11/7/2018.
 */

@Dao
public interface UsuarioDao {

    @Insert
    void insertarUsuario(Usuario usuario);

    @Delete
    void eliminarUsuario(Usuario usuario);

    @Update
    void actualizarUsuario(Usuario usuario);

    @Query("DELETE FROM usuario")
    void limpiarTablaUsuario();

    @Query("SELECT * FROM usuario WHERE log=1")
    Usuario verificarSesion();

    @Query("SELECT count(*) FROM usuario WHERE log=1")
    int verificarUsuarioConectado();

    @Query("SELECT * FROM usuario WHERE password=:password AND email=:email")
    Usuario obtenerUsuario(String password, String email);

    @Query("SELECT COUNT(id) FROM usuario WHERE password=:password AND email=:email")
    int verificarUsuario(String password, String email);


}
