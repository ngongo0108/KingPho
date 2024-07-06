package com.example.kingpho;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kingpho.adapter.FoodItemAdapter;
import com.example.kingpho.model.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        TextView tvOrderId = findViewById(R.id.tvOrderId);
        TextView tvTotalAmount = findViewById(R.id.tvTotalAmount);
        GridView gvOrderedItems = findViewById(R.id.gvOrderedItems);
        RatingBar rbRating = findViewById(R.id.rbRating);
        EditText etProductReview = findViewById(R.id.etProductReview);
        Button btnSubmitReview = findViewById(R.id.btnSubmitReview);


        String orderId = "#123456";
        double totalAmount = 150.00;


        tvOrderId.setText("Order ID: " + orderId);
        tvTotalAmount.setText("Total Amount: $" + totalAmount);


        List<FoodItem> foodItems = new ArrayList<>();
        foodItems.add(new FoodItem("Pizza", "Cheese Pizza", 2, 10.00, R.drawable.phocuon));
        foodItems.add(new FoodItem("Burger", "Beef Burger", 1, 8.00, R.drawable.photron));
        foodItems.add(new FoodItem("Pasta", "Chicken Pasta", 3, 12.00, R.drawable.phokho));
        foodItems.add(new FoodItem("Pasta", "Chicken Pasta", 3, 12.00, R.drawable.phonuoc));
        foodItems.add(new FoodItem("Pasta", "Chicken Pasta", 3, 12.00, R.drawable.phoran));



        FoodItemAdapter adapter = new FoodItemAdapter(this, foodItems);
        gvOrderedItems.setAdapter(adapter);


        rbRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser) {

                    Toast.makeText(OrderDetailsActivity.this, "Rated: " + rating + " stars", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Handle review submission
        btnSubmitReview.setOnClickListener(v -> {
            String reviewText = etProductReview.getText().toString();
            float rating = rbRating.getRating();




            Toast.makeText(OrderDetailsActivity.this, "Review submitted! Rating: " + rating + " stars. Review: " + reviewText, Toast.LENGTH_SHORT).show();

            rbRating.setRating(0);
            etProductReview.setText("");
        });
    }
}
