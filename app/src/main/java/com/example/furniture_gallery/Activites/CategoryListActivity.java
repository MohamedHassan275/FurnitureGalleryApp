package com.example.furniture_gallery.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.furniture_gallery.Adapters.CategoryHomeAdapter;
import com.example.furniture_gallery.Core.Language.Language;
import com.example.furniture_gallery.Core.SharedPrefrance.PreferenceHelperChoseLanguage;
import com.example.furniture_gallery.Model.UserModel.HomeModel;
import com.example.furniture_gallery.Model.UserResponseModel.CategoryHomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.HomeResponseModel;
import com.example.furniture_gallery.R;
import com.example.furniture_gallery.ViewModel.HomeViewModel;
import com.example.furniture_gallery.databinding.ActivityCategoryListBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoryListActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityCategoryListBinding categoryListBinding;
    HomeViewModel homeViewModel;
    CategoryHomeAdapter categoryHomeAdapter;
    List<CategoryHomeResponseModel>categoryHomeResponseModels = new ArrayList<>();
    PreferenceHelperChoseLanguage preferenceHelperChoseLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceHelperChoseLanguage = PreferenceHelperChoseLanguage.getInstans(this);
        Language.changeLanguage(this,preferenceHelperChoseLanguage.getLang());
        categoryListBinding = ActivityCategoryListBinding.inflate(getLayoutInflater());
        setContentView(categoryListBinding.getRoot());

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getDetailsHome("Bearer 159|Chs7WOMBStS7Dsod5P4ULMrrTKQEkjfuTt5Sbv9w",preferenceHelperChoseLanguage.getLang());

        categoryListBinding.SwipeRefreshLayoutCategoryList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                homeViewModel.getDetailsHome("Bearer 159|Chs7WOMBStS7Dsod5P4ULMrrTKQEkjfuTt5Sbv9w",preferenceHelperChoseLanguage.getLang());
                categoryListBinding.SwipeRefreshLayoutCategoryList.setRefreshing(false);

            }
        });

        categoryListBinding.progressBarCyclicCategoryList.setVisibility(View.VISIBLE);
        homeViewModel.homeModelMutableLiveData.observe(this, new Observer<HomeModel>() {
            @Override
            public void onChanged(HomeModel homeModel) {
                if(homeModel.getStatus()){
                    categoryListBinding.progressBarCyclicCategoryList.setVisibility(View.GONE);
                    HomeResponseModel homeResponseModel = homeModel.getData();
                    categoryHomeResponseModels = homeResponseModel.getCategories();
                    if (categoryHomeResponseModels.size() > 0){
                        categoryListBinding.progressBarCyclicCategoryList.setVisibility(View.GONE);
                        categoryHomeAdapter = new CategoryHomeAdapter(categoryHomeResponseModels);
                        categoryListBinding.recyclerViewCategoryList.setLayoutManager(new GridLayoutManager(CategoryListActivity.this,4));
                        categoryListBinding.recyclerViewCategoryList.setHasFixedSize(true);
                        categoryListBinding.recyclerViewCategoryList.setAdapter(categoryHomeAdapter);

                    }else {
                        categoryListBinding.progressBarCyclicCategoryList.setVisibility(View.GONE);
                        categoryListBinding.tvNoDataCategory.setVisibility(View.VISIBLE);

                    }
                }else {
                    categoryListBinding.progressBarCyclicCategoryList.setVisibility(View.GONE);
                    Toast.makeText(CategoryListActivity.this, "no data with server", Toast.LENGTH_SHORT).show();
                }
            }
        });

        categoryListBinding.imageBackCategory.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageBackCategory:
                startActivity(new Intent(CategoryListActivity.this,HomeMainActivity.class));
                break;

        }
    }
}