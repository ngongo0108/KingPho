package com.example.kingpho.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kingpho.R;
import com.example.kingpho.adapter.GoingOnAdapter;
import com.example.kingpho.model.Order;

import java.util.List;

public class GoingOnFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Order> ongoingOrders;
    private GoingOnAdapter adapter;

    public GoingOnFragment(List<Order> ongoingOrders) {
        this.ongoingOrders = ongoingOrders;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_going_on, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewGoingOn);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize adapter with ongoing orders
        adapter = new GoingOnAdapter(getContext(), ongoingOrders);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
