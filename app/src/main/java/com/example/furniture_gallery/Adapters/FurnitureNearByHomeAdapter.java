package com.example.furniture_gallery.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniture_gallery.Model.UserResponseModel.BranchTypeHomeResponseModel;
import com.example.furniture_gallery.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FurnitureNearByHomeAdapter extends RecyclerView.Adapter<FurnitureNearByHomeAdapter.ViewHolder> {

    List<BranchTypeHomeResponseModel> branchTypeHomeResponseModels;
    private static SetOnBranchTypeHomeItemClickListener mclickListener;

    public FurnitureNearByHomeAdapter(List<BranchTypeHomeResponseModel> categoryHomeResponseModelList) {
        this.branchTypeHomeResponseModels = categoryHomeResponseModelList;
    }

    public interface SetOnBranchTypeHomeItemClickListener {
        void onItemClick(int position);

    }

    public void BtnOnBranchTypeHomeItemClickListener(SetOnBranchTypeHomeItemClickListener clickListener) {
        mclickListener = clickListener;
    }

    @NonNull
    @Override
    public FurnitureNearByHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.furniture_near_by_list_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FurnitureNearByHomeAdapter.ViewHolder holder, int position) {

        BranchTypeHomeResponseModel categoryHomeResponseModel = branchTypeHomeResponseModels.get(position);

        Picasso.get().load(categoryHomeResponseModel.getImage()).into(holder.ImageViewSliderNearBy);
        holder.tv_NameFurnitureNearBy.setText(String.valueOf(categoryHomeResponseModel.getName()));

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout RelativeLayoutFurnitureNearBy;
        TextView tv_NameFurnitureNearBy;
        ImageView ImageViewSliderNearBy;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            RelativeLayoutFurnitureNearBy = itemView.findViewById(R.id.RelativeLayoutFurnitureNearBy);
            tv_NameFurnitureNearBy = itemView.findViewById(R.id.tv_NameFurnitureNearBy);
            ImageViewSliderNearBy = itemView.findViewById(R.id.ImageViewSliderNearBy);

            RelativeLayoutFurnitureNearBy.setOnClickListener(new View.OnClickListener() {
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
        return branchTypeHomeResponseModels.size();
    }
}
