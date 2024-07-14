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
import com.example.kingpho.model.Product;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends BaseAdapter {

    private Context context;
    private List<Product> productList;
    private List<Product> filteredProductList;

    public FoodAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.filteredProductList = new ArrayList<>(productList);
    }

    @Override
    public int getCount() {
        return filteredProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredProductList.get(position);
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
            holder.titleTextView = convertView.findViewById(R.id.titleMethod);
            holder.priceTextView = convertView.findViewById(R.id.price);
            holder.addToCartButton = convertView.findViewById(R.id.addtocartBtn);
            holder.favouriteButton = convertView.findViewById(R.id.favouriteBtnAdd);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Product product = filteredProductList.get(position);

        holder.titleTextView.setText(product.getName());
        holder.priceTextView.setText(String.valueOf(product.getPrice()));

//        int drawableResourceId = context.getResources().getIdentifier(product.getFoodImage(), "drawable", context.getPackageName());
//        Glide.with(context).load(drawableResourceId).into(holder.imageView);

        if (product.getImageUrls() != null) {
            if (!product.getImageUrls().isEmpty()) {
                String imageProductUrls = product.getImageUrls().get(0);
                Glide.with(context).load(imageProductUrls).into(holder.imageView);
            }
            else {
                holder.imageView.setImageResource(R.drawable.icon_pho);
            }
        }
        else {
            holder.imageView.setImageResource(R.drawable.icon_pho);
        }

        holder.addToCartButton.setOnClickListener(v -> {
            // Implement add to cart functionality
        });

        holder.favouriteButton.setOnClickListener(v -> {
            // Implement add to favourite functionality
        });

        return convertView;
    }

    public void filter(String query) {
        filteredProductList.clear();

        if (query.isEmpty()) {
            filteredProductList.addAll(productList);
        } else {
               query = query.toLowerCase();
            for (Product product : productList) {
                if (product.getName().toLowerCase().contains(query)) {
                    filteredProductList.add(product);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void updateData(List<Product> newFoodList) {
        this.productList.clear();
        this.productList.addAll(newFoodList);
        this.filteredProductList.clear();
        this.filteredProductList.addAll(newFoodList);
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
