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
    @POST("login/social")
    Call<LoginModel> LoginUserByFaceBook(@Field("provider_type") String provider_type, @Field("provider_id") String provider_id,
            @Field("name") String name, @Field("email") String email);

    @FormUrlEncoded
    @POST("check/social")
    Call<LoginModel> CheckLoginUserBySocial(@Field("provider_type") String provider_type, @Field("provider_id") String provider_id);

    @FormUrlEncoded
    @POST("home/furniture-nearby")
    Call<FurnitureNearByModel> GetFurnitureNearby(@Field("lat") String lat, @Field("lng") String lng);

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

    @FormUrlEncoded
    @POST("register")
    Call<LoginModel> RegisterUser(@Field("name") String name, @Field("email") String email,
                                         @Field("phone") String phone,
                                         @Field("password") String password,
                                         @Field("password_confirmation") String password_confirmation,
                                         @Field("lang") String lang,
                                         @Field("address") String address);

//    @GET("countries")
//    Call<CountiresModel> getCountries();
//
//    @FormUrlEncoded
//    @POST("governorates_by_country")
//    Call<SpecialitiesDoctorsModel> getGovernoratesByCountry(@Field("country_id") String country_id);
//
//

}


