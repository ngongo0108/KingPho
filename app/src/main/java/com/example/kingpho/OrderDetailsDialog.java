package com.example.kingpho;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderDetailsDialog extends Dialog {

    private List<FoodItem> foodItems;

    public OrderDetailsDialog(@NonNull Context context, List<FoodItem> foodItems) {
        super(context);
        this.foodItems = foodItems;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_order_details);

        RecyclerView rvOrderItems = findViewById(R.id.rvOrderItems);
        TextView tvDialogTitle = findViewById(R.id.tvDialogTitle);

        tvDialogTitle.setText("Order Details");

        OrderItemAdapter adapter = new OrderItemAdapter(getContext(), foodItems);
        rvOrderItems.setLayoutManager(new LinearLayoutManager(getContext()));
        rvOrderItems.setAdapter(adapter);
    }
}
