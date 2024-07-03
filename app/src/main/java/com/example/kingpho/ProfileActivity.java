package com.example.kingpho;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kingpho.adapter.MyDetailAdapt;
import com.example.kingpho.model.MyDetail;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private ImageView imgGoBack, camera;
    private TextView save;
    private ListView lvInfoUser;
    private ArrayList<MyDetail> arrayInfo;
    private MyDetailAdapt adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_detail);

        mapping();

        arrayInfo = getDataList();
        adapter = new MyDetailAdapt(this, R.layout.item_my_detail, arrayInfo);
        lvInfoUser.setAdapter(adapter);
        lvInfoUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyDetail detail = arrayInfo.get(position);
                detail.setInfo("1234");
            }
        });
        imgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, arrayInfo.get(0).getInfo(), Toast.LENGTH_SHORT).show();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void mapping() {
        imgGoBack = findViewById(R.id.imgGoBack);
        camera = findViewById(R.id.camera);
        save = findViewById(R.id.save);
        lvInfoUser = findViewById(R.id.lvInfoUser);
    }
    private ArrayList<MyDetail> getDataList() {
        ArrayList<MyDetail> list = new ArrayList<>();
        list.add(new MyDetail("Username", "Amanda Jane"));
        list.add(new MyDetail("Email", "amanda@gmail.com"));
        list.add(new MyDetail("Phone", "+65 2311 333"));
        list.add(new MyDetail("Date of birth", "20/05/1990"));
        list.add(new MyDetail("Address", "123 Royal Street, New York"));
        return list;
    }
    private void goBack() {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
        finish();
    }
}
