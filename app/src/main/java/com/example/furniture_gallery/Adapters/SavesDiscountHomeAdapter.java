package com.example.furniture_gallery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.furniture_gallery.Model.UserModel.HomeModel;
import com.example.furniture_gallery.Model.UserResponseModel.ProductOfferHomeResponesModel;
import com.example.furniture_gallery.Model.UserResponseModel.SavesDiscountHomeResponseModel;
import com.example.furniture_gallery.R;
import com.example.furniture_gallery.Retrofit_Api.Retrofit_Api;
import com.example.furniture_gallery.ViewModel.HomeViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SavesDiscountHomeAdapter extends RecyclerView.Adapter<SavesDiscountHomeAdapter.ViewHolder> {

    Context context;
    List<SavesDiscountHomeResponseModel> savesDiscountHomeResponseModels;
    List<ProductOfferHomeResponesModel> productOfferHomeResponesModels;
    ProductSavesOfferHomeAdapter productSavesOfferHomeAdapter;
    private SetOnSavesDiscountHomeItemClickListener mclickListener;

    public SavesDiscountHomeAdapter(Context context, List<SavesDiscountHomeResponseModel> offerHomeResponseModels) {
        this.context = context;
        this.savesDiscountHomeResponseModels = offerHomeResponseModels;
    }

    public interface SetOnSavesDiscountHomeItemClickListener {
        void onItemClick(int position);

    }

    public void BtnOnSavesDiscountHomeItemClickListener(SetOnSavesDiscountHomeItemClickListener clickListener) {
        mclickListener = clickListener;
    }

    @NonNull
    @Override
    public SavesDiscountHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.discount_saves_list_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SavesDiscountHomeAdapter.ViewHolder holder, int position) {

        SavesDiscountHomeResponseModel savesDiscountHomeResponseModel = savesDiscountHomeResponseModels.get(position);

        Picasso.get().load(savesDiscountHomeResponseModel.getImages().get(0).getPath()).into(holder.ImageViewSliderDiscountSaves);
        Picasso.get().load(savesDiscountHomeResponseModel.getFurnitureLogo()).into(holder.ImageFurnitureDiscountSaves);
        holder.tv_NameFurnitureDiscountSaves.setText(savesDiscountHomeResponseModel.getFurnitureName());
        holder.tv_nameDiscountSaves.setText(savesDiscountHomeResponseModel.getName());
        holder.tv_PricePriceFurnitureDiscountSaves.setText(String.valueOf(savesDiscountHomeResponseModel.getPrice()));
        holder.tv_TimeDiscountSaves1.setText(String.valueOf(savesDiscountHomeResponseModel.getHours()));
        holder.tv_TimeDiscountSaves2.setText(String.valueOf(savesDiscountHomeResponseModel.getMinutes()));
        holder.tv_TimeDiscountSaves3.setText(String.valueOf(0));
        holder.tv_TimeDiscountSaves4.setText(String.valueOf(0));



        holder.progressBar_cyclic_DiscountSaves.setVisibility(View.VISIBLE);
        Call<HomeModel> call = Retrofit_Api.RETROFIT_API_INSTANCE().GetDetailsHomeModel("Bearer 159|Chs7WOMBStS7Dsod5P4ULMrrTKQEkjfuTt5Sbv9w");
        call.enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
                if(response.body().getStatus()){
                    productOfferHomeResponesModels = savesDiscountHomeResponseModel.getProducts();
                    if (productOfferHomeResponesModels.size() > 0){
                        holder.progressBar_cyclic_DiscountSaves.setVisibility(View.GONE);
                        productSavesOfferHomeAdapter = new ProductSavesOfferHomeAdapter(productOfferHomeResponesModels);
                        holder.recyclerView_DiscountSaves.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false));
                        holder.recyclerView_DiscountSaves.setHasFixedSize(true);
                        holder.recyclerView_DiscountSaves.setAdapter(productSavesOfferHomeAdapter);

                    }else {
                        holder.progressBar_cyclic_DiscountSaves.setVisibility(View.GONE);
                        holder.tv_noDataDiscountSaves.setVisibility(View.VISIBLE);

                    }
                }else {
                    holder.progressBar_cyclic_DiscountSaves.setVisibility(View.GONE);
                    Toast.makeText(context, "no data with server", Toast.LENGTH_SHORT).show();
                }

            }

        @Override
        public void onFailure(Call<HomeModel> call, Throwable t) {

        }
    });


    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout tv_orderNowDiscountSaves;
        RelativeLayout RelativeLayoutDiscountSaves;
        TextView tv_NameFurnitureDiscountSaves,tv_noDataDiscountSaves,tv_nameDiscountSaves,tv_PricePriceFurnitureDiscountSaves,
                tv_TimeDiscountSaves1,tv_TimeDiscountSaves2,tv_TimeDiscountSaves3,tv_TimeDiscountSaves4;
        ProgressBar progressBar_cyclic_DiscountSaves;
        SwipeRefreshLayout SwipeRefreshLayoutDiscountSaves;
        RecyclerView recyclerView_DiscountSaves;
        ImageView ImageFurnitureDiscountSaves,ImageViewSliderDiscountSaves;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            RelativeLayoutDiscountSaves = itemView.findViewById(R.id.RelativeLayoutDiscountSaves);
            ImageFurnitureDiscountSaves = itemView.findViewById(R.id.ImageFurnitureDiscountSaves);
            ImageViewSliderDiscountSaves = itemView.findViewById(R.id.ImageViewSliderDiscountSaves);
            tv_NameFurnitureDiscountSaves = itemView.findViewById(R.id.tv_NameFurnitureDiscountSaves);
            tv_TimeDiscountSaves1 = itemView.findViewById(R.id.tv_TimeDiscountSaves1);
            tv_TimeDiscountSaves2 = itemView.findViewById(R.id.tv_TimeDiscountSaves2);
            tv_TimeDiscountSaves3 = itemView.findViewById(R.id.tv_TimeDiscountSaves3);
            tv_TimeDiscountSaves4 = itemView.findViewById(R.id.tv_TimeDiscountSaves4);
            tv_orderNowDiscountSaves = itemView.findViewById(R.id.tv_orderNowDiscountSaves);
            tv_noDataDiscountSaves = itemView.findViewById(R.id.tv_noDataDiscountSaves);
            tv_nameDiscountSaves = itemView.findViewById(R.id.tv_nameDiscountSaves);
            tv_PricePriceFurnitureDiscountSaves = itemView.findViewById(R.id.tv_PricePriceFurnitureDiscountSaves);
            progressBar_cyclic_DiscountSaves = itemView.findViewById(R.id.progressBar_cyclic_DiscountSaves);
            SwipeRefreshLayoutDiscountSaves = itemView.findViewById(R.id.SwipeRefreshLayoutDiscountSaves);
            recyclerView_DiscountSaves = itemView.findViewById(R.id.recyclerView_DiscountSaves);


            RelativeLayoutDiscountSaves.setOnClickListener(new View.OnClickListener() {
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
            tv_orderNowDiscountSaves.setOnClickListener(new View.OnClickListener() {
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
        return savesDiscountHomeResponseModels.size();
    }
}
