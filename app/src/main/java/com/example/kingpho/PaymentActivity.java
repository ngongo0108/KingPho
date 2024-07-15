package com.example.kingpho;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kingpho.R;
import com.example.kingpho.adapter.PaymentMethodAdapter;
import com.example.kingpho.model.PaymentMethod;
import com.example.kingpho.paymentZalo.Api.CreateOrder;

import org.json.JSONObject;

import java.util.ArrayList;

import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class PaymentActivity extends AppCompatActivity {
    private ImageView imgGoBack;
    private ListView lvMethod;
    private TextView totalPriceTextView;
    private Button btnCheckout;
    private ArrayList<PaymentMethod> methodArray;
    private PaymentMethodAdapter methodAdapter;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mapping();

        methodArray = getData();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ZaloPay SDK Init
        ZaloPaySDK.init(2553, Environment.SANDBOX);

        methodAdapter = new PaymentMethodAdapter(this, R.layout.item_payment, methodArray);
        lvMethod.setAdapter(methodAdapter);

        Double totalPrice = getIntent().getDoubleExtra("total_price", (double) 0);
        String totalPriceString = String.format("%.0f", totalPrice);
        Log.d("PaymentActivity", "Get from CartFragment: " + totalPrice);
        totalPriceTextView.setText(totalPriceString);

        lvMethod.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PaymentMethod payment = methodArray.get(position);
                if (index != -1) {
                    methodAdapter.setSelectedItem(-1); // Reset previously selected item
                }
                methodAdapter.setSelectedItem(position); // Set the new selected item
                methodAdapter.notifyDataSetChanged();
                index = position; // Update previously selected position
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
                if (index == -1) {
                    Toast.makeText(PaymentActivity.this, "Vui lòng chọn phương thức thanh toán", Toast.LENGTH_SHORT).show();
                    return;
                }

                PaymentMethod selectedMethod = methodArray.get(index);
                if (selectedMethod.getTitle().equals("Zalo Pay")) {
                    CreateOrder orderApi = new CreateOrder();

                    try {
                        JSONObject data = orderApi.createOrder(totalPriceString);
                        Log.d("PaymentActivity", "Sent To Zalopay: " + totalPriceString);
                        String code = data.getString("return_code");
                        Log.d("PaymentActivity", "Return code: " + code);
                        Log.d("PaymentActivity", "Response data: " + data.toString());
                        if (code.equals("1")) {
                            String token = data.getString("zp_trans_token");
                            ZaloPaySDK.getInstance().payOrder(PaymentActivity.this, token, "demozpdk://app", new PayOrderListener() {
                                @Override
                                public void onPaymentSucceeded(String transactionId, String transToken, String appTransId) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            new AlertDialog.Builder(PaymentActivity.this)
                                                    .setTitle("Thanh toán thành công")
                                                    .setMessage(String.format("TransactionId: %s - TransToken: %s", transactionId, transToken))
                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            dialog.dismiss();
                                                            // Chuyển hướng sang trang Tracking
                                                            Intent intent = new Intent(PaymentActivity.this, TrackingActivity.class);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    })
                                                    .show();
                                        }
                                    });
                                }

                                @Override
                                public void onPaymentCanceled(String s, String s1) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            new AlertDialog.Builder(PaymentActivity.this)
                                                    .setTitle("Hủy Thanh Toán")
                                                    .setMessage("Bạn đã hủy thanh toán. Vui lòng thử lại.")
                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            dialog.dismiss();
                                                            // Quay lại PaymentActivity
                                                            finish();
                                                            startActivity(getIntent());
                                                        }
                                                    })
                                                    .show();
                                        }
                                    });
                                }

                                @Override
                                public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            new AlertDialog.Builder(PaymentActivity.this)
                                                    .setTitle("Lỗi Thanh Toán")
                                                    .setMessage("Đã xảy ra lỗi khi thanh toán. Vui lòng thử lại.")
                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            dialog.dismiss();
                                                            // Quay lại PaymentActivity
                                                            finish();
                                                            startActivity(getIntent());
                                                        }
                                                    })
                                                    .show();
                                        }
                                    });
                                }
                            });
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(PaymentActivity.this, "Vui lòng chọn ZaloPay để tiếp tục", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }

    private void mapping() {
        imgGoBack = findViewById(R.id.imgGoBack);
        totalPriceTextView = findViewById(R.id.totalPrice);
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
