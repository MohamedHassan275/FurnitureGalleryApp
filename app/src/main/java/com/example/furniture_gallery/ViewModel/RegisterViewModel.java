package com.example.furniture_gallery.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.furniture_gallery.Model.UserModel.LoginModel;
import com.example.furniture_gallery.Retrofit_Api.Retrofit_Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {

    public MutableLiveData<LoginModel> registerModelMutableLiveData = new MutableLiveData<>();

    public void RegisterUser(String name, String email, String phone, String password, String confirmPassword, String lang, String address){
        Call<LoginModel> call = Retrofit_Api.RETROFIT_API_INSTANCE().RegisterUser(name,email,phone,password,confirmPassword,lang,address);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                if (response.code() == 200) {
                    registerModelMutableLiveData.setValue(response.body());
                } else if (response.code() == 422) {
                    registerModelMutableLiveData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

            }
        });
    }
}
