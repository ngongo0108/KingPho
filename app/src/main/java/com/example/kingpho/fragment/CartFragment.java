package com.example.kingpho.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kingpho.Interface.ChangeNumberItemsListener;
import com.example.kingpho.PaymentActivity;
import com.example.kingpho.helper.Manager;
import com.example.kingpho.R;
import com.example.kingpho.adapter.CartAdapter;
import com.example.kingpho.helper.TinyDB;
import com.example.kingpho.model.Food;
import java.util.ArrayList;
import java.util.HashMap;

public class CartFragment extends Fragment implements ChangeNumberItemsListener, CartAdapter.OnItemCheckedChangeListener {

    private RecyclerView recyclerViewCart;
    private TextView itemTotalTxt, itemTotal, deliveryTxt, deliveryTotal, totalTxt, total;
    private Button checkoutBtn;
    private ImageView imageViewEmptyCart;
    private LinearLayout itemTotalLayout, deliveryLayout, totalLayout;
    private ArrayList<Food> cartList;
    private Manager manager;
    private CartAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerViewCart = view.findViewById(R.id.recyclerViewCart);
        itemTotalTxt = view.findViewById(R.id.itemTotalTxt);
        itemTotal = view.findViewById(R.id.itemTotal);
        deliveryTxt = view.findViewById(R.id.deliveryTxt);
        deliveryTotal = view.findViewById(R.id.deliveryTotal);
        totalTxt = view.findViewById(R.id.totalTxt);
        total = view.findViewById(R.id.total);
        checkoutBtn = view.findViewById(R.id.checkoutBtn);
        imageViewEmptyCart = view.findViewById(R.id.imageViewEmptyCart);
        itemTotalLayout = view.findViewById(R.id.itemTotalLayout);
        deliveryLayout = view.findViewById(R.id.deliveryLayout);
        totalLayout = view.findViewById(R.id.totalLayout);

        manager = new Manager(getContext(), new TinyDB(getContext()));
        cartList = manager.getListCart();

        setupRecyclerView();
        updateTotal();

        checkoutBtn.setOnClickListener(v -> {
            double finalTotalPrice = calculateFinalTotalPrice();
            Intent intent = new Intent(getContext(), PaymentActivity.class);
            intent.putExtra("total_price", finalTotalPrice);
            startActivity(intent);
        });

        return view;
    }

    private void setupRecyclerView() {
        adapter = new CartAdapter(cartList, manager, this, this);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewCart.setAdapter(adapter);
    }

    private void updateTotal() {
        double totalPrice = 0;
        int totalQuantity = 0;
        HashMap<Integer, Boolean> checkedStates = adapter.getCheckedStates();

        for (int i = 0; i < cartList.size(); i++) {
            if (checkedStates.getOrDefault(i, false)) {
                Food item = cartList.get(i);
                totalPrice += item.getFoodPrice() * item.getNumberInCart();
                totalQuantity += item.getNumberInCart();
            }
        }

        double deliveryFee = totalPrice * 0.2;
        double totalOrder = totalPrice + deliveryFee;

        itemTotal.setText(String.valueOf(totalQuantity));
        deliveryTotal.setText(String.format("%.2fđ", deliveryFee));
        total.setText(String.format("%.2fđ", totalOrder));

        if (cartList.isEmpty()) {
            imageViewEmptyCart.setVisibility(View.VISIBLE);
            recyclerViewCart.setVisibility(View.GONE);
            itemTotalLayout.setVisibility(View.GONE);
            deliveryLayout.setVisibility(View.GONE);
            totalLayout.setVisibility(View.GONE);
            checkoutBtn.setVisibility(View.GONE);
        } else {
            imageViewEmptyCart.setVisibility(View.GONE);
            recyclerViewCart.setVisibility(View.VISIBLE);
            itemTotalLayout.setVisibility(View.VISIBLE);
            deliveryLayout.setVisibility(View.VISIBLE);
            totalLayout.setVisibility(View.VISIBLE);
            checkoutBtn.setVisibility(View.VISIBLE);
        }
    }

    private double calculateFinalTotalPrice() {
        double totalPrice = 0;
        HashMap<Integer, Boolean> checkedStates = adapter.getCheckedStates();
        for (int i = 0; i < cartList.size(); i++) {
            if (checkedStates.getOrDefault(i, false)) {
                Food item = cartList.get(i);
                totalPrice += item.getFoodPrice() * item.getNumberInCart();
            }
        }
        double deliveryFee = totalPrice * 0.2;
        return totalPrice + deliveryFee;
    }

    @Override
    public void changed() {
        updateTotal();
    }

    @Override
    public void onItemCheckedChanged() {
        updateTotal();
    }
}