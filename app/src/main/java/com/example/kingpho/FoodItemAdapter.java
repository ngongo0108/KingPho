package com.example.kingpho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.ViewHolder> {

    private Context context;
    private List<FoodItem> foodItems;

    public FoodItemAdapter(Context context, List<FoodItem> foodItems) {
        this.context = context;
        this.foodItems = foodItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItem foodItem = foodItems.get(position);
        holder.tvFoodDetail.setText(String.valueOf(foodItem.getQuantity()));
        holder.tvFoodName.setText(foodItem.getName());
        holder.tvFoodDetails.setText(String.valueOf(foodItem.getPrice()));
        holder.ivFoodImage.setImageResource(foodItem.getImageResId());
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFoodDetail, tvFoodName, tvFoodDetails;
        ShapeableImageView ivFoodImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFoodDetail = itemView.findViewById(R.id.tvFoodDetail);
            tvFoodName = itemView.findViewById(R.id.tvFoodName);
            tvFoodDetails = itemView.findViewById(R.id.tvFoodDetails);
            ivFoodImage = itemView.findViewById(R.id.ivFoodImage);
        }
    }
}
