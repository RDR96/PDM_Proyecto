package com.example.carlos.myapplication.Database.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.carlos.myapplication.Database.Entidades.Cancion;

import java.util.List;

/**
 * Created by Rodrigo Corvera on 11/7/2018.
 */

@Dao
public interface FavoritasDao {

    @Insert
    void insertarFavorito(Cancion favoritas);

    @Delete
    void eliminarFavorito(Cancion favoritas);

    @Query("SELECT * FROM canciones_favoritas")
    List<Cancion> obtenerFavoritos();

    @Query("SELECT COUNT(id) FROM canciones_favoritas WHERE id =:id")
    int verificarFavorito(String id);

    @Query("DELETE FROM canciones_favoritas WHERE id=:id")
    void quitarFavorito(String id);

    @Query("DELETE FROM canciones_favoritas")
    void limpiarFavoritos();


}