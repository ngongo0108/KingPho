package com.example.kingpho;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CompletedFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Order> completedOrders;
    private CompletedOrderAdapter adapter;

    public CompletedFragment(List<Order> completedOrders) {
        this.completedOrders = completedOrders;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_completed, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewCompleted);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CompletedOrderAdapter(getContext(), completedOrders, new CompletedOrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Order order) {
                // Handle item click (for example, reorder)
            }
        });
        recyclerView.setAdapter(adapter);

        return view;
    }
}
