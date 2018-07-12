package com.example.carlos.myapplication.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rodrigo Corvera on 11/7/2018.
 */

public class ApiAdapter {

    private static DataService API_HANDLER;

    private static String baseUrl = "https://frozen-brook-56410.herokuapp.com/api/";

    public static DataService getApiHandler () {

        /*HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(logging);*/

        if ( API_HANDLER == null ) {

            Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            API_HANDLER = retrofit.create(DataService.class);

        }

        return API_HANDLER;
    }

}
