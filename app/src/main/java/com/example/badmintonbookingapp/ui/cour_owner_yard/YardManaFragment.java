package com.example.badmintonbookingapp.ui.cour_owner_yard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.ui.cour_owner_staff.StaffManaFragment;
import com.example.badmintonbookingapp.ui.cour_owner_staff.StaffManaViewModel;


public class YardManaFragment extends Fragment {

    private YardManaViewModel mViewModel;

    public static YardManaFragment newInstance() {
        return new YardManaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_yard_mana, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(YardManaViewModel.class);
        // TODO: Use the ViewModel
    }
}
