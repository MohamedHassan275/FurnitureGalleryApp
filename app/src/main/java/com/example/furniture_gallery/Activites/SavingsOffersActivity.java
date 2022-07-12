package com.example.furniture_gallery.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.furniture_gallery.Adapters.CategoryHomeAdapter;
import com.example.furniture_gallery.Adapters.SavesOfferHomeAdapter;
import com.example.furniture_gallery.Model.UserModel.HomeModel;
import com.example.furniture_gallery.Model.UserResponseModel.HomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.OfferHomeResponseModel;
import com.example.furniture_gallery.R;
import com.example.furniture_gallery.ViewModel.HomeViewModel;
import com.example.furniture_gallery.databinding.ActivitySavingsOffersBinding;

import java.util.ArrayList;
import java.util.List;

public class SavingsOffersActivity extends AppCompatActivity implements View.OnClickListener {

    ActivitySavingsOffersBinding savesOfferBinding;
    HomeViewModel homeViewModel;
    List<OfferHomeResponseModel> offerHomeResponseModels = new ArrayList<>();
    SavesOfferHomeAdapter savesOfferHomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savesOfferBinding = ActivitySavingsOffersBinding.inflate(getLayoutInflater());
        setContentView(savesOfferBinding.getRoot());

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        homeViewModel.getDetailsHome("Bearer 159|Chs7WOMBStS7Dsod5P4ULMrrTKQEkjfuTt5Sbv9w");

        savesOfferBinding.progressBarCyclicSavesOffer.setVisibility(View.VISIBLE);

        homeViewModel.modelMutableLiveData.observe(this, new Observer<HomeModel>() {
            @Override
            public void onChanged(HomeModel homeModel) {
                if(homeModel.getStatus()){
                    HomeResponseModel homeResponseModel = homeModel.getData();
                    offerHomeResponseModels = homeResponseModel.getOffers();
                    if (offerHomeResponseModels.size() > 0){
                        savesOfferBinding.progressBarCyclicSavesOffer.setVisibility(View.GONE);
                        savesOfferHomeAdapter = new SavesOfferHomeAdapter(SavingsOffersActivity.this,offerHomeResponseModels);
                        savesOfferBinding.recyclerViewCategoryList.setLayoutManager(new LinearLayoutManager(SavingsOffersActivity.this, RecyclerView.VERTICAL,false));
                        savesOfferBinding.recyclerViewCategoryList.setHasFixedSize(true);
                        savesOfferBinding.recyclerViewCategoryList.setAdapter(savesOfferHomeAdapter);

                    }else {
                        savesOfferBinding.progressBarCyclicSavesOffer.setVisibility(View.GONE);
                        savesOfferBinding.tvNoDataCategory.setVisibility(View.VISIBLE);

                    }
                }else {
                    savesOfferBinding.progressBarCyclicSavesOffer.setVisibility(View.GONE);
                    Toast.makeText(SavingsOffersActivity.this, "no data with server", Toast.LENGTH_SHORT).show();
                }
            }
        });

        savesOfferBinding.imageBackSavesOffer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageBackSavesOffer:
                startActivity(new Intent(SavingsOffersActivity.this,HomeMainActivity.class));
                break;
        }
    }
}