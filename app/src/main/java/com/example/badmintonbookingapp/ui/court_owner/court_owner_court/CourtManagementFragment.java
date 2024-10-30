package com.example.badmintonbookingapp.ui.court_owner.court_owner_court;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.badmintonbookingapp.R;


public class CourtManagementFragment extends Fragment {

    private CourtManagementViewModel mViewModel;

    public static CourtManagementFragment newInstance() {
        return new CourtManagementFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_court_management, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CourtManagementViewModel.class);
        // TODO: Use the ViewModel
    }
}
