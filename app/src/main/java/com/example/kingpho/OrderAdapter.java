package com.example.kingpho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context context;
    private List<Order> orderList;
    private OnItemClickListener onItemClickListener;

    public OrderAdapter(Context context, List<Order> orderList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.orderList = orderList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.textViewTitle.setText(order.getTitle());
        holder.textViewPrice.setText(order.getPrice());
        holder.textViewStatus.setText(order.getStatus());
        holder.imageViewOrder.setImageResource(order.getImageResource());

        holder.buttonTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(order);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Order order);
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewOrder;
        TextView textViewTitle, textViewPrice, textViewStatus;
        Button buttonTracking;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewOrder = itemView.findViewById(R.id.imageViewOrder);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            buttonTracking = itemView.findViewById(R.id.buttonTracking);
        }
    }
}

