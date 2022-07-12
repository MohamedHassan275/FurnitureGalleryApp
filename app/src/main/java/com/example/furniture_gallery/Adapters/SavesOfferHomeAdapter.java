package com.example.furniture_gallery.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.furniture_gallery.Model.UserResponseModel.CategoryHomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.OfferHomeResponseModel;
import com.example.furniture_gallery.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SavesOfferHomeAdapter extends RecyclerView.Adapter<SavesOfferHomeAdapter.ViewHolder> {

    List<OfferHomeResponseModel> offerHomeResponseModels;
    private SetOnSavesOfferHomeItemClickListener mclickListener;

    public SavesOfferHomeAdapter(List<OfferHomeResponseModel> categoryHomeResponseModelList) {
        this.offerHomeResponseModels = categoryHomeResponseModelList;
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

        OfferHomeResponseModel categoryHomeResponseModel = offerHomeResponseModels.get(position);

        Picasso.get().load(categoryHomeResponseModel.getImages().get(0).getPath()).into(holder.ImageViewSlider);
        Picasso.get().load(categoryHomeResponseModel.getFurnitureLogo()).into(holder.ImageFurnitureSavesDiscount);
        holder.tv_nameSavesOffer.setText(categoryHomeResponseModel.getName());
        holder.tv_NameFurnitureSavesDiscount.setText(categoryHomeResponseModel.getFurnitureName());
        holder.tv_PricePriceFurnitureSavesDiscount.setText(categoryHomeResponseModel.getPrice());



    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout LinearLayoutSavesDiscount;
        TextView tv_nameSavesOffer,tv_orderNow,tv_noDataSavesOffer,tv_NameFurnitureSavesDiscount,tv_PricePriceFurnitureSavesDiscount;
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
