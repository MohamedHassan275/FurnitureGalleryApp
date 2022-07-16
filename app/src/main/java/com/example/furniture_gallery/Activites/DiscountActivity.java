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

import com.example.furniture_gallery.Adapters.DiscountHomeAdapter;
import com.example.furniture_gallery.Core.Language.Language;
import com.example.furniture_gallery.Core.SharedPrefrance.PreferenceHelperChoseLanguage;
import com.example.furniture_gallery.Model.UserModel.DiscountsModel;
import com.example.furniture_gallery.Model.UserModel.HomeModel;
import com.example.furniture_gallery.Model.UserResponseModel.DiscountHomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.HomeResponseModel;
import com.example.furniture_gallery.ViewModel.DiscountsViewModel;
import com.example.furniture_gallery.ViewModel.HomeViewModel;
import com.example.furniture_gallery.databinding.ActivityDiscountBinding;

import java.util.ArrayList;
import java.util.List;

public class DiscountActivity extends AppCompatActivity {

    ActivityDiscountBinding discountBinding;
    DiscountsViewModel discountsViewModel;
    List<DiscountHomeResponseModel> DiscountHomeResponseModels = new ArrayList<>();
    DiscountHomeAdapter DiscountHomeAdapter;
    PreferenceHelperChoseLanguage preferenceHelperChoseLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceHelperChoseLanguage = PreferenceHelperChoseLanguage.getInstans(this);
        Language.changeLanguage(this,preferenceHelperChoseLanguage.getLang());
        discountBinding = ActivityDiscountBinding.inflate(getLayoutInflater());
        setContentView(discountBinding.getRoot());

        discountsViewModel = new ViewModelProvider(this).get(DiscountsViewModel.class);
        discountsViewModel.GetOffer("DESC");

        discountBinding.SwipeRefreshLayoutDiscount.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                discountsViewModel.GetOffer("DESC");
                discountBinding.SwipeRefreshLayoutDiscount.setRefreshing(false);

            }
        });

        discountBinding.progressBarCyclicDiscount.setVisibility(View.VISIBLE);

        discountsViewModel.discountsModelMutableLiveData.observe(this, new Observer<DiscountsModel>() {
            @Override
            public void onChanged(DiscountsModel discountsModel) {
                if(discountsModel.getStatus()){
                    DiscountHomeResponseModels = discountsModel.getDiscounts();
                    if (DiscountHomeResponseModels.size() > 0){
                        discountBinding.progressBarCyclicDiscount.setVisibility(View.GONE);
                        DiscountHomeAdapter = new DiscountHomeAdapter(DiscountActivity.this,DiscountHomeResponseModels);
                        discountBinding.recyclerViewDiscount.setLayoutManager(new LinearLayoutManager(DiscountActivity.this, RecyclerView.VERTICAL,false));
                        discountBinding.recyclerViewDiscount.setHasFixedSize(true);
                        discountBinding.recyclerViewDiscount.setAdapter(DiscountHomeAdapter);

                    }else {
                        discountBinding.progressBarCyclicDiscount.setVisibility(View.GONE);
                        discountBinding.tvNoDataDiscountSaves.setVisibility(View.VISIBLE);

                    }
                }else {
                    discountBinding.progressBarCyclicDiscount.setVisibility(View.GONE);
                    Toast.makeText(DiscountActivity.this, "no data with server", Toast.LENGTH_SHORT).show();
                }
            }
        });

        discountBinding.imageBackDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DiscountActivity.this,HomeMainActivity.class));
            }
        });
    }
}