package com.example.carlos.myapplication.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.carlos.myapplication.Database.Daos.FavoritasDao;
import com.example.carlos.myapplication.Database.Daos.UsuarioDao;
import com.example.carlos.myapplication.Database.Entidades.Cancion;
import com.example.carlos.myapplication.Database.Entidades.Usuario;

/**
 * Created by Rodrigo Corvera on 11/7/2018.
 */

@Database(entities = {Usuario.class, Cancion.class}, version=2)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase DATABASE_INSTANCE;

    public abstract UsuarioDao usuarioDao();
    public abstract FavoritasDao favoritasDao();

    private static final Object LOCK = new Object();

    public static AppDatabase getDatabaseInstance(Context context) {

        if (DATABASE_INSTANCE == null) {
            synchronized (LOCK){
                if (DATABASE_INSTANCE == null) {
                    DATABASE_INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "share_fun").fallbackToDestructiveMigration().build();
                }
            }
        }
        return DATABASE_INSTANCE;
    }

    public static void destroyInstance () {
        DATABASE_INSTANCE = null;
    }
}
