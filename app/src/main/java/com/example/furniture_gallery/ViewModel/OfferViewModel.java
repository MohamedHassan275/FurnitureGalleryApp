package com.example.furniture_gallery.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.furniture_gallery.Model.UserModel.CategoryModel;
import com.example.furniture_gallery.Model.UserModel.OfferModel;
import com.example.furniture_gallery.Retrofit_Api.Retrofit_Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferViewModel extends ViewModel {

    public MutableLiveData<OfferModel> offerModelMutableLiveData = new MutableLiveData<>();

    public void GetOffer(String price){
        Call<OfferModel> call = Retrofit_Api.RETROFIT_API_INSTANCE().GetOffer(price);
        call.enqueue(new Callback<OfferModel>() {
            @Override
            public void onResponse(Call<OfferModel> call, Response<OfferModel> response) {
                offerModelMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<OfferModel> call, Throwable t) {

            }
        });
    }
}
