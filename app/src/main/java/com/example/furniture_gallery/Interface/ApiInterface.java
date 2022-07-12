package com.example.furniture_gallery.Interface;


import com.example.furniture_gallery.Activites.HomeMainActivity;
import com.example.furniture_gallery.Model.UserModel.HomeModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiInterface {
//
    String URL = "https://backend.forhomi.com/api/";
  //  String URL_IMAGE = "https://tayib.net/new_tayip/";

    @GET("home")
    Call<HomeModel> GetDetailsHomeModel(@Header("Authorization") String Authorization);

//    @GET("countries")
//    Call<CountiresModel> getCountries();
//
//    @FormUrlEncoded
//    @POST("governorates_by_country")
//    Call<SpecialitiesDoctorsModel> getGovernoratesByCountry(@Field("country_id") String country_id);
//
//

}


