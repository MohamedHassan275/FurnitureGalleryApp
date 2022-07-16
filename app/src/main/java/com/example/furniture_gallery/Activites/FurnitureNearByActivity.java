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

import com.example.furniture_gallery.Adapters.FurnitureNearByScreenAdapter;
import com.example.furniture_gallery.Core.Language.Language;
import com.example.furniture_gallery.Core.SharedPrefrance.PreferenceHelperChoseLanguage;
import com.example.furniture_gallery.Model.UserModel.FurnitureNearByModel;
import com.example.furniture_gallery.Model.UserResponseModel.FurnitureNearByResponseModel;
import com.example.furniture_gallery.ViewModel.FurnitureViewModel;
import com.example.furniture_gallery.ViewModel.HomeViewModel;
import com.example.furniture_gallery.databinding.ActivityFurnitureNearByBinding;

import java.util.ArrayList;
import java.util.List;

public class FurnitureNearByActivity extends AppCompatActivity {

    ActivityFurnitureNearByBinding furnitureNearByBinding;
    FurnitureViewModel furnitureViewModel;
    List<FurnitureNearByResponseModel> furnitureNearByResponseModels = new ArrayList<>();
    FurnitureNearByScreenAdapter furnitureNearByScreenAdapter;
    PreferenceHelperChoseLanguage preferenceHelperChoseLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceHelperChoseLanguage = PreferenceHelperChoseLanguage.getInstans(this);
        Language.changeLanguage(this,preferenceHelperChoseLanguage.getLang());
        furnitureNearByBinding = ActivityFurnitureNearByBinding.inflate(getLayoutInflater());
        setContentView(furnitureNearByBinding.getRoot());

        furnitureViewModel = new ViewModelProvider(this).get(FurnitureViewModel.class);

        furnitureViewModel.getFurnitureNearBy(25.2121212,24.1252152);

        furnitureNearByBinding.progressBarCyclicFurnitureNearBy.setVisibility(View.VISIBLE);

        furnitureNearByBinding.SwipeRefreshLayoutFurnitureNearBy.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                furnitureViewModel.getFurnitureNearBy(25.2121212,24.1252152);
                furnitureNearByBinding.SwipeRefreshLayoutFurnitureNearBy.setRefreshing(false);

            }
        });
        furnitureViewModel.furnitureNearByModelMutableLiveData.observe(this, new Observer<FurnitureNearByModel>() {
            @Override
            public void onChanged(FurnitureNearByModel furnitureNearByModel) {
                if (furnitureNearByModel.getStatus()) {
                    furnitureNearByResponseModels = furnitureNearByModel.getFurnitureNearByResponseModels();
                    if (furnitureNearByResponseModels.size() > 0) {
                        furnitureNearByBinding.progressBarCyclicFurnitureNearBy.setVisibility(View.GONE);
                        furnitureNearByScreenAdapter = new FurnitureNearByScreenAdapter(furnitureNearByResponseModels);
                        furnitureNearByBinding.recyclerViewFurnitureNearBy.setLayoutManager(new LinearLayoutManager(FurnitureNearByActivity.this,
                                RecyclerView.VERTICAL,false));
                        furnitureNearByBinding.recyclerViewFurnitureNearBy.setHasFixedSize(true);
                        furnitureNearByBinding.recyclerViewFurnitureNearBy.setAdapter(furnitureNearByScreenAdapter);

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