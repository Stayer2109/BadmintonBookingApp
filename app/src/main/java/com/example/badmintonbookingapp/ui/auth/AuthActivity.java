package com.example.badmintonbookingapp.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.badmintonbookingapp.MainActivity;
import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.dto.request.SignUpRequest;
import com.example.badmintonbookingapp.dto.response.JwtAuthenticationResponse;
import com.example.badmintonbookingapp.utils.TokenManager;

import java.util.concurrent.atomic.AtomicInteger;

public class AuthActivity extends AppCompatActivity {
    private TokenManager tokenManager;
    private AuthViewModel authViewModel;
    private CardView cardLogin, cardRegister;
    private TextView tvLogin, tvRegister;
    private Button btnLogin, btnRegister;
    private EditText etUsername, etEmail, etPassword, etFirstName, etLastName;
    private RadioButton rbUser, rbCourtOwner;
    private AtomicInteger role;
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
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etUsername = findViewById(R.id.etRegisterUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etRegisterPassword);
        btnRegister = findViewById(R.id.btnRegister);
        rbUser = findViewById(R.id.rbUser);
        rbCourtOwner = findViewById(R.id.rbCourtOwner);
        tvLogin.setOnClickListener(v -> LoginMapping());
        btnRegister.setOnClickListener(v -> Register());
        rbUser.setOnClickListener(v -> {
            rbUser.setChecked(true);
            rbCourtOwner.setChecked(false);
        });

        rbCourtOwner.setOnClickListener(v -> {
            rbUser.setChecked(false);
            rbCourtOwner.setChecked(true);
        });
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
        setupSignInObservers();
    }

    // Set up LiveData observers for sign-in response and error handling
    private void setupSignInObservers() {
        authViewModel.getSignInResponse().observe(this, new Observer<JwtAuthenticationResponse>() {
            @Override
            public void onChanged(JwtAuthenticationResponse response) {
                if (response != null) {
                    checkUser();
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
            Toast.makeText(this, "Sign-in Successful!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    // Method to handle register button click
    private void Register() {
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        role = new AtomicInteger(0);
        if (rbUser.isChecked()) {
            role.set(2);
        } else if (rbCourtOwner.isChecked()) {
            role.set(4);
        } else {
            Toast.makeText(this, "Please select a role", Toast.LENGTH_SHORT).show();
            return;
        }

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Call ViewModel to initiate register process
        SignUpRequest signUpRequest = new SignUpRequest(firstName, lastName, email, password, username, role.get());
        authViewModel.register(signUpRequest);
        setupSignInObservers();
    }
}