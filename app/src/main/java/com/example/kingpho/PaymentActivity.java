package com.example.kingpho;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kingpho.adapter.PaymentMethodAdapter;
import com.example.kingpho.model.PaymentMethod;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {
    public static final String EXTRA_TOTAL_PRICE = "com.example.kingpho.EXTRA_TOTAL_PRICE";
    private ImageView imgGoBack;
    private ListView lvMethod;
    private TextView totalPrice;
    private Button btnCheckout;
    private ArrayList<PaymentMethod> methodArray;
    private PaymentMethodAdapter methodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mapping();

        methodArray = getData();

        Toast.makeText(PaymentActivity.this, methodArray.size() + "", Toast.LENGTH_SHORT).show();
        methodAdapter = new PaymentMethodAdapter(this, R.layout.item_payment, methodArray);
        lvMethod.setAdapter(methodAdapter);


        lvMethod.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PaymentMethod payment = methodArray.get(position);
                Toast.makeText(PaymentActivity.this, payment.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        imgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaymentActivity.this, "Thanh toan thanh cong", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PaymentActivity.this, MyOrderActivity.class);
                startActivity(intent);
                finish();
            }
        });
        // Get the total price from the intent
        double total = getIntent().getDoubleExtra(EXTRA_TOTAL_PRICE, 0);
        totalPrice.setText(String.format("$%.2f", total));
    }
    private void mapping() {
        imgGoBack = findViewById(R.id.imgGoBack);
        totalPrice = findViewById(R.id.totalPrice);
        btnCheckout = findViewById(R.id.btnCheckout);
        lvMethod = findViewById(R.id.lvMethod);
    }
    private ArrayList<PaymentMethod> getData() {
        ArrayList<PaymentMethod> list = new ArrayList<>();
        list.add(new PaymentMethod(R.drawable.cash, "Cash"));
        list.add(new PaymentMethod(R.drawable.zalopay, "Zalo Pay"));
        return list;
    }
}
