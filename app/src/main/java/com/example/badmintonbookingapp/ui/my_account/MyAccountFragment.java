package com.example.badmintonbookingapp.ui.my_account;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.ui.auth.AuthActivity;
import com.example.badmintonbookingapp.ui.auth.AuthViewModel;

import java.text.SimpleDateFormat;

public class MyAccountFragment extends Fragment {
    private AuthViewModel authViewModel;
    private CardView cardMyAccount;
    private TextView tvUsername, tvEmail, tvName, tvGender, tvDob;
    private Button btnLogout;

    public static MyAccountFragment newInstance() {
        return new MyAccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize ViewModel and UI elements
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        Mapping(view);
        GetAccount();
    }

    private void Mapping(View view) {
        cardMyAccount = view.findViewById(R.id.cvMyAccount);
        tvUsername = view.findViewById(R.id.tvUsername);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvName = view.findViewById(R.id.tvName);
        tvGender = view.findViewById(R.id.tvGender);
        tvDob = view.findViewById(R.id.tvDob);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> Logout());
    }

    @SuppressLint("SetTextI18n")
    private void GetAccount() {
        authViewModel.getAccount();
        authViewModel.getAccountInfo().observe(getViewLifecycleOwner(), userResponseDTO -> {
            if (userResponseDTO != null) {
                tvUsername.setText(userResponseDTO.getUsername());
                tvEmail.setText(userResponseDTO.getEmail());
                tvName.setText(userResponseDTO.getFirstName() + " " + userResponseDTO.getLastName());

                // Set gender
                tvGender.setText(userResponseDTO.getGender() ? "male" : "female");

                // Format and display date of birth
                @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dob = formatter.format(userResponseDTO.getDob());
                tvDob.setText(dob);
            }

        });
    }

    private void Logout() {
        authViewModel.logout();
        startActivity(new Intent(getActivity(), AuthActivity.class));
        getActivity().finish();
    }
}
