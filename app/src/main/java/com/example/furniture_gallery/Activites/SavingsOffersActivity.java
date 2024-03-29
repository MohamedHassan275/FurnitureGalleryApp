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

import com.example.furniture_gallery.Adapters.SavesOfferHomeAdapter;
import com.example.furniture_gallery.Core.Language.Language;
import com.example.furniture_gallery.Core.SharedPrefrance.PreferenceHelperChoseLanguage;
import com.example.furniture_gallery.Model.UserModel.OfferModel;
import com.example.furniture_gallery.Model.UserModel.SaveOfferModel;
import com.example.furniture_gallery.Model.UserResponseModel.OfferHomeResponseModel;
import com.example.furniture_gallery.R;
import com.example.furniture_gallery.ViewModel.OfferViewModel;
import com.example.furniture_gallery.ViewModel.SaveOfferViewModel;
import com.example.furniture_gallery.databinding.ActivitySavingsOffersBinding;

import java.util.ArrayList;
import java.util.List;

public class SavingsOffersActivity extends AppCompatActivity implements View.OnClickListener {

    ActivitySavingsOffersBinding savesOfferBinding;
    OfferViewModel offerViewModel;
    List<OfferHomeResponseModel> offerHomeResponseModels = new ArrayList<>();
    SavesOfferHomeAdapter savesOfferHomeAdapter;
    PreferenceHelperChoseLanguage preferenceHelperChoseLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceHelperChoseLanguage = PreferenceHelperChoseLanguage.getInstans(this);
        Language.changeLanguage(this,preferenceHelperChoseLanguage.getLang());
        savesOfferBinding = ActivitySavingsOffersBinding.inflate(getLayoutInflater());
        setContentView(savesOfferBinding.getRoot());

        offerViewModel = new ViewModelProvider(this).get(OfferViewModel.class);

        offerViewModel.GetOffer("ASC");

        savesOfferBinding.SwipeRefreshLayoutCategoryList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                offerViewModel.GetOffer("ASC");
                savesOfferBinding.SwipeRefreshLayoutCategoryList.setRefreshing(false);

            }
        });

        savesOfferBinding.progressBarCyclicSavesOffer.setVisibility(View.VISIBLE);

        offerViewModel.offerModelMutableLiveData.observe(this, new Observer<OfferModel>() {
            @Override
            public void onChanged(OfferModel offerModel) {
                if(offerModel.getStatus()){
                    offerHomeResponseModels = offerModel.getOffers();
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