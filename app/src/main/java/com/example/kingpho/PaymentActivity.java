package com.example.kingpho;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {
    public static final String EXTRA_TOTAL_PRICE = "com.example.kingpho.EXTRA_TOTAL_PRICE";
    private TextView totalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        totalPriceTextView = findViewById(R.id.totalPriceTextView);

        // Get the total price from the intent
        double totalPrice = getIntent().getDoubleExtra(EXTRA_TOTAL_PRICE, 0);
        totalPriceTextView.setText(String.format("$%.2f", totalPrice));
    }
}
