package com.example.badmintonbookingapp.ui.court_owner.court_owner_staff;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.badmintonbookingapp.R;

public class StaffManagementFragment extends Fragment {

    private StaffManagementViewModel mViewModel;

    public static StaffManagementFragment newInstance() {
        return new StaffManagementFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_staff_management, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(StaffManagementViewModel.class);
        // TODO: Use the ViewModel
    }
}