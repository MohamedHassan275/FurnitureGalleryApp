package com.example.furniture_gallery.Retrofit_Api;

import com.example.furniture_gallery.Interface.ApiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_Api {

    public static ApiInterface RETROFIT_API_INSTANCE() {
        ApiInterface api = null;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return api = retrofit.create(ApiInterface.class);

    }

    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit (String url){

        if(retrofit==null){

            retrofit = new Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
