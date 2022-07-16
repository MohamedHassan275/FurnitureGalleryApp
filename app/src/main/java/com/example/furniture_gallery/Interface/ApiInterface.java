package com.example.furniture_gallery.Interface;


import com.example.furniture_gallery.Activites.HomeMainActivity;
import com.example.furniture_gallery.Model.UserModel.CategoryModel;
import com.example.furniture_gallery.Model.UserModel.DiscountsModel;
import com.example.furniture_gallery.Model.UserModel.FurnitureNearByModel;
import com.example.furniture_gallery.Model.UserModel.HomeModel;
import com.example.furniture_gallery.Model.UserModel.LoginModel;
import com.example.furniture_gallery.Model.UserModel.LogoutModel;
import com.example.furniture_gallery.Model.UserModel.OfferModel;
import com.example.furniture_gallery.Model.UserModel.SaveOfferModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
//
    String URL = "https://backend.forhomi.com/api/";
  //  String URL_IMAGE = "https://tayib.net/new_tayip/";

    @GET("home")
    Call<HomeModel> GetDetailsHomeModel(@Header("Authorization") String Authorization,@Header("Accept-Language") String AcceptLanguage);

    @FormUrlEncoded
    @POST("login")
    Call<LoginModel> LoginUser(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("home/furniture-nearby")
    Call<FurnitureNearByModel> GetFurnitureNearby(@Field("lat") double lat, @Field("lng") double lng);

    @GET("logout")
    Call<LogoutModel> LogoutApp(@Header("Authorization") String Authorization);

    @GET("home/categories")
    Call<CategoryModel> GetCategories();

    @GET("home/offers")
    Call<OfferModel> GetOffer(@Query("price") String price);

    @GET("home/saves")
    Call<SaveOfferModel> GetSaveOffer(@Query("price") String price);

    @GET("home/discounts")
    Call<DiscountsModel> GetDiscounts(@Query("price") String price);

//    @GET("countries")
//    Call<CountiresModel> getCountries();
//
//    @FormUrlEncoded
//    @POST("governorates_by_country")
//    Call<SpecialitiesDoctorsModel> getGovernoratesByCountry(@Field("country_id") String country_id);
//
//

}


