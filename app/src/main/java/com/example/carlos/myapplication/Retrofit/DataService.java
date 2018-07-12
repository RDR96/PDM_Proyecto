package com.example.carlos.myapplication.Retrofit;

import android.media.session.MediaSession;

import com.example.carlos.myapplication.Database.Entidades.Usuario;
import com.example.carlos.myapplication.objects.RespuestaUsuario;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Rodrigo Corvera on 11/7/2018.
 */

public interface DataService {

    @FormUrlEncoded
    @POST("user/create")
    Call<Usuario> registro(@Field("username") String username,
                                @Field("email") String email,
                                @Field("password") String password);


    @FormUrlEncoded
    @POST("authenticate/")
    Call<RespuestaUsuario> inicio_sesion(@Field("username") String username,
                                         @Field("password") String password);



}
