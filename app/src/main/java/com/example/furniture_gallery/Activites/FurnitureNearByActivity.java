package com.example.furniture_gallery.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.furniture_gallery.Adapters.CategoryHomeAdapter;
import com.example.furniture_gallery.Adapters.DiscountHomeAdapter;
import com.example.furniture_gallery.Adapters.FurnitureNearByHomeAdapter;
import com.example.furniture_gallery.Adapters.SavesDiscountHomeAdapter;
import com.example.furniture_gallery.Adapters.SavesOfferHomeAdapter;
import com.example.furniture_gallery.Core.Language.Language;
import com.example.furniture_gallery.Core.SharedPrefrance.PreferenceHelperChoseLanguage;
import com.example.furniture_gallery.Model.UserModel.HomeModel;
import com.example.furniture_gallery.Model.UserResponseModel.BranchTypeHomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.HomeResponseModel;
import com.example.furniture_gallery.R;
import com.example.furniture_gallery.ViewModel.HomeViewModel;
import com.example.furniture_gallery.databinding.ActivityFurnitureNearByBinding;

import java.util.ArrayList;
import java.util.List;

public class FurnitureNearByActivity extends AppCompatActivity {

    ActivityFurnitureNearByBinding furnitureNearByBinding;
    HomeViewModel homeViewModel;
    List<BranchTypeHomeResponseModel> branchTypeHomeResponseModelArrayList = new ArrayList<>();
    FurnitureNearByHomeAdapter furnitureNearByHomeAdapter;
    PreferenceHelperChoseLanguage preferenceHelperChoseLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceHelperChoseLanguage = PreferenceHelperChoseLanguage.getInstans(this);
        Language.changeLanguage(this,preferenceHelperChoseLanguage.getLang());
        furnitureNearByBinding = ActivityFurnitureNearByBinding.inflate(getLayoutInflater());
        setContentView(furnitureNearByBinding.getRoot());

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        homeViewModel.getDetailsHome("Bearer 159|Chs7WOMBStS7Dsod5P4ULMrrTKQEkjfuTt5Sbv9w", preferenceHelperChoseLanguage.getLang());

        furnitureNearByBinding.progressBarCyclicFurnitureNearBy.setVisibility(View.VISIBLE);

        furnitureNearByBinding.SwipeRefreshLayoutFurnitureNearBy.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                homeViewModel.getDetailsHome("Bearer 159|Chs7WOMBStS7Dsod5P4ULMrrTKQEkjfuTt5Sbv9w", preferenceHelperChoseLanguage.getLang());
                furnitureNearByBinding.SwipeRefreshLayoutFurnitureNearBy.setRefreshing(false);

            }
        });
        homeViewModel.modelMutableLiveData.observe(this, new Observer<HomeModel>() {
            @Override
            public void onChanged(HomeModel homeModel) {
                if (homeModel.getStatus()) {
                    HomeResponseModel homeResponseModel = homeModel.getData();
                    branchTypeHomeResponseModelArrayList = homeResponseModel.getBranchType();
                    if (branchTypeHomeResponseModelArrayList.size() > 0) {
                        furnitureNearByBinding.progressBarCyclicFurnitureNearBy.setVisibility(View.GONE);
                        furnitureNearByHomeAdapter = new FurnitureNearByHomeAdapter( branchTypeHomeResponseModelArrayList);
                        furnitureNearByBinding.recyclerViewFurnitureNearBy.setLayoutManager(new GridLayoutManager(FurnitureNearByActivity.this,2));
                        furnitureNearByBinding.recyclerViewFurnitureNearBy.setHasFixedSize(true);
                        furnitureNearByBinding.recyclerViewFurnitureNearBy.setAdapter(furnitureNearByHomeAdapter);

                    } else {
                        furnitureNearByBinding.progressBarCyclicFurnitureNearBy.setVisibility(View.GONE);
                        furnitureNearByBinding.tvNoDataFurnitureNearBy.setVisibility(View.VISIBLE);

                    }
                } else {
                    furnitureNearByBinding.progressBarCyclicFurnitureNearBy.setVisibility(View.GONE);
                    Toast.makeText(FurnitureNearByActivity.this, "no data with server", Toast.LENGTH_SHORT).show();
                }
            }
        });

        furnitureNearByBinding.imageBackFurnitureNearBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(FurnitureNearByActivity.this,HomeMainActivity.class));
            }
        });

    }
}