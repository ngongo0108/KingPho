package com.example.kingpho;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private EditText edtFullname, edtNewUsername, edtEmail, edtPhone, edtNewPassword, edtConfirm;
    private Button btnSignUp;
    private TextView tvLogin;
    private ImageView ivEye1, ivEye;
    private boolean isPasswordVisible1 = false;
    private boolean isPasswordVisible2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtFullname = findViewById(R.id.edtFullname);
        edtNewUsername = findViewById(R.id.edtNewUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirm = findViewById(R.id.edtConfirm);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvLogin = findViewById(R.id.tvLogin);
        ivEye1 = findViewById(R.id.ivEye1);
        ivEye = findViewById(R.id.ivEye);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToLogin();
            }
        });

        ivEye1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePasswordVisibility(edtNewPassword, ivEye1, isPasswordVisible1);
                isPasswordVisible1 = !isPasswordVisible1;
            }
        });

        ivEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePasswordVisibility(edtConfirm, ivEye, isPasswordVisible2);
                isPasswordVisible2 = !isPasswordVisible2;
            }
        });
    }

    private void signUp() {
        String fullname = edtFullname.getText().toString().trim();
        String username = edtNewUsername.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String password = edtNewPassword.getText().toString().trim();
        String confirmPassword = edtConfirm.getText().toString().trim();

        if (TextUtils.isEmpty(fullname)) {
            edtFullname.setError("Full Name is required");
            edtFullname.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(username)) {
            edtNewUsername.setError("Username is required");
            edtNewUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            edtEmail.setError("Email is required");
            edtEmail.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Invalid email format");
            edtEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            edtPhone.setError("Phone Number is required");
            edtPhone.requestFocus();
            return;
        } else if (!isValidPhoneNumber(phone)) {
            edtPhone.setError("Invalid phone number");
            edtPhone.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            edtNewPassword.setError("Password is required");
            edtNewPassword.requestFocus();
            return;
        } else if (password.length() < 6) {
            edtNewPassword.setError("Password must be at least 6 characters long");
            edtNewPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            edtConfirm.setError("Passwords do not match");
            edtConfirm.requestFocus();
            return;
        }

        Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();
        navigateToLogin();
    }

    private void navigateToLogin() {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void togglePasswordVisibility(EditText editText, ImageView imageView, boolean isPasswordVisible) {
        if (isPasswordVisible) {

            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            imageView.setImageResource(R.drawable.baseline_remove_red_eye_24);
        } else {

            editText.setTransformationMethod(null);
        }
        isPasswordVisible = !isPasswordVisible;
        editText.setSelection(editText.getText().length());
    }

    private boolean isValidPhoneNumber(String phone) {
        Pattern pattern = Pattern.compile("^(\\+84|0)(3|5|7|8|9)\\d{8}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
