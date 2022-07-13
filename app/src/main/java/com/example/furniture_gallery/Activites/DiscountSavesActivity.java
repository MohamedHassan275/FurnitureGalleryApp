package com.example.furniture_gallery.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.furniture_gallery.Adapters.SavesDiscountHomeAdapter;
import com.example.furniture_gallery.Model.UserModel.HomeModel;
import com.example.furniture_gallery.Model.UserResponseModel.HomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.SavesDiscountHomeResponseModel;
import com.example.furniture_gallery.R;
import com.example.furniture_gallery.ViewModel.HomeViewModel;
import com.example.furniture_gallery.databinding.ActivityDiscountSavesBinding;
import com.example.furniture_gallery.databinding.DiscountSavesListViewBinding;

import java.util.ArrayList;
import java.util.List;

public class DiscountSavesActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityDiscountSavesBinding discountSavesBinding;
    HomeViewModel homeViewModel;
    List<SavesDiscountHomeResponseModel> savesDiscountHomeResponseModels = new ArrayList<>();
    SavesDiscountHomeAdapter savesDiscountHomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        discountSavesBinding = ActivityDiscountSavesBinding.inflate(getLayoutInflater());
        setContentView(discountSavesBinding.getRoot());


        homeViewModel.getDetailsHome("Bearer 159|Chs7WOMBStS7Dsod5P4ULMrrTKQEkjfuTt5Sbv9w");

        discountSavesBinding.progressBarCyclicDiscountSaves.setVisibility(View.VISIBLE);

        homeViewModel.modelMutableLiveData.observe(this, new Observer<HomeModel>() {
            @Override
            public void onChanged(HomeModel homeModel) {
                if(homeModel.getStatus()){
                    HomeResponseModel homeResponseModel = homeModel.getData();
                    savesDiscountHomeResponseModels = homeResponseModel.getSaves();
                    if (savesDiscountHomeResponseModels.size() > 0){
                        discountSavesBinding.progressBarCyclicDiscountSaves.setVisibility(View.GONE);
                        savesDiscountHomeAdapter = new SavesDiscountHomeAdapter(DiscountSavesActivity.this,savesDiscountHomeResponseModels);
                        discountSavesBinding.recyclerViewDiscountSaves.setLayoutManager(new LinearLayoutManager(DiscountSavesActivity.this, RecyclerView.HORIZONTAL,false));
                        discountSavesBinding.recyclerViewDiscountSaves.setHasFixedSize(true);
                        discountSavesBinding.recyclerViewDiscountSaves.setAdapter(savesDiscountHomeAdapter);

                    }else {
                        discountSavesBinding.progressBarCyclicDiscountSaves.setVisibility(View.GONE);
                        discountSavesBinding.tvNoDataDiscountSaves.setVisibility(View.VISIBLE);

                    }
                }else {
                    discountSavesBinding.progressBarCyclicDiscountSaves.setVisibility(View.GONE);
                    Toast.makeText(DiscountSavesActivity.this, "no data with server", Toast.LENGTH_SHORT).show();
                }
            }
        });

        discountSavesBinding.imageBackDiscountSaves.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageBackDiscountSaves:
                startActivity(new Intent(DiscountSavesActivity.this,HomeMainActivity.class));
                break;
        }
    }
}