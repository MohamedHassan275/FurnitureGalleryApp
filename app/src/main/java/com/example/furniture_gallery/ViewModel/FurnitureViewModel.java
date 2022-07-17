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

public class FurnitureViewModel extends ViewModel {

   public MutableLiveData<FurnitureNearByModel> furnitureNearByModelMutableLiveData = new MutableLiveData<>();


    public void getFurnitureNearBy(String lat,String lng){
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


}
