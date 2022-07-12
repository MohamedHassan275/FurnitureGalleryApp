package com.example.furniture_gallery.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.furniture_gallery.Model.UserModel.HomeModel;
import com.example.furniture_gallery.Model.UserResponseModel.HomeResponseModel;
import com.example.furniture_gallery.Retrofit_Api.Retrofit_Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

   public MutableLiveData<HomeModel> modelMutableLiveData = new MutableLiveData<>();

    public void getCategoryHome(String AccessToken){
        Call<HomeModel> call = Retrofit_Api.RETROFIT_API_INSTANCE().GetDetailsHomeModel(AccessToken);
        call.enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
                modelMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<HomeModel> call, Throwable t) {

            }
        });
    }
}
