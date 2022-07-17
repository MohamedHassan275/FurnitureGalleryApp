package com.example.furniture_gallery.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.furniture_gallery.Model.UserModel.DiscountsModel;
import com.example.furniture_gallery.Model.UserModel.SaveOfferModel;
import com.example.furniture_gallery.Retrofit_Api.Retrofit_Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscountsViewModel extends ViewModel {

    public MutableLiveData<DiscountsModel> discountsModelMutableLiveData = new MutableLiveData<>();

    public void GetDiscount(String price){
        Call<DiscountsModel> call = Retrofit_Api.RETROFIT_API_INSTANCE().GetDiscounts(price);
        call.enqueue(new Callback<DiscountsModel>() {
            @Override
            public void onResponse(Call<DiscountsModel> call, Response<DiscountsModel> response) {
                discountsModelMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DiscountsModel> call, Throwable t) {

            }
        });
    }
}
