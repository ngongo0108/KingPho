package com.example.kingpho.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kingpho.R;
import com.example.kingpho.model.FoodItem;

import java.util.List;

public class FoodItemsAdapter extends RecyclerView.Adapter<FoodItemsAdapter.FoodItemViewHolder> {

    private List<FoodItem> foodItems;

    public FoodItemsAdapter(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemViewHolder holder, int position) {
        FoodItem foodItem = foodItems.get(position);
        holder.textViewFoodName.setText(foodItem.getName());
        holder.textViewQuantity.setText("Quantity: " + foodItem.getQuantity());
        holder.textViewPrice.setText("Price: $" + foodItem.getTotalPrice()); // Display total price
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public static class FoodItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewFoodName, textViewQuantity, textViewPrice;

        public FoodItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFoodName = itemView.findViewById(R.id.textViewFoodName);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
        }
    }
}
