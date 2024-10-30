package com.example.badmintonbookingapp.ui.staff.my_account;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.badmintonbookingapp.R;

public class StaffMyAccountFragment extends Fragment {

    private StaffMyAccountViewModel mViewModel;

    public static StaffMyAccountFragment newInstance() {
        return new StaffMyAccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_staff_my_account, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(StaffMyAccountViewModel.class);
        // TODO: Use the ViewModel
    }

}