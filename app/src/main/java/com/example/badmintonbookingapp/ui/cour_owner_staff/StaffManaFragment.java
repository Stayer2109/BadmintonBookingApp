package com.example.badmintonbookingapp.ui.cour_owner_staff;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.ui.booking.BookingFragment;
import com.example.badmintonbookingapp.ui.booking.BookingViewModel;

public class StaffManaFragment extends Fragment {

    private StaffManaViewModel mViewModel;

    public static StaffManaFragment newInstance() {
        return new StaffManaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_staff_mana, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(StaffManaViewModel.class);
        // TODO: Use the ViewModel
    }
}