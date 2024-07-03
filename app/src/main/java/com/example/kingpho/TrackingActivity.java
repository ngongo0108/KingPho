package com.example.kingpho;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.widget.TextView;

public class TrackingActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTrackingSteps;
    private TrackingStepsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        recyclerViewTrackingSteps = findViewById(R.id.recyclerViewTrackingSteps);
        recyclerViewTrackingSteps.setLayoutManager(new LinearLayoutManager(this));

        // Receive data from intent
        String foodName = getIntent().getStringExtra("foodName");
        String estimatedTime = getIntent().getStringExtra("estimatedTime");
        String price = getIntent().getStringExtra("price");

        // Set data to TextViews
        TextView textViewFoodName = findViewById(R.id.textViewFoodName);
        textViewFoodName.setText(foodName);

        TextView textViewEstimatedTime = findViewById(R.id.textViewEstimatedTime);
        textViewEstimatedTime.setText("Estimated Time: " + estimatedTime);

        TextView textViewPrice = findViewById(R.id.textViewPrice);
        textViewPrice.setText("Price: " + price);

        // Simulated tracking steps (replace with actual logic)
        List<TrackingStep> steps = new ArrayList<>();
        steps.add(new TrackingStep("Order confirmed", R.drawable.ic_check_circle));
        steps.add(new TrackingStep("Preparing", R.drawable.ic_check_circle_gray));
        steps.add(new TrackingStep("Out for delivery", R.drawable.ic_check_circle_gray));
        steps.add(new TrackingStep("Delivered", R.drawable.ic_check_circle_gray));

        // Set up the adapter
        adapter = new TrackingStepsAdapter(steps);
        recyclerViewTrackingSteps.setAdapter(adapter);
    }
}
