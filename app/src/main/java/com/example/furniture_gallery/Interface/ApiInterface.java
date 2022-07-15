package com.example.furniture_gallery.Interface;


import com.example.furniture_gallery.Activites.HomeMainActivity;
import com.example.furniture_gallery.Model.UserModel.HomeModel;
import com.example.furniture_gallery.Model.UserModel.LoginModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {
//
    String URL = "https://backend.forhomi.com/api/";
  //  String URL_IMAGE = "https://tayib.net/new_tayip/";

    @GET("home")
    Call<HomeModel> GetDetailsHomeModel(@Header("Authorization") String Authorization,@Header("Accept-Language") String AcceptLanguage);

    @FormUrlEncoded
    @POST("login")
    Call<LoginModel> LoginUser(@Field("username") String username, @Field("password") String password);


//    @GET("countries")
//    Call<CountiresModel> getCountries();
//
//    @FormUrlEncoded
//    @POST("governorates_by_country")
//    Call<SpecialitiesDoctorsModel> getGovernoratesByCountry(@Field("country_id") String country_id);
//
//

}


