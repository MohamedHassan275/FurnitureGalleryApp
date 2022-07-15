package com.example.furniture_gallery.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.furniture_gallery.Model.UserModel.LoginModel;
import com.example.furniture_gallery.Retrofit_Api.Retrofit_Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<LoginModel> loginModelMutableLiveData = new MutableLiveData<>();

    public void LoginUser(String userName, String password){
        Call<LoginModel> call = Retrofit_Api.RETROFIT_API_INSTANCE().LoginUser(userName,password);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                if (response.code() == 200) {
                    loginModelMutableLiveData.setValue(response.body());
                } else if (response.code() != 422) {
                    loginModelMutableLiveData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

            }
        });
    }
}
