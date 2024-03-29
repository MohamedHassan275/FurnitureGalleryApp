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

import com.example.furniture_gallery.Adapters.SavesDiscountHomeAdapter;
import com.example.furniture_gallery.Core.Language.Language;
import com.example.furniture_gallery.Core.SharedPrefrance.PreferenceHelperChoseLanguage;
import com.example.furniture_gallery.Model.UserModel.HomeModel;
import com.example.furniture_gallery.Model.UserModel.SaveOfferModel;
import com.example.furniture_gallery.Model.UserResponseModel.HomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.SavesDiscountHomeResponseModel;
import com.example.furniture_gallery.R;
import com.example.furniture_gallery.ViewModel.HomeViewModel;
import com.example.furniture_gallery.ViewModel.SaveOfferViewModel;
import com.example.furniture_gallery.databinding.ActivityDiscountSavesBinding;

import java.util.ArrayList;
import java.util.List;

public class DiscountSavesActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityDiscountSavesBinding discountSavesBinding;
    SaveOfferViewModel saveOfferViewModel;
    List<SavesDiscountHomeResponseModel> savesDiscountHomeResponseModels = new ArrayList<>();
    SavesDiscountHomeAdapter savesDiscountHomeAdapter;
    PreferenceHelperChoseLanguage preferenceHelperChoseLanguage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceHelperChoseLanguage = PreferenceHelperChoseLanguage.getInstans(this);
        Language.changeLanguage(this,preferenceHelperChoseLanguage.getLang());
        discountSavesBinding = ActivityDiscountSavesBinding.inflate(getLayoutInflater());
        setContentView(discountSavesBinding.getRoot());


        saveOfferViewModel = new ViewModelProvider(this).get(SaveOfferViewModel.class);

        saveOfferViewModel.GetSaveDiscount("DESC");

        discountSavesBinding.SwipeRefreshLayoutDiscountSaves.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                saveOfferViewModel.GetSaveDiscount("DESC");
                discountSavesBinding.SwipeRefreshLayoutDiscountSaves.setRefreshing(false);

            }
        });

        discountSavesBinding.progressBarCyclicDiscountSaves.setVisibility(View.VISIBLE);

        saveOfferViewModel.saveOfferModelMutableLiveData.observe(this, new Observer<SaveOfferModel>() {
            @Override
            public void onChanged(SaveOfferModel saveOfferModel) {
                if(saveOfferModel.getStatus()){
                    savesDiscountHomeResponseModels = saveOfferModel.getSaves();
                    if (savesDiscountHomeResponseModels.size() > 0){
                        discountSavesBinding.progressBarCyclicDiscountSaves.setVisibility(View.GONE);
                        savesDiscountHomeAdapter = new SavesDiscountHomeAdapter(DiscountSavesActivity.this,savesDiscountHomeResponseModels);
                        discountSavesBinding.recyclerViewDiscountSaves.setLayoutManager(new LinearLayoutManager(DiscountSavesActivity.this,
                                RecyclerView.VERTICAL,false));
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