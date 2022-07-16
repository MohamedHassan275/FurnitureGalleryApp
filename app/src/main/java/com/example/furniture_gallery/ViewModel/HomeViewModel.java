package com.example.furniture_gallery.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.furniture_gallery.Model.UserModel.FurnitureNearByModel;
import com.example.furniture_gallery.Model.UserModel.HomeModel;
import com.example.furniture_gallery.Model.UserModel.LogoutModel;
import com.example.furniture_gallery.Retrofit_Api.Retrofit_Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

   public MutableLiveData<HomeModel> homeModelMutableLiveData = new MutableLiveData<>();
   public MutableLiveData<FurnitureNearByModel> furnitureNearByModelMutableLiveData = new MutableLiveData<>();
   public MutableLiveData<LogoutModel> logoutModelMutableLiveData = new MutableLiveData<>();

    public void getDetailsHome(String AccessToken,String Language){
        Call<HomeModel> call = Retrofit_Api.RETROFIT_API_INSTANCE().GetDetailsHomeModel(AccessToken,Language);
        call.enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
                homeModelMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<HomeModel> call, Throwable t) {

            }
        });
    }

    public void getFurnitureNearBy(double lat,double lng){
        Call<FurnitureNearByModel> call = Retrofit_Api.RETROFIT_API_INSTANCE().GetFurnitureNearby(lat,lng);
        call.enqueue(new Callback<FurnitureNearByModel>() {
            @Override
            public void onResponse(Call<FurnitureNearByModel> call, Response<FurnitureNearByModel> response) {
                furnitureNearByModelMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<FurnitureNearByModel> call, Throwable t) {

            }
        });
    }

    public void logoutApp(String AccessToken){
        Call<LogoutModel> call = Retrofit_Api.RETROFIT_API_INSTANCE().LogoutApp(AccessToken);
        call.enqueue(new Callback<LogoutModel>() {
            @Override
            public void onResponse(Call<LogoutModel> call, Response<LogoutModel> response) {
                logoutModelMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<LogoutModel> call, Throwable t) {

            }
        });
    }

}
