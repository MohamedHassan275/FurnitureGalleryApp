package com.example.furniture_gallery.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.furniture_gallery.Model.UserResponseModel.OfferHomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.ProductOfferHomeResponesModel;
import com.example.furniture_gallery.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductSavesOfferHomeAdapter extends RecyclerView.Adapter<ProductSavesOfferHomeAdapter.ViewHolder> {

    List<ProductOfferHomeResponesModel> offerHomeResponseModels;
    private SetOnProductSavesOfferHomeItemClickListener mclickListener;

    public ProductSavesOfferHomeAdapter(List<ProductOfferHomeResponesModel> categoryHomeResponseModelList) {
        this.offerHomeResponseModels = categoryHomeResponseModelList;
    }

    public interface SetOnProductSavesOfferHomeItemClickListener {
        void onItemClick(int position);

    }

    public void BtnOnProductSavesOfferHomeItemClickListener(SetOnProductSavesOfferHomeItemClickListener clickListener) {
        mclickListener = clickListener;
    }

    @NonNull
    @Override
    public ProductSavesOfferHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_saves_dsicount_list_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSavesOfferHomeAdapter.ViewHolder holder, int position) {

        ProductOfferHomeResponesModel categoryHomeResponseModel = offerHomeResponseModels.get(position);

        Picasso.get().load(categoryHomeResponseModel.getIcon()).into(holder.ImageProductSavesDiscount);
        holder.tv_titleProductSavesDiscount.setText(String.valueOf(categoryHomeResponseModel.getQuantity()));

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_titleProductSavesDiscount;
        ImageView ImageProductSavesDiscount;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_titleProductSavesDiscount = itemView.findViewById(R.id.tv_titleProductSavesDiscount);
            ImageProductSavesDiscount = itemView.findViewById(R.id.ImageProductSavesDiscount);



        }
    }
    @Override
    public int getItemCount() {
        return offerHomeResponseModels.size();
    }
}
