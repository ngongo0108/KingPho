
package com.example.kingpho.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kingpho.Interface.ChangeNumberItemsListener;
import com.example.kingpho.helper.Manager;
import com.example.kingpho.R;
import com.example.kingpho.adapter.CartAdapter;
import com.example.kingpho.helper.TinyDB;

public class CartFragment extends Fragment {

    private RecyclerView recyclerViewCart;
    private CartAdapter cartAdapter;
    private Manager manager;
    private TextView textViewCart;
    private TextView itemTotalTxt;
    private TextView deliveryTxt;
    private TextView totalTxt;
    private TextView itemTotal;
    private TextView deliveryTotal;
    private TextView total;
    private ScrollView scrollView;
    private Button checkoutBtn;
    private ImageView imageViewEmptyCart;
    private LinearLayout itemTotalLayout , deliveryLayout,totalLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        TinyDB tinyDB = new TinyDB(requireContext());
        manager = new Manager(requireContext(), tinyDB);

        initView(view);
        initList();

        recyclerViewCart.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewCart.setAdapter(cartAdapter);
        CalculateCart();
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Checkout not implemented yet", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void initView(View view) {
        recyclerViewCart = view.findViewById(R.id.recyclerViewFav);
        imageViewEmptyCart = view.findViewById(R.id.emptyCartLayout);
         textViewCart = view.findViewById(R.id.textViewCart);
        checkoutBtn = view.findViewById(R.id.checkoutBtn);
        itemTotalTxt = view.findViewById(R.id.itemTotalTxt);
        deliveryTxt = view.findViewById(R.id.deliveryTxt);
        totalTxt = view.findViewById(R.id.totalTxt);
        scrollView = view.findViewById(R.id.scrollViewCart);
        itemTotal = view.findViewById(R.id.itemTotal);
        deliveryTotal = view.findViewById(R.id.deliveryTotal);
        total = view.findViewById(R.id.total);

        itemTotalLayout=view.findViewById(R.id.itemTotalLayout);
        deliveryLayout=view.findViewById(R.id.deliveryLayout);
        totalLayout=view.findViewById(R.id.totalLayout);
    }

    private void initList() {
        cartAdapter = new CartAdapter(manager.getListCart(), manager, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });

        recyclerViewCart.setAdapter(cartAdapter);
        updateEmptyCartUI();
    }

    private void CalculateCart() {
        double delivery = 10;
        double totalPrice = manager.getTotalPrice();
        double totalAmount = Math.round((totalPrice  + delivery) * 100.0) / 100.0;
        double itemTotalPrice = Math.round(totalPrice * 100.0) / 100.0;
        itemTotal.setText(String.valueOf(itemTotalPrice) + "đ");
        deliveryTotal.setText(String.valueOf(delivery) + "đ");
        total.setText(String.valueOf(totalAmount) + "đ");
    }

    private void updateEmptyCartUI() {
        if (manager.getListCart().isEmpty()) {
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
}

