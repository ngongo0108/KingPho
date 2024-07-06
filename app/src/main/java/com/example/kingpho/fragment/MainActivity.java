package com.example.kingpho.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.kingpho.R;
import com.example.kingpho.model.Food;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public LinearLayout homeBtn, productsBtn, favouriteBtn, profileBtn;
    private View activeButton;
    private FloatingActionButton cartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }

        homeBtn = findViewById(R.id.homeBtn);
        productsBtn = findViewById(R.id.produtsBtn);
        cartBtn = findViewById(R.id.cartBtn);
        favouriteBtn = findViewById(R.id.favouriteBtn);
        profileBtn = findViewById(R.id.profileBtn);

        homeBtn.setOnClickListener(this);
        productsBtn.setOnClickListener(this);
        cartBtn.setOnClickListener(this);
        favouriteBtn.setOnClickListener(this);
        profileBtn.setOnClickListener(this);

        // Đặt nút Home làm nút active mặc định
        setActiveButton(homeBtn);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.homeBtn) {
            replaceFragment(new HomeFragment());
            setActiveButton(homeBtn);
        } else if (v.getId() == R.id.produtsBtn) {
            replaceFragment(new ProductFragment());
            setActiveButton(productsBtn);
        }else if (v.getId() == R.id.cartBtn) {
            replaceFragment(new CartFragment());
            setActiveButton(cartBtn);
        } else if(v.getId() == R.id.favouriteBtn){
            replaceFragment(new FavouriteFragment());
            setActiveButton(favouriteBtn);
        }else if (v.getId() == R.id.profileBtn) {
            replaceFragment(new ProfileFragment());
            setActiveButton(profileBtn);
        }
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void setActiveButton(View activeBtn) {
        if (activeButton != null) {
            activeButton.setBackgroundColor(ContextCompat.getColor(this, R.color.color_gray));
        }
        activeBtn.setBackgroundResource(R.drawable.bg_btn_change);
        activeButton = activeBtn;
    }
}
