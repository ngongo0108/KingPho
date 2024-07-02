package com.example.kingpho;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class OrderTrackingActivity extends AppCompatActivity {

    private TextView tvEstimatedTimeValue;
    private TextView tvOrderNumberValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_tracking);

        tvEstimatedTimeValue = findViewById(R.id.tvEstimatedTimeValue);
        tvOrderNumberValue = findViewById(R.id.tvOrderNumberValue);

        // Set the estimated time and order number values
        tvEstimatedTimeValue.setText("30 minutes");
        tvOrderNumberValue.setText("#2482011");

        // Additional setup if needed
    }
}

