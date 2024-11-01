package com.example.badmintonbookingapp.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.badmintonbookingapp.MainActivity;
import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.dto.response.JwtAuthenticationResponse;
import com.example.badmintonbookingapp.utils.TokenManager;

public class AuthActivity extends AppCompatActivity {
    private TokenManager tokenManager;
    private AuthViewModel authViewModel;
    private CardView cardLogin, cardRegister;
    private TextView tvLogin, tvRegister;
    private Button btnLogin, btnRegister;
    private EditText etUsername, etEmail, etPassword, etName, etPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        tokenManager = TokenManager.getInstance(this);
        checkUser();

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        Mapping();
    }

    private void Mapping () {
        cardLogin = findViewById(R.id.cvLogin);
        cardRegister = findViewById(R.id.cvRegister);
        LoginMapping();
        // disable action bar
        getSupportActionBar().hide();
    }

    // Mapping login and register view
    private void LoginMapping() {
        cardLogin.setVisibility(View.VISIBLE);
        cardRegister.setVisibility(View.GONE);
        tvRegister = findViewById(R.id.tvRegister);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister.setOnClickListener(view -> RegisterMapping());
        btnLogin.setOnClickListener(v -> SignIn());
    }

    private void RegisterMapping() {
        cardLogin.setVisibility(View.GONE);
        cardRegister.setVisibility(View.VISIBLE);
        tvLogin = findViewById(R.id.tvLogin);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etUsername);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin.setOnClickListener(v -> LoginMapping());
        //btnRegister.setOnClickListener(v -> Register());
    }

    // Method to handle sign-in button click
    private void SignIn() {
        // admin: 123
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Call ViewModel to initiate sign-in process
        authViewModel.signIn(username, password);

        // Save token to shared preferences
        tokenManager = TokenManager.getInstance(this);
        authViewModel.getSignInResponse().observe(this, response -> {
            if (response != null) {
                tokenManager.saveTokens(response.getToken(), response.getRefreshToken());
                Toast.makeText(AuthActivity.this, "Signed in successfully!", Toast.LENGTH_SHORT).show();
                authViewModel.fetchUserInfo();
                checkUser();
            }
        });

        authViewModel.getUserInfo().observe(this, user -> {
            if (user != null) {
                // Use user data as needed
                Toast.makeText(AuthActivity.this, "User: " + user.getUsername(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    // Set up LiveData observers for sign-in response and error handling
    private void setupObservers() {
        authViewModel.getSignInResponse().observe(this, new Observer<JwtAuthenticationResponse>() {
            @Override
            public void onChanged(JwtAuthenticationResponse response) {
                if (response != null) {
                    // Handle successful sign-in (e.g., navigate to main activity or save token)
                    Toast.makeText(AuthActivity.this, "Sign-in Successful! Token: "
                            + response.getToken(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        authViewModel.getError().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable error) {
                Toast.makeText(AuthActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Check if user is already signed in and navigate to main activity
    private void checkUser() {
        if (tokenManager.getAccessToken() != null) {
            // Navigate to main activity
            Toast.makeText(this, "Already signed in!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}