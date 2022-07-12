package com.example.furniture_gallery.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniture_gallery.Model.UserResponseModel.CategoryHomeResponseModel;
import com.example.furniture_gallery.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryHomeAdapter extends RecyclerView.Adapter<CategoryHomeAdapter.ViewHolder> {

    List<CategoryHomeResponseModel> categoryHomeResponseModelList;
    private SetOnCategoryItemClickListener mclickListener;

    public CategoryHomeAdapter(List<CategoryHomeResponseModel> categoryHomeResponseModelList) {
        this.categoryHomeResponseModelList = categoryHomeResponseModelList;
    }

    public interface SetOnCategoryItemClickListener {
        void onItemClick(int position);

    }

    public void BtnOnCategoryItemClickListener(SetOnCategoryItemClickListener clickListener) {
        mclickListener = clickListener;
    }

    @NonNull
    @Override
    public CategoryHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHomeAdapter.ViewHolder holder, int position) {

        CategoryHomeResponseModel categoryHomeResponseModel = categoryHomeResponseModelList.get(position);

        Picasso.get().load(categoryHomeResponseModel.getImage()).into(holder.CategoryImage);
        holder.CategoryTitle.setText(categoryHomeResponseModel.getName());

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView CategoryImage;
        TextView CategoryTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CategoryImage = itemView.findViewById(R.id.CategoryImageList);
            CategoryTitle = itemView.findViewById(R.id.CategoryTitleList);

        }
    }
    @Override
    public int getItemCount() {
        return categoryHomeResponseModelList.size();
    }
}
