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
import com.example.furniture_gallery.Model.UserModel.CategoryModel;
import com.example.furniture_gallery.Model.UserModel.HomeModel;
import com.example.furniture_gallery.Model.UserResponseModel.CategoryHomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.HomeResponseModel;
import com.example.furniture_gallery.R;
import com.example.furniture_gallery.ViewModel.CategoriesViewModel;
import com.example.furniture_gallery.ViewModel.HomeViewModel;
import com.example.furniture_gallery.databinding.ActivityCategoryListBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoryListActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityCategoryListBinding categoryListBinding;
    CategoriesViewModel categoriesViewModel;
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

        categoriesViewModel = new ViewModelProvider(this).get(CategoriesViewModel.class);
        categoriesViewModel.GetCategories();

        categoryListBinding.SwipeRefreshLayoutCategoryList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                categoriesViewModel.GetCategories();
                categoryListBinding.SwipeRefreshLayoutCategoryList.setRefreshing(false);

            }
        });

        categoryListBinding.progressBarCyclicCategoryList.setVisibility(View.VISIBLE);
        categoriesViewModel.categoryModelMutableLiveData.observe(this, new Observer<CategoryModel>() {
            @Override
            public void onChanged(CategoryModel categoryModel) {
                if(categoryModel.getStatus()){
                    categoryListBinding.progressBarCyclicCategoryList.setVisibility(View.GONE);
                    categoryHomeResponseModels = categoryModel.getCategories();
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