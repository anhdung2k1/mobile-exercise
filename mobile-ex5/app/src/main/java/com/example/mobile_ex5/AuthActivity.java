package com.example.mobile_ex5;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        EditText email = findViewById(R.id.emailInput);
        EditText password = findViewById(R.id.passwordInput);
        Button loginBtn = findViewById(R.id.btnLogin);
        Button signupBtn = findViewById(R.id.btnSignup);

        loginBtn.setOnClickListener(v -> {
            String emailText = email.getText().toString().trim();
            String passText = password.getText().toString().trim();

            if (emailText.isEmpty() || passText.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Login Successful (not implemented)", Toast.LENGTH_SHORT).show();
            }
        });

        signupBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Sign up clicked!", Toast.LENGTH_SHORT).show();
        });
    }
}
