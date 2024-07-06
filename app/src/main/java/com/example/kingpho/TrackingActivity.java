package com.example.kingpho;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TrackingActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFoodItems;
    private RecyclerView recyclerViewTrackingSteps;
    private FoodItemsAdapter foodItemsAdapter;
    private TrackingStepsAdapter trackingStepsAdapter;
    private List<FoodItem> foodItems;
    private List<TrackingStep> trackingSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        recyclerViewFoodItems = findViewById(R.id.recyclerViewFoodItems);
        recyclerViewTrackingSteps = findViewById(R.id.recyclerViewTrackingSteps);

        recyclerViewFoodItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTrackingSteps.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve data passed from GoingOnAdapter
        Order order = (Order) getIntent().getSerializableExtra("order");

        // Populate order information in views
        ImageView imageViewFood = findViewById(R.id.imageViewFood);
        TextView textViewOrderNumber = findViewById(R.id.textViewOrderNumber);
        TextView textViewEstimatedTime = findViewById(R.id.textViewEstimatedTime);
        TextView textViewTotalPrice = findViewById(R.id.textViewTotalPrice);

        imageViewFood.setImageResource(order.getImg()); // Set image resource
        textViewOrderNumber.setText(order.getOrderNumber()); // Set order number
        textViewEstimatedTime.setText("Estimated Time: " + order.getEstimatedTime()); // Set estimated time
        textViewTotalPrice.setText("Total Price: $" + order.getTotalPrice()); // Set total price

        // Set up back button
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // Set up RecyclerView adapter for food items
        foodItems = order.getFoodItems();
        foodItemsAdapter = new FoodItemsAdapter(foodItems);
        recyclerViewFoodItems.setAdapter(foodItemsAdapter);

        // Example data (replace with actual fetched data)
        trackingSteps = new ArrayList<>();
        trackingSteps.add(new TrackingStep("Order Placed", "We have received your order.", R.drawable.order_placed, "completed"));
        trackingSteps.add(new TrackingStep("Order Confirmed", "Your order has been confirmed.", R.drawable.order_confirmed, "completed"));
        trackingSteps.add(new TrackingStep("Order Processed", "We are preparing your order.", R.drawable.order_processing, "ongoing"));
        trackingSteps.add(new TrackingStep("Ready to Pickup", "Your order is ready for pickup.", R.drawable.order_ready, "pending"));

        // Set up RecyclerView adapter for tracking steps
        trackingStepsAdapter = new TrackingStepsAdapter(trackingSteps);
        recyclerViewTrackingSteps.setAdapter(trackingStepsAdapter);
    }
}
