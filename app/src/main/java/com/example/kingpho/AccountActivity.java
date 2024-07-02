package com.example.kingpho;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kingpho.adapter.AccountItemAdapter;
import com.example.kingpho.model.AccountItem;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {
    private ShapeableImageView imgAvatar;
    private ImageView imgEdit;
    private TextView tvName, tvEmail;
    private ListView lvItem;
    private Button btnLogOut;
    private ArrayList<AccountItem> arrayItem;
    private AccountItemAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mapping();
        arrayItem = getDataList();
        adapter = new AccountItemAdapter(this, R.layout.item_function_user, arrayItem);
        lvItem.setAdapter(adapter);

        // set value
        tvName.setText("Thu Huong");
        tvEmail.setText("huong@gmail.com");
        imgEdit.setImageResource(R.drawable.edit);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutAction();
            }
        });
    }

    private void mapping() {
        imgAvatar = findViewById(R.id.imgAvatar);
        imgEdit = findViewById(R.id.imgEdit);
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        lvItem = findViewById(R.id.lvItem);
        btnLogOut = findViewById(R.id.btnLogOut);
    }

    private ArrayList<AccountItem> getDataList() {
        ArrayList<AccountItem> list = new ArrayList<>();
        list.add(new AccountItem(R.drawable.orders, "Order"));
        list.add(new AccountItem(R.drawable.mydetails, "My Details"));
        list.add(new AccountItem(R.drawable.delivery, "Delivery Address"));
        list.add(new AccountItem(R.drawable.payment, "Payment Methods"));
        list.add(new AccountItem(R.drawable.promo, "Promo Cord"));
        list.add(new AccountItem(R.drawable.bell, "Notification"));
        list.add(new AccountItem(R.drawable.help, "Help"));
        list.add(new AccountItem(R.drawable.about, "About"));
        return list;
    }
    private void logoutAction() {
        Toast.makeText(this, "Logout Action", Toast.LENGTH_SHORT).show();
    }
}
