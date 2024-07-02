package com.example.kingpho;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryActivity extends AppCompatActivity {

    private ListView transactionsListView;
    private TransactionAdapter adapter;
    private List<Transaction> transactionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);  // Ensure this is the correct layout file

        transactionsListView = findViewById(R.id.transactionsListView);  // Ensure this ID matches your XML

        // Sample data
        transactionList = new ArrayList<>();
        transactionList.add(new Transaction("2024-06-28", "Pho Bo", 8.5, "pho_bo_image"));
        transactionList.add(new Transaction("2024-06-27", "Pho Ga", 7.5, "pho_ga_image"));
        transactionList.add(new Transaction("2024-06-26", "Pho Chay", 6.5, "pho_chay_image"));

        adapter = new TransactionAdapter(this, transactionList);
        transactionsListView.setAdapter(adapter);
    }
}

