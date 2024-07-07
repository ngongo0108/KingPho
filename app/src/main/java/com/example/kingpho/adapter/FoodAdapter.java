package com.example.kingpho.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kingpho.R;
import com.example.kingpho.model.FoodActivity;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends BaseAdapter {

    private Context context;
    private List<FoodActivity> foodList;
    private List<FoodActivity> filteredFoodList;

    public FoodAdapter(Context context, List<FoodActivity> foodList) {
        this.context = context;
        this.foodList = foodList;
        this.filteredFoodList = new ArrayList<>(foodList);
    }

    @Override
    public int getCount() {
        return filteredFoodList.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredFoodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.pic);
            holder.titleTextView = convertView.findViewById(R.id.title);
            holder.priceTextView = convertView.findViewById(R.id.price);
            holder.addToCartButton = convertView.findViewById(R.id.addtocartBtn);
            holder.favouriteButton = convertView.findViewById(R.id.favouriteBtnAdd);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        FoodActivity foodActivity = filteredFoodList.get(position);

        holder.titleTextView.setText(foodActivity.getFoodTitle());
        holder.priceTextView.setText(String.valueOf(foodActivity.getFoodPrice()));

        int drawableResourceId = context.getResources().getIdentifier(foodActivity.getFoodImage(), "drawable", context.getPackageName());
        Glide.with(context).load(drawableResourceId).into(holder.imageView);

        holder.addToCartButton.setOnClickListener(v -> {
            // Implement add to cart functionality
        });

        holder.favouriteButton.setOnClickListener(v -> {
            // Implement add to favourite functionality
        });

        return convertView;
    }

    public void filter(String query) {
        filteredFoodList.clear();

        if (query.isEmpty()) {
            filteredFoodList.addAll(foodList);
        } else {
            query = query.toLowerCase();
            for (FoodActivity foodActivity : foodList) {
                if (foodActivity.getFoodTitle().toLowerCase().contains(query)) {
                    filteredFoodList.add(foodActivity);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void updateData(List<FoodActivity> newFoodList) {
        this.foodList.clear();
        this.foodList.addAll(newFoodList);
        this.filteredFoodList.clear();
        this.filteredFoodList.addAll(newFoodList);
        notifyDataSetChanged();
    }

    static class ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView priceTextView;
        ImageView addToCartButton;
        ImageView favouriteButton;
    }
}
