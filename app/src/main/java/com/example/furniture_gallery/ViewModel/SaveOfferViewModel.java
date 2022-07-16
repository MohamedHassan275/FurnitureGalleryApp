package com.example.furniture_gallery.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.furniture_gallery.Model.UserModel.OfferModel;
import com.example.furniture_gallery.Model.UserModel.SaveOfferModel;
import com.example.furniture_gallery.Retrofit_Api.Retrofit_Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveOfferViewModel extends ViewModel {

    public MutableLiveData<SaveOfferModel> saveOfferModelMutableLiveData = new MutableLiveData<>();

    public void GetOffer(String price){
        Call<SaveOfferModel> call = Retrofit_Api.RETROFIT_API_INSTANCE().GetSaveOffer(price);
        call.enqueue(new Callback<SaveOfferModel>() {
            @Override
            public void onResponse(Call<SaveOfferModel> call, Response<SaveOfferModel> response) {
                saveOfferModelMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SaveOfferModel> call, Throwable t) {

            }
        });
    }
}
