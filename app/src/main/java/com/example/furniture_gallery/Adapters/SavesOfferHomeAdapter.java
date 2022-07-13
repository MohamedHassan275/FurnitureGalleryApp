package com.example.furniture_gallery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.furniture_gallery.Activites.SavingsOffersActivity;
import com.example.furniture_gallery.Core.SharedPrefrance.PreferenceHelperChoseLanguage;
import com.example.furniture_gallery.Model.UserModel.HomeModel;
import com.example.furniture_gallery.Model.UserResponseModel.CategoryHomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.HomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.OfferHomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.ProductOfferHomeResponesModel;
import com.example.furniture_gallery.R;
import com.example.furniture_gallery.Retrofit_Api.Retrofit_Api;
import com.example.furniture_gallery.ViewModel.HomeViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SavesOfferHomeAdapter extends RecyclerView.Adapter<SavesOfferHomeAdapter.ViewHolder> {

    Context context;
    List<OfferHomeResponseModel> offerHomeResponseModels;
    List<ProductOfferHomeResponesModel> productOfferHomeResponesModels;
    ProductSavesOfferHomeAdapter productSavesOfferHomeAdapter;
    private SetOnSavesOfferHomeItemClickListener mclickListener;
    PreferenceHelperChoseLanguage preferenceHelperChoseLanguage;

    public SavesOfferHomeAdapter(Context context, List<OfferHomeResponseModel> offerHomeResponseModels) {
        this.context = context;
        this.offerHomeResponseModels = offerHomeResponseModels;
    }

    public interface SetOnSavesOfferHomeItemClickListener {
        void onItemClick(int position);

    }

    public void BtnOnSavesOfferHomeItemClickListener(SetOnSavesOfferHomeItemClickListener clickListener) {
        mclickListener = clickListener;
    }

    @NonNull
    @Override
    public SavesOfferHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.saves_discount_list_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SavesOfferHomeAdapter.ViewHolder holder, int position) {

        preferenceHelperChoseLanguage = PreferenceHelperChoseLanguage.getInstans(context);

        OfferHomeResponseModel categoryHomeResponseModel = offerHomeResponseModels.get(position);

        Picasso.get().load(categoryHomeResponseModel.getImages().get(0).getPath()).into(holder.ImageViewSlider);
        Picasso.get().load(categoryHomeResponseModel.getFurnitureLogo()).into(holder.ImageFurnitureSavesDiscount);
        holder.tv_nameSavesOffer.setText(categoryHomeResponseModel.getName());
        holder.tv_NameFurnitureSavesDiscount.setText(categoryHomeResponseModel.getFurnitureName());
        holder.tv_PricePriceFurnitureSavesDiscount.setText(String.valueOf(categoryHomeResponseModel.getPrice()));



        holder.progressBar_cyclic_SavesOffer.setVisibility(View.VISIBLE);
        Call<HomeModel> call = Retrofit_Api.RETROFIT_API_INSTANCE().GetDetailsHomeModel("Bearer 159|Chs7WOMBStS7Dsod5P4ULMrrTKQEkjfuTt5Sbv9w",preferenceHelperChoseLanguage.getLang());
        call.enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
                if(response.body().getStatus()){
                    productOfferHomeResponesModels = categoryHomeResponseModel.getProducts();
                    if (productOfferHomeResponesModels.size() > 0){
                        holder.progressBar_cyclic_SavesOffer.setVisibility(View.GONE);
                        productSavesOfferHomeAdapter = new ProductSavesOfferHomeAdapter(productOfferHomeResponesModels);
                        holder.recyclerView_SavesOffer.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,false));
                        holder.recyclerView_SavesOffer.setHasFixedSize(true);
                        holder.recyclerView_SavesOffer.setAdapter(productSavesOfferHomeAdapter);

                    }else {
                        holder.progressBar_cyclic_SavesOffer.setVisibility(View.GONE);
                        holder.tv_noDataSavesOffer.setVisibility(View.VISIBLE);

                    }
                }else {
                    holder.progressBar_cyclic_SavesOffer.setVisibility(View.GONE);
                    Toast.makeText(context, "no data with server", Toast.LENGTH_SHORT).show();
                }

            }

        @Override
        public void onFailure(Call<HomeModel> call, Throwable t) {

        }
    });


    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout LinearLayoutSavesDiscount,tv_orderNow;
        TextView tv_nameSavesOffer,tv_noDataSavesOffer,tv_NameFurnitureSavesDiscount,tv_PricePriceFurnitureSavesDiscount;
        ProgressBar progressBar_cyclic_SavesOffer;
        SwipeRefreshLayout SwipeRefreshLayoutSavesOffer;
        RecyclerView recyclerView_SavesOffer;
        ImageView ImageFurnitureSavesDiscount,ImageViewSlider;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            LinearLayoutSavesDiscount = itemView.findViewById(R.id.LinearLayoutSavesDiscount);
            tv_nameSavesOffer = itemView.findViewById(R.id.tv_nameSavesOffer);
            tv_orderNow = itemView.findViewById(R.id.tv_orderNow);
            tv_noDataSavesOffer = itemView.findViewById(R.id.tv_noDataSavesOffer);
            tv_NameFurnitureSavesDiscount = itemView.findViewById(R.id.tv_NameFurnitureSavesDiscount);
            tv_PricePriceFurnitureSavesDiscount = itemView.findViewById(R.id.tv_PricePriceFurnitureSavesDiscount);
            progressBar_cyclic_SavesOffer = itemView.findViewById(R.id.progressBar_cyclic_SavesOffer);
            SwipeRefreshLayoutSavesOffer = itemView.findViewById(R.id.SwipeRefreshLayoutSavesOffer);
            recyclerView_SavesOffer = itemView.findViewById(R.id.recyclerView_SavesOffer);
            ImageFurnitureSavesDiscount = itemView.findViewById(R.id.ImageFurnitureSavesDiscount);
            ImageViewSlider = itemView.findViewById(R.id.ImageViewSlider);

            LinearLayoutSavesDiscount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mclickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mclickListener.onItemClick(position);
                        }
                    }

                }
            });
            tv_orderNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mclickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mclickListener.onItemClick(position);
                        }
                    }

                }
            });


        }
    }
    @Override
    public int getItemCount() {
        return offerHomeResponseModels.size();
    }
}
