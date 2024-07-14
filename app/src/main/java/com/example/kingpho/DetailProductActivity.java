package com.example.kingpho;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.kingpho.adapter.ImageProductAdapter;
import com.example.kingpho.callback.ProductCallback;
import com.example.kingpho.fragment.MainActivity;
import com.example.kingpho.model.ImageProduct;
import com.example.kingpho.model.Product;
import com.example.kingpho.service.ProductService;
import com.example.kingpho.utils.RetrofitClient;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailProductActivity extends AppCompatActivity {

    private ProductService productService;

    private ViewPager2 vpListImage;
    private CircleIndicator3 circleIndicator;
    private ImageView imgGoBack, imgTym, imgMinus, imgPlus, imgNext;
    private TextView tvNameProduct, price, sumPrice, description, seeMore;
    private EditText numberProduct;
    private Button btnAddToCard;
    private boolean isFavor = false;
    private static int PRICE = 45000;
    private List<String> arrayImage;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentPosition = vpListImage.getCurrentItem();
            if (currentPosition == arrayImage.size() - 1) {
                vpListImage.setCurrentItem(0);
            } else {
                vpListImage.setCurrentItem(currentPosition +1 );
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        Intent intent = new Intent();
        int foodId = intent.getIntExtra("foodId", 1);

        Retrofit retrofit = RetrofitClient.getRetrofitInstance(this);
        productService = retrofit.create(ProductService.class);

        mapping();
        vpListImage.setOffscreenPageLimit(3);
        vpListImage.setClipToPadding(false);
        vpListImage.setClipChildren(false);

        arrayImage = new ArrayList<>();

        vpListImage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 2000);
            }
        });

        getProductById(foodId, new ProductCallback() {
            @Override
            public void onListProductFetched(List<Product> productList) {

            }

            @Override
            public void onProductFetched(Product product) {
                if (product != null) {
                    tvNameProduct.setText(product.getName());
                    price.setText(formatMoney(String.valueOf((int) product.getPrice())));
                    sumPrice.setText(formatMoney(String.valueOf((int) product.getPrice())));
                    description.setText(product.getDescription());
                    PRICE = (int) product.getPrice();

                    arrayImage.addAll(product.getImageUrls());
                    ImageProductAdapter adapter = new ImageProductAdapter(arrayImage);
                    vpListImage.setAdapter(adapter);
                    circleIndicator.setViewPager(vpListImage);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }
        });

        numberProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    return;
                }
                int num = Integer.parseInt(s.toString());
                if (num > 100) {
                    numberProduct.setText(String.valueOf(100));
                    numberProduct.setSelection(numberProduct.getText().length());
                    Toast.makeText(DetailProductActivity.this, "Vui lòng không vượt quá 100", Toast.LENGTH_SHORT).show();
                }
                else {
                    sumPrice.setText(formatMoney(String.valueOf(PRICE*num)));
                }
            }
        });
        imgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusAction();
            }
        });
        imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusAction();
            }
        });
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeMoreAction();
            }
        });
        seeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeMoreAction();
            }
        });
        btnAddToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCard();
            }
        });
    }

    private void mapping() {
        vpListImage = findViewById(R.id.vpListImage);
        circleIndicator = findViewById(R.id.circleIndicator);
        imgGoBack = findViewById(R.id.imgGoBack);
        tvNameProduct = findViewById(R.id.tvNameProduct);
        imgTym = findViewById(R.id.imgTym);
        price = findViewById(R.id.price);
        imgMinus = findViewById(R.id.imgMinus);
        numberProduct = findViewById(R.id.numberProduct);
        imgPlus =findViewById(R.id.imgPlus);
        sumPrice = findViewById(R.id.sumPrice);
        btnAddToCard = findViewById(R.id.btnAddToCard);
        description = findViewById(R.id.description);
        seeMore = findViewById(R.id.seeMore);
        imgNext = findViewById(R.id.imgNext);
    }


    private void goBack() {
        finish();
    }
    private void minusAction() {
        String numString = numberProduct.getText().toString().trim();
        if (numString.equals("0")) {
            return;
        } else {
            int num = Integer.parseInt(numString);
            numberProduct.setText(String.valueOf(num - 1));
            num--;
            sumPrice.setText(formatMoney(String.valueOf(PRICE * num)));
        }

    }
    private void plusAction() {
        String numString = numberProduct.getText().toString().trim();
        int num = Integer.parseInt(numString);
        numberProduct.setText(String.valueOf(num + 1));
        num++;
        sumPrice.setText(formatMoney(String.valueOf(PRICE * num)));
    }
    public void tymAction(View view) {
        if (isFavor) {
            imgTym.setImageResource(R.drawable.tym);
        } else {
            imgTym.setImageResource(R.drawable.favorite);
        }
        isFavor = !isFavor;
    }
    private void addToCard() {
        Toast.makeText(this, "product added to cart", Toast.LENGTH_SHORT).show();
    }
    private void seeMoreAction() {
        Intent intent = new Intent(this, RatingActivity.class);
        startActivity(intent);
    }
    public static String formatMoney(String moneyString) {
        try {
            int moneyAmount = Integer.parseInt(moneyString);

            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());

            DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
            decimalFormat.applyPattern("#,###");

            return decimalFormat.format(moneyAmount);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return moneyString;
        }
    }

    public void getProductById(int id, ProductCallback callback) {
        try {
            Call<Product> call = productService.getProductById(id);
            call.enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Product product = response.body();

                        callback.onProductFetched(product);
                    }
                }

                @Override
                public void onFailure(Call<Product> call, Throwable throwable) {
                    Toast.makeText(DetailProductActivity.this, "Failed to get product", Toast.LENGTH_LONG).show();
                    throwable.printStackTrace();
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 2000);
    }
}
