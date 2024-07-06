package com.example.kingpho.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kingpho.ProfileActivity;
import com.example.kingpho.R;
import com.example.kingpho.adapter.AccountItemAdapter;
import com.example.kingpho.model.AccountItem;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    private ShapeableImageView imgAvatar;
    private ImageView imgEdit;
    private TextView tvName, tvEmail;
    private ListView lvItem;
    private Button btnLogOut;
    private ArrayList<AccountItem> arrayItem;
    private AccountItemAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_account, container, false);

        mapping(view);
        arrayItem = getDataList();
        adapter = new AccountItemAdapter(getContext(), R.layout.item_function_user, arrayItem);
        lvItem.setAdapter(adapter);

        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        // set value
        imgAvatar.setImageResource(R.drawable.avatar);
        tvName.setText("Thu Huong");
        tvEmail.setText("huong@gmail.com");
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile();
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutAction();
            }
        });
        return view;
    }

    private void mapping(View view) {
        imgAvatar = view.findViewById(R.id.imgAvatar);
        imgEdit = view.findViewById(R.id.imgEdit);
        tvName = view.findViewById(R.id.tvName);
        tvEmail = view.findViewById(R.id.tvEmail);
        lvItem = view.findViewById(R.id.lvItem);
        btnLogOut = view.findViewById(R.id.btnLogOut);
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
        Toast.makeText(getContext(), "Logout Action", Toast.LENGTH_SHORT).show();
    }
    private void editProfile() {
        Intent intent = new Intent(getContext(), ProfileActivity.class);
        startActivity(intent);
    }
}
