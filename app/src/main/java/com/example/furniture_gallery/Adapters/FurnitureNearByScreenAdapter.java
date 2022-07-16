package com.example.furniture_gallery.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniture_gallery.Model.UserResponseModel.FurnitureNearByResponseModel;
import com.example.furniture_gallery.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FurnitureNearByScreenAdapter extends RecyclerView.Adapter<FurnitureNearByScreenAdapter.ViewHolder> {

    List<FurnitureNearByResponseModel> furnitureNearByResponseModels;
    private static SetOnBranchTypeHomeItemClickListener mclickListener;

    public FurnitureNearByScreenAdapter(List<FurnitureNearByResponseModel> categoryHomeResponseModelList) {
        this.furnitureNearByResponseModels = categoryHomeResponseModelList;
    }

    public interface SetOnBranchTypeHomeItemClickListener {
        void onItemClick(int position);

    }

    public void BtnOnBranchTypeHomeItemClickListener(SetOnBranchTypeHomeItemClickListener clickListener) {
        mclickListener = clickListener;
    }

    @NonNull
    @Override
    public FurnitureNearByScreenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.furniture_list_view_screen, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FurnitureNearByScreenAdapter.ViewHolder holder, int position) {

        FurnitureNearByResponseModel categoryHomeResponseModel = furnitureNearByResponseModels.get(position);

        Picasso.get().load(categoryHomeResponseModel.getLogo()).into(holder.CircleImageViewFurnitureName);
        holder.tv_NameFurnitureDiscount.setText(categoryHomeResponseModel.getName());
        holder.tv_TypeFurnitureDiscount.setText(categoryHomeResponseModel.getBranchTypeName());
        holder.tv_AddressFurnitureDiscount.setText(categoryHomeResponseModel.getAddress());

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout LinearLayoutFurniture;
        TextView tv_NameFurnitureDiscount,tv_TypeFurnitureDiscount,tv_AddressFurnitureDiscount;
        CircleImageView CircleImageViewFurnitureName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            LinearLayoutFurniture = itemView.findViewById(R.id.LinearLayoutFurniture);
            tv_NameFurnitureDiscount = itemView.findViewById(R.id.tv_NameFurnitureDiscount);
            tv_TypeFurnitureDiscount = itemView.findViewById(R.id.tv_TypeFurnitureDiscount);
            tv_AddressFurnitureDiscount = itemView.findViewById(R.id.tv_AddressFurnitureDiscount);
            CircleImageViewFurnitureName = itemView.findViewById(R.id.CircleImageViewFurnitureName);

            LinearLayoutFurniture.setOnClickListener(new View.OnClickListener() {
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
        return furnitureNearByResponseModels.size();
    }
}
