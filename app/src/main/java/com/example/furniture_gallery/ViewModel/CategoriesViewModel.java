package com.example.furniture_gallery.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.furniture_gallery.Model.UserModel.CategoryModel;
import com.example.furniture_gallery.Retrofit_Api.Retrofit_Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesViewModel extends ViewModel {

    public MutableLiveData<CategoryModel> categoryModelMutableLiveData = new MutableLiveData<>();

    public void GetCategories(){
        Call<CategoryModel> call = Retrofit_Api.RETROFIT_API_INSTANCE().GetCategories();
        call.enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                categoryModelMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {

            }
        });
    }
}
