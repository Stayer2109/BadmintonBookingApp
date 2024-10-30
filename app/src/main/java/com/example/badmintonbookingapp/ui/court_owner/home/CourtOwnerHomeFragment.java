package com.example.badmintonbookingapp.ui.court_owner.home;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.badmintonbookingapp.R;

public class CourtOwnerHomeFragment extends Fragment {

    private CourtOwnerHomeViewModel mViewModel;

    public static CourtOwnerHomeFragment newInstance() {
        return new CourtOwnerHomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_court_owner_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CourtOwnerHomeViewModel.class);
        // TODO: Use the ViewModel
    }

}