package com.example.kingpho;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity {
    private List<FoodItem> foodItems; // Biến instance để lưu danh sách các mặt hàng

    private TextView tvTotalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        TextView tvOrderId = findViewById(R.id.tvOrderId);
        tvTotalAmount = findViewById(R.id.tvTotalAmount); // Sử dụng biến instance đã khai báo

        RecyclerView rvOrderedItems = findViewById(R.id.rvOrderedItems);
        RatingBar rbRating = findViewById(R.id.rbRating);
        EditText etProductReview = findViewById(R.id.etProductReview);
        Button btnSubmitReview = findViewById(R.id.btnSubmitReview);

        // Khởi tạo dữ liệu cho danh sách foodItems
        foodItems = new ArrayList<>();
        foodItems.add(new FoodItem("Pizza", "Cheese Pizza", 2, 10.00, R.drawable.phocuon));
        foodItems.add(new FoodItem("Burger", "Beef Burger", 1, 8.00, R.drawable.photron));
        foodItems.add(new FoodItem("Pasta", "Chicken Pasta", 3, 12.00, R.drawable.phokho));
        foodItems.add(new FoodItem("Pasta", "Chicken Pasta", 3, 12.00, R.drawable.phonuoc));
        foodItems.add(new FoodItem("Pasta", "Chicken Pasta", 3, 12.00, R.drawable.phoran));
        foodItems.add(new FoodItem("Pasta", "Chicken Pasta", 3, 12.00, R.drawable.phoran));
        foodItems.add(new FoodItem("Pasta", "Chicken Pasta", 3, 12.00, R.drawable.phoran));
        foodItems.add(new FoodItem("Pasta", "Chicken Pasta", 3, 12.00, R.drawable.phoran));
        foodItems.add(new FoodItem("Pasta", "Chicken Pasta", 3, 12.00, R.drawable.phoran));

        // Hiển thị thông tin đơn hàng
        String orderId = "#123456";
        double totalAmount = 150.00;

        tvOrderId.setText("Mã đơn: " + orderId);
        tvTotalAmount.setText("Tổng tiền: " + totalAmount + "đ");

        // Thiết lập RecyclerView và Adapter
        FoodItemAdapter adapter = new FoodItemAdapter(this, foodItems);
        rvOrderedItems.setLayoutManager(new GridLayoutManager(this, 1));
        rvOrderedItems.setAdapter(adapter);

        // Xử lý sự kiện khi click vào tvTotalAmount để hiển thị dialog chi tiết đơn hàng
        tvTotalAmount.setOnClickListener(v -> {
            OrderDetailsDialog dialog = new OrderDetailsDialog(OrderDetailsActivity.this, foodItems);
            dialog.show();
        });

        // Xử lý sự kiện khi thay đổi rating
        rbRating.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            if (fromUser) {
                Toast.makeText(OrderDetailsActivity.this, "Đánh giá: " + rating + " sao", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý sự kiện khi nhấn nút gửi đánh giá
        btnSubmitReview.setOnClickListener(v -> {
            String reviewText = etProductReview.getText().toString();
            float rating = rbRating.getRating();

            Toast.makeText(OrderDetailsActivity.this, "Đánh giá được gửi đi! Đánh giá: " + rating + " sao. Nhận xét: " + reviewText, Toast.LENGTH_SHORT).show();

            rbRating.setRating(0);
            etProductReview.setText("");
        });
    }
}
