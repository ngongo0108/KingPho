package com.example.kingpho;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.kingpho.fragment.CompletedFragment;
import com.example.kingpho.fragment.GoingOnFragment;
import com.example.kingpho.model.FoodItem;
import com.example.kingpho.model.Order;

import java.util.ArrayList;
import java.util.List;

public class MyOrderActivity extends AppCompatActivity {

    private Button btnGoingOn, btnCompleted;
    private ImageButton btnBack;

    // Sample data for ongoing and completed orders
    private List<Order> ongoingOrders;
    private List<Order> completedOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        // Initialize sample data (you can fetch or create actual data here)
        initializeSampleData();

        btnBack = findViewById(R.id.btnBack);
        btnGoingOn = findViewById(R.id.btnGoingOn);
        btnCompleted = findViewById(R.id.btnCompleted);

        // Handle back button click
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Load the GoingOnFragment by default and set button states
        loadOngoingTransactions();
        btnGoingOn.setBackgroundColor(ContextCompat.getColor(MyOrderActivity.this, R.color.yellow));
        btnCompleted.setBackgroundColor(ContextCompat.getColor(MyOrderActivity.this, android.R.color.white));

        btnGoingOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnGoingOn.setBackgroundColor(ContextCompat.getColor(MyOrderActivity.this, R.color.yellow));
                btnCompleted.setBackgroundColor(ContextCompat.getColor(MyOrderActivity.this, android.R.color.white));
                loadOngoingTransactions();
            }
        });

        btnCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCompleted.setBackgroundColor(ContextCompat.getColor(MyOrderActivity.this, R.color.yellow));
                btnGoingOn.setBackgroundColor(ContextCompat.getColor(MyOrderActivity.this, android.R.color.white));
                loadCompletedTransactions();
            }
        });
    }

    private void initializeSampleData() {
        // Sample data for ongoing orders
        ongoingOrders = new ArrayList<>();
        List<FoodItem> ongoingFoodItems = new ArrayList<>();
        ongoingFoodItems.add(new FoodItem("Pho Ga", 9.99, R.drawable.pdb, 2));
        ongoingFoodItems.add(new FoodItem("Pho Bo", 11.99, R.drawable.pdb, 1));
        double ongoingTotalPrice = calculateTotalPrice(ongoingFoodItems);
        ongoingOrders.add(new Order(ongoingFoodItems, "Ongoing", "Order #001", ongoingTotalPrice, R.drawable.ic_launcher_background, "30 minutes"));

        // Sample data for completed orders
        completedOrders = new ArrayList<>();
        List<FoodItem> completedFoodItems = new ArrayList<>();
        completedFoodItems.add(new FoodItem("Pho Hai San", 14.99, R.drawable.pdb, 2));
        double completedTotalPrice = calculateTotalPrice(completedFoodItems);
        completedOrders.add(new Order(completedFoodItems, "Completed", "Order #002", completedTotalPrice, R.drawable.ic_launcher_background, "45 minutes"));
    }

    private double calculateTotalPrice(List<FoodItem> foodItems) {
        double totalPrice = 0;
        for (FoodItem item : foodItems) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        return totalPrice;
    }

    private void loadOngoingTransactions() {
        loadFragment(new GoingOnFragment(ongoingOrders));
    }

    private void loadCompletedTransactions() {
        loadFragment(new CompletedFragment(completedOrders));
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.orderFrameLayout, fragment);
        fragmentTransaction.commit();
    }
}
