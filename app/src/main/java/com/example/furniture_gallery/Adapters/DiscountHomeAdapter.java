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

import com.example.furniture_gallery.Core.SharedPrefrance.PreferenceHelperChoseLanguage;
import com.example.furniture_gallery.Model.UserModel.HomeModel;
import com.example.furniture_gallery.Model.UserResponseModel.DiscountHomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.ProductOfferHomeResponesModel;
import com.example.furniture_gallery.Model.UserResponseModel.SavesDiscountHomeResponseModel;
import com.example.furniture_gallery.R;
import com.example.furniture_gallery.Retrofit_Api.Retrofit_Api;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscountHomeAdapter extends RecyclerView.Adapter<DiscountHomeAdapter.ViewHolder> {

    Context context;
    List<DiscountHomeResponseModel> discountHomeResponseModelList;
    private SetOnDiscountHomeItemClickListener mclickListener;

    public DiscountHomeAdapter(Context context, List<DiscountHomeResponseModel> offerHomeResponseModels) {
        this.context = context;
        this.discountHomeResponseModelList = offerHomeResponseModels;
    }

    public interface SetOnDiscountHomeItemClickListener {
        void onItemClick(int position);

    }

    public void BtnOnDiscountHomeItemClickListener(SetOnDiscountHomeItemClickListener clickListener) {
        mclickListener = clickListener;
    }

    @NonNull
    @Override
    public DiscountHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.discount_home_list_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountHomeAdapter.ViewHolder holder, int position) {

        DiscountHomeResponseModel savesDiscountHomeResponseModel = discountHomeResponseModelList.get(position);

        Picasso.get().load(savesDiscountHomeResponseModel.getImages().get(0).getPath()).into(holder.ImageViewSliderDiscount);
        Picasso.get().load(savesDiscountHomeResponseModel.getFurnitureLogo()).into(holder.ImageFurnitureDiscount);
        holder.tv_NameFurnitureDiscount.setText(savesDiscountHomeResponseModel.getFurnitureName());
        holder.tv_nameDiscount.setText(savesDiscountHomeResponseModel.getProductName());
        holder.tv_DetailsDiscount.setText(savesDiscountHomeResponseModel.getProductDescription());
        holder.tv_PersentPriceDiscount.setText(String.valueOf(savesDiscountHomeResponseModel.getPercent()) + "%");
        holder.tv_PriceAfterDiscount.setText(String.valueOf(savesDiscountHomeResponseModel.getPriceAfter()) + "جنيه");
        holder.tv_BeforePriceDiscount.setText(String.valueOf(savesDiscountHomeResponseModel.getPriceBefore()) + "جنيه");

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout LinearLayoutDiscount,tv_orderNowDiscount;
        TextView tv_NameFurnitureDiscount,tv_nameDiscount,tv_DetailsDiscount,tv_PersentPriceDiscount,tv_PriceAfterDiscount,tv_BeforePriceDiscount;
        ImageView ImageViewSliderDiscount;
        CircleImageView ImageFurnitureDiscount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            LinearLayoutDiscount = itemView.findViewById(R.id.LinearLayoutDiscount);
            tv_orderNowDiscount = itemView.findViewById(R.id.tv_orderNowDiscount);
            ImageFurnitureDiscount = itemView.findViewById(R.id.ImageFurnitureDiscount);
            ImageViewSliderDiscount = itemView.findViewById(R.id.ImageViewSliderDiscount);
            tv_NameFurnitureDiscount = itemView.findViewById(R.id.tv_NameFurnitureDiscount);
            tv_nameDiscount = itemView.findViewById(R.id.tv_nameDiscount);
            tv_DetailsDiscount = itemView.findViewById(R.id.tv_DetailsDiscount);
            tv_PersentPriceDiscount = itemView.findViewById(R.id.tv_PersentPriceDiscount);
            tv_PriceAfterDiscount = itemView.findViewById(R.id.tv_PriceAfterDiscount);
            tv_BeforePriceDiscount = itemView.findViewById(R.id.tv_BeforePriceDiscount);


            tv_orderNowDiscount.setOnClickListener(new View.OnClickListener() {
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
            LinearLayoutDiscount.setOnClickListener(new View.OnClickListener() {
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
        return discountHomeResponseModelList.size();
    }
}
