package com.example.badmintonbookingapp.ui.manage_checkin;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.badmintonbookingapp.R;

public class ManageCheckinFragment extends Fragment {

    private ManageCheckinViewModel mViewModel;

    public static ManageCheckinFragment newInstance() {
        return new ManageCheckinFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manage_checkin, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ManageCheckinViewModel.class);
        // TODO: Use the ViewModel
    }

}