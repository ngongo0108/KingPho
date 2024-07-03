package com.example.kingpho;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class GoingOnFragment extends Fragment {

    private RecyclerView recyclerViewOrders;
    private OrderAdapter orderAdapter;
    private List<Order> orderList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_going_on, container, false);

        recyclerViewOrders = view.findViewById(R.id.recyclerViewOrders);
        recyclerViewOrders.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the order list and adapter
        orderList = new ArrayList<>();
        // Add some demo data
        orderList.add(new Order(R.drawable.ic_launcher_foreground, "Pho Special", "$10.99", "In Progress"));
        orderList.add(new Order(R.drawable.ic_launcher_foreground, "Pho Beef", "$9.99", "In Progress"));

        orderAdapter = new OrderAdapter(getContext(), orderList, new OrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Order order) {
                // Handle tracking button click
            }
        });

        recyclerViewOrders.setAdapter(orderAdapter);

        return view;
    }
}
