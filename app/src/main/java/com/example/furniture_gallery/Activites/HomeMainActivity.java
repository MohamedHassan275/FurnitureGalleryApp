package com.example.furniture_gallery.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.furniture_gallery.Adapters.CategoryHomeAdapter;
import com.example.furniture_gallery.Model.UserModel.HomeModel;
import com.example.furniture_gallery.Model.UserResponseModel.CategoryHomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.HomeResponseModel;
import com.example.furniture_gallery.R;
import com.example.furniture_gallery.ViewModel.HomeViewModel;
import com.example.furniture_gallery.databinding.ActivityHomeMainBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeMainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityHomeMainBinding homeMainBinding;
    RecyclerView RecyclerViewHome;
    HomeViewModel homeViewModel;
    List<CategoryHomeResponseModel> categoryHomeResponseModels = new ArrayList<>();
    CategoryHomeAdapter categoryHomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeMainBinding = ActivityHomeMainBinding.inflate(getLayoutInflater());
        setContentView(homeMainBinding.getRoot());

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        homeViewModel.getCategoryHome("Bearer 159|Chs7WOMBStS7Dsod5P4ULMrrTKQEkjfuTt5Sbv9w");

        homeMainBinding.SwipeRefreshLayoutCategoryList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                homeViewModel.getCategoryHome("Bearer 159|Chs7WOMBStS7Dsod5P4ULMrrTKQEkjfuTt5Sbv9w");
                homeMainBinding.SwipeRefreshLayoutCategoryList.setRefreshing(false);

            }
        });
        homeMainBinding.tvMoreWatchCategory.setOnClickListener(this);
        homeMainBinding.progressBarCyclicCategoryList.setVisibility(View.VISIBLE);
        homeViewModel.modelMutableLiveData.observe(this, new Observer<HomeModel>() {
            @Override
            public void onChanged(HomeModel homeModel) {
                if(homeModel.getStatus()){
                    homeMainBinding.progressBarCyclicCategoryList.setVisibility(View.GONE);
                    HomeResponseModel homeResponseModel = homeModel.getData();
                    categoryHomeResponseModels = homeResponseModel.getCategories();
                    if (categoryHomeResponseModels.size() > 0){
                        homeMainBinding.progressBarCyclicCategoryList.setVisibility(View.GONE);
                        categoryHomeAdapter = new CategoryHomeAdapter(categoryHomeResponseModels);
                        homeMainBinding.recyclerViewCategoryList.setLayoutManager(new LinearLayoutManager(HomeMainActivity.this,RecyclerView.HORIZONTAL,false));
                        homeMainBinding.recyclerViewCategoryList.setHasFixedSize(true);
                        homeMainBinding.recyclerViewCategoryList.setAdapter(categoryHomeAdapter);

                    }else {
                        homeMainBinding.progressBarCyclicCategoryList.setVisibility(View.GONE);
                        homeMainBinding.tvNoDataCategory.setVisibility(View.VISIBLE);

                    }
                }else {
                    homeMainBinding.progressBarCyclicCategoryList.setVisibility(View.GONE);
                    Toast.makeText(HomeMainActivity.this, "no data with server", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_moreWatchCategory:
                startActivity(new Intent(HomeMainActivity.this,CategoryListActivity.class));
                break;
        }
    }
}