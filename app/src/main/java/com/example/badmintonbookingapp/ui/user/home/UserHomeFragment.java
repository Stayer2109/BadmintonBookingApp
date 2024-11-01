package com.example.badmintonbookingapp.ui.user.home;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.adapter.YardAdapter;
import com.example.badmintonbookingapp.dto.response.YardResponseDTO;

import java.util.List;

public class UserHomeFragment extends Fragment {

    private UserHomeViewModel mViewModel;
    private YardAdapter yardAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory())
                .get(UserHomeViewModel.class);

        RecyclerView recyclerViewYards = getView().findViewById(R.id.recyclerViewYards);
        recyclerViewYards.setLayoutManager(new LinearLayoutManager(getContext()));

        yardAdapter = new YardAdapter(null);  // Empty list initially
        recyclerViewYards.setAdapter(yardAdapter);

        mViewModel.getYards().observe(getViewLifecycleOwner(), new Observer<List<YardResponseDTO>>() {
            @Override
            public void onChanged(List<YardResponseDTO> yardResponseDTOS) {
                yardAdapter.setYards(yardResponseDTOS);
                yardAdapter.notifyDataSetChanged();
            }
        });
    }
}