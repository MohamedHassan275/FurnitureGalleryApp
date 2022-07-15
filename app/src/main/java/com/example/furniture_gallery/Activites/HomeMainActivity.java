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
import com.example.furniture_gallery.Adapters.DiscountHomeAdapter;
import com.example.furniture_gallery.Adapters.FurnitureNearByHomeAdapter;
import com.example.furniture_gallery.Adapters.SavesDiscountHomeAdapter;
import com.example.furniture_gallery.Adapters.SavesOfferHomeAdapter;
import com.example.furniture_gallery.Core.Language.Language;
import com.example.furniture_gallery.Core.SharedPrefrance.PreferenceHelper;
import com.example.furniture_gallery.Core.SharedPrefrance.PreferenceHelperChoseLanguage;
import com.example.furniture_gallery.Model.UserModel.HomeModel;
import com.example.furniture_gallery.Model.UserResponseModel.BranchTypeHomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.CategoryHomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.DiscountHomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.HomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.OfferHomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.SavesDiscountHomeResponseModel;
import com.example.furniture_gallery.R;
import com.example.furniture_gallery.ViewModel.HomeViewModel;
import com.example.furniture_gallery.databinding.ActivityHomeMainBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeMainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityHomeMainBinding homeMainBinding;
    HomeViewModel homeViewModel;
    List<CategoryHomeResponseModel> categoryHomeResponseModels = new ArrayList<>();
    List<OfferHomeResponseModel> offerHomeResponseModels = new ArrayList<>();
    List<SavesDiscountHomeResponseModel> savesDiscountHomeResponseModels = new ArrayList<>();
    List<DiscountHomeResponseModel> DiscountHomeResponseModels = new ArrayList<>();
    List<BranchTypeHomeResponseModel> branchTypeHomeResponseModelArrayList = new ArrayList<>();
    CategoryHomeAdapter categoryHomeAdapter;
    SavesOfferHomeAdapter savesOfferHomeAdapter;
    SavesDiscountHomeAdapter savesDiscountHomeAdapter;
    DiscountHomeAdapter DiscountHomeAdapter;
    FurnitureNearByHomeAdapter furnitureNearByHomeAdapter;
    PreferenceHelper preferenceHelper;
    PreferenceHelperChoseLanguage preferenceHelperChoseLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceHelper = PreferenceHelper.getInstans(this);
        preferenceHelperChoseLanguage = PreferenceHelperChoseLanguage.getInstans(this);
        Language.changeLanguage(this, preferenceHelperChoseLanguage.getLang());
        homeMainBinding = ActivityHomeMainBinding.inflate(getLayoutInflater());
        setContentView(homeMainBinding.getRoot());

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getDetailsHome(preferenceHelper.getAccessToken(), preferenceHelperChoseLanguage.getLang());

        homeMainBinding.tvMoreWatchCategory.setOnClickListener(this);
        homeMainBinding.tvMoreWatchSavesDiscount.setOnClickListener(this);
        homeMainBinding.tvMoreWatchDiscountSaves.setOnClickListener(this);
        homeMainBinding.tvMoreWatchDiscount.setOnClickListener(this);
        homeMainBinding.tvMoreWatchFurnitureNearBy.setOnClickListener(this);

        Toast.makeText(this, preferenceHelper.getAccessToken(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, preferenceHelperChoseLanguage.getLang(), Toast.LENGTH_SHORT).show();


        GetCategoryItem();
        GetSavesOfferItem();
        GetDiscountSavesItem();
        GetDiscountItem();
        GetFurnitureNearByItem();

    }

    private void GetCategoryItem() {

        homeMainBinding.SwipeRefreshLayoutCategoryList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                homeViewModel.getDetailsHome("Bearer 159|Chs7WOMBStS7Dsod5P4ULMrrTKQEkjfuTt5Sbv9w", preferenceHelperChoseLanguage.getLang());
                homeMainBinding.SwipeRefreshLayoutCategoryList.setRefreshing(false);

            }
        });

        homeMainBinding.progressBarCyclicCategoryList.setVisibility(View.VISIBLE);
        homeViewModel.modelMutableLiveData.observe(this, new Observer<HomeModel>() {
            @Override
            public void onChanged(HomeModel homeModel) {
                if (homeModel.getStatus()) {
                    homeMainBinding.progressBarCyclicCategoryList.setVisibility(View.GONE);
                    HomeResponseModel homeResponseModel = homeModel.getData();
                    categoryHomeResponseModels = homeResponseModel.getCategories();
                    if (categoryHomeResponseModels.size() > 0) {
                        homeMainBinding.progressBarCyclicCategoryList.setVisibility(View.GONE);
                        categoryHomeAdapter = new CategoryHomeAdapter(categoryHomeResponseModels);
                        homeMainBinding.recyclerViewCategoryList.setLayoutManager(new LinearLayoutManager(HomeMainActivity.this, RecyclerView.HORIZONTAL, false));
                        homeMainBinding.recyclerViewCategoryList.setHasFixedSize(true);
                        homeMainBinding.recyclerViewCategoryList.setAdapter(categoryHomeAdapter);

                    } else {
                        homeMainBinding.progressBarCyclicCategoryList.setVisibility(View.GONE);
                        homeMainBinding.tvNoDataCategory.setVisibility(View.VISIBLE);

                    }
                } else {
                    homeMainBinding.progressBarCyclicCategoryList.setVisibility(View.GONE);
                    Toast.makeText(HomeMainActivity.this, "no data with server", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void GetSavesOfferItem() {


        homeMainBinding.progressBarCyclicSavesOffer.setVisibility(View.VISIBLE);

        homeViewModel.modelMutableLiveData.observe(this, new Observer<HomeModel>() {
            @Override
            public void onChanged(HomeModel homeModel) {
                if (homeModel.getStatus()) {
                    HomeResponseModel homeResponseModel = homeModel.getData();
                    offerHomeResponseModels = homeResponseModel.getOffers();
                    if (offerHomeResponseModels.size() > 0) {
                        homeMainBinding.progressBarCyclicSavesOffer.setVisibility(View.GONE);
                        savesOfferHomeAdapter = new SavesOfferHomeAdapter(HomeMainActivity.this, offerHomeResponseModels);
                        homeMainBinding.recyclerViewSavesOffer.setLayoutManager(new LinearLayoutManager(HomeMainActivity.this, RecyclerView.HORIZONTAL, false));
                        homeMainBinding.recyclerViewSavesOffer.setHasFixedSize(true);
                        homeMainBinding.recyclerViewSavesOffer.setAdapter(savesOfferHomeAdapter);

                    } else {
                        homeMainBinding.progressBarCyclicSavesOffer.setVisibility(View.GONE);
                        homeMainBinding.tvNoDataCategory.setVisibility(View.VISIBLE);

                    }
                } else {
                    homeMainBinding.progressBarCyclicSavesOffer.setVisibility(View.GONE);
                    Toast.makeText(HomeMainActivity.this, "no data with server", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void GetDiscountSavesItem() {

        homeMainBinding.progressBarCyclicDiscountOffer.setVisibility(View.VISIBLE);

        homeViewModel.modelMutableLiveData.observe(this, new Observer<HomeModel>() {
            @Override
            public void onChanged(HomeModel homeModel) {
                if (homeModel.getStatus()) {
                    HomeResponseModel homeResponseModel = homeModel.getData();
                    savesDiscountHomeResponseModels = homeResponseModel.getSaves();
                    if (savesDiscountHomeResponseModels.size() > 0) {
                        homeMainBinding.progressBarCyclicDiscountOffer.setVisibility(View.GONE);
                        savesDiscountHomeAdapter = new SavesDiscountHomeAdapter(HomeMainActivity.this, savesDiscountHomeResponseModels);
                        homeMainBinding.recyclerViewDiscountOffer.setLayoutManager(new LinearLayoutManager(HomeMainActivity.this, RecyclerView.HORIZONTAL, false));
                        homeMainBinding.recyclerViewDiscountOffer.setHasFixedSize(true);
                        homeMainBinding.recyclerViewDiscountOffer.setAdapter(savesDiscountHomeAdapter);

                    } else {
                        homeMainBinding.progressBarCyclicDiscountOffer.setVisibility(View.GONE);
                        homeMainBinding.tvNoDataDiscountOffer.setVisibility(View.VISIBLE);

                    }
                } else {
                    homeMainBinding.progressBarCyclicDiscountOffer.setVisibility(View.GONE);
                    Toast.makeText(HomeMainActivity.this, "no data with server", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void GetDiscountItem() {

        homeMainBinding.progressBarCyclicDiscount.setVisibility(View.VISIBLE);

        homeViewModel.modelMutableLiveData.observe(this, new Observer<HomeModel>() {
            @Override
            public void onChanged(HomeModel homeModel) {
                if (homeModel.getStatus()) {
                    HomeResponseModel homeResponseModel = homeModel.getData();
                    DiscountHomeResponseModels = homeResponseModel.getDiscounts();
                    if (DiscountHomeResponseModels.size() > 0) {
                        homeMainBinding.progressBarCyclicDiscount.setVisibility(View.GONE);
                        DiscountHomeAdapter = new DiscountHomeAdapter(HomeMainActivity.this, DiscountHomeResponseModels);
                        homeMainBinding.recyclerViewDiscount.setLayoutManager(new LinearLayoutManager(HomeMainActivity.this, RecyclerView.HORIZONTAL, false));
                        homeMainBinding.recyclerViewDiscount.setHasFixedSize(true);
                        homeMainBinding.recyclerViewDiscount.setAdapter(DiscountHomeAdapter);

                    } else {
                        homeMainBinding.progressBarCyclicDiscount.setVisibility(View.GONE);
                        homeMainBinding.tvNoDataDiscount.setVisibility(View.VISIBLE);

                    }
                } else {
                    homeMainBinding.progressBarCyclicDiscount.setVisibility(View.GONE);
                    Toast.makeText(HomeMainActivity.this, "no data with server", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void GetFurnitureNearByItem() {

        homeMainBinding.progressBarCyclicFurnitureNearBy.setVisibility(View.VISIBLE);

        homeViewModel.modelMutableLiveData.observe(this, new Observer<HomeModel>() {
            @Override
            public void onChanged(HomeModel homeModel) {
                if (homeModel.getStatus()) {
                    HomeResponseModel homeResponseModel = homeModel.getData();
                    branchTypeHomeResponseModelArrayList = homeResponseModel.getBranchType();
                    if (branchTypeHomeResponseModelArrayList.size() > 0) {
                        homeMainBinding.progressBarCyclicFurnitureNearBy.setVisibility(View.GONE);
                        furnitureNearByHomeAdapter = new FurnitureNearByHomeAdapter( branchTypeHomeResponseModelArrayList);
                        homeMainBinding.recyclerViewFurnitureNearBy.setLayoutManager(new LinearLayoutManager(HomeMainActivity.this, RecyclerView.HORIZONTAL, false));
                        homeMainBinding.recyclerViewFurnitureNearBy.setHasFixedSize(true);
                        homeMainBinding.recyclerViewFurnitureNearBy.setAdapter(furnitureNearByHomeAdapter);

                    } else {
                        homeMainBinding.progressBarCyclicFurnitureNearBy.setVisibility(View.GONE);
                        homeMainBinding.tvNoDataFurnitureNearBy.setVisibility(View.VISIBLE);

                    }
                } else {
                    homeMainBinding.progressBarCyclicFurnitureNearBy.setVisibility(View.GONE);
                    Toast.makeText(HomeMainActivity.this, "no data with server", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_moreWatchCategory:
                startActivity(new Intent(HomeMainActivity.this, CategoryListActivity.class));
                break;
            case R.id.tv_moreWatchSavesDiscount:
                startActivity(new Intent(HomeMainActivity.this, SavingsOffersActivity.class));
                break;
            case R.id.tv_moreWatchDiscountSaves:
                startActivity(new Intent(HomeMainActivity.this, DiscountSavesActivity.class));
                break;
            case R.id.tv_moreWatchDiscount:
                startActivity(new Intent(HomeMainActivity.this, DiscountActivity.class));
                break;
            case R.id.tv_moreWatchFurnitureNearBy:
                startActivity(new Intent(HomeMainActivity.this, FurnitureNearByActivity.class));
                break;
        }
    }
}