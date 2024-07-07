package com.example.kingpho.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kingpho.Interface.ChangeNumberItemsListener;
import com.example.kingpho.R;
import com.example.kingpho.helper.Manager;
import com.example.kingpho.model.Food;

import java.util.ArrayList;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private ArrayList<Food> cartItemList;
    private Manager manager;
    private ChangeNumberItemsListener changeNumberItemsListener;
    private ArrayList<Food> selectedItems;

    public CartAdapter(ArrayList<Food> cartItemList, Manager manager, ChangeNumberItemsListener changeNumberItemsListener) {
        this.cartItemList = cartItemList;
        this.manager = manager;
        this.changeNumberItemsListener = changeNumberItemsListener;
        this.selectedItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Food item = cartItemList.get(position);
        holder.productName.setText(item.getFoodTitle());
        holder.productPrice.setText(String.valueOf(item.getFoodPrice()));
        holder.productQuantity.setText(String.valueOf(item.getNumberInCart()));
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(item.getFoodImage(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.productImage);

        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(selectedItems.contains(item));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!selectedItems.contains(item)) {
                        selectedItems.add(item);
                    }
                } else {
                    selectedItems.remove(item);
                }
            }
        });

        holder.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.minusNumberFood(cartItemList, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

        holder.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.plusNumberFood(cartItemList, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public ArrayList<Food> getSelectedItems() {
        return selectedItems;
    }
    public double getTotalPrice() {
        double total = 0;
        for (Food item : selectedItems) {
            total += item.getFoodPrice();
        }
        return total;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice, productQuantity;
        ImageView productImage, minusBtn, plusBtn;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.titleMethod);
            productPrice = itemView.findViewById(R.id.price);
            productQuantity = itemView.findViewById(R.id.textViewQuantity);
            productImage = itemView.findViewById(R.id.pic);
            minusBtn = itemView.findViewById(R.id.minusBtn);
            plusBtn = itemView.findViewById(R.id.plusBtn);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
