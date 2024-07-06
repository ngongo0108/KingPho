package com.example.kingpho.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kingpho.R;
import com.example.kingpho.TrackingActivity;
import com.example.kingpho.model.Order;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class GoingOnAdapter extends RecyclerView.Adapter<GoingOnAdapter.ViewHolder> {

    private Context context;
    private List<Order> ongoingOrders;

    public GoingOnAdapter(Context context, List<Order> ongoingOrders) {
        this.context = context;
        this.ongoingOrders = ongoingOrders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = ongoingOrders.get(position);
        holder.textViewTitleGoingOn.setText(order.getOrderNumber());
        holder.textViewPriceGoingOn.setText(String.format("$%.2f", order.getTotalPrice()));
        holder.textViewStatusGoingOn.setText(order.getStatus());
        holder.imageViewOrderGoingOn.setImageResource(order.getImg());

        holder.buttonTrackingGoingOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TrackingActivity.class);
                intent.putExtra("order", order); // Pass order object to TrackingActivity
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ongoingOrders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitleGoingOn;
        TextView textViewPriceGoingOn;
        TextView textViewStatusGoingOn;
        Button buttonTrackingGoingOn;
        ShapeableImageView imageViewOrderGoingOn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitleGoingOn = itemView.findViewById(R.id.textViewTitleGoingOn);
            textViewPriceGoingOn = itemView.findViewById(R.id.textViewPriceGoingOn);
            textViewStatusGoingOn = itemView.findViewById(R.id.textViewStatusGoingOn);
            buttonTrackingGoingOn = itemView.findViewById(R.id.buttonTrackingGoingOn);
            imageViewOrderGoingOn = itemView.findViewById(R.id.imageViewOrderGoingOn);
        }
    }
}
