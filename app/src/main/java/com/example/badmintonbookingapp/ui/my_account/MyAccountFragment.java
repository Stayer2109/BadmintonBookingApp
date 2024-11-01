package com.example.badmintonbookingapp.ui.my_account;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.ui.auth.AuthViewModel;

import java.text.SimpleDateFormat;

public class MyAccountFragment extends Fragment {
    private AuthViewModel authViewModel;
    private CardView cardMyAccount;
    private TextView tvUsername, tvEmail, tvName, tvPhone, tvGender, tvDob;

    public static MyAccountFragment newInstance() {
        return new MyAccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_account, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        Mapping();
    }

    private void Mapping () {
        cardMyAccount = getView().findViewById(R.id.cvMyAccount);
        tvUsername = getView().findViewById(R.id.tvUsername);
        tvEmail = getView().findViewById(R.id.tvEmail);
        tvName = getView().findViewById(R.id.tvName);
        tvGender = getView().findViewById(R.id.tvGender);
        tvDob = getView().findViewById(R.id.tvDob);
    }

    private void GetAccount() {
        authViewModel.getUserInfo().observe(getViewLifecycleOwner(), userResponseDTO -> {
            tvUsername.setText(userResponseDTO.getUsername());
            tvEmail.setText(userResponseDTO.getEmail());
            tvName.setText(userResponseDTO.getFirstName() + " " + userResponseDTO.getLastName());

            // if gender is true then display male, else display female
            if (userResponseDTO.getGender()) { tvGender.setText("male"); }
            else { tvGender.setText("female"); }

            // Convert date to string, format: dd/MM/yyyy
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dob = formatter.format(userResponseDTO.getDob());
            tvDob.setText(dob);
        });
    }
}