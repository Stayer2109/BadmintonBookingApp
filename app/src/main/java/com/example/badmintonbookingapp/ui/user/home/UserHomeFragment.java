package com.example.badmintonbookingapp.ui.user.home;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
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
import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.utils.TokenManager;

import java.util.List;

public class UserHomeFragment extends Fragment {

    private UserHomeViewModel homeViewModel;
    private YardAdapter yardAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtain instances of TokenManager and AuthRepository
        TokenManager tokenManager = TokenManager.getInstance(requireContext());
        AuthRepository authRepository = new AuthRepository(tokenManager); // Assuming constructor accepts TokenManager

        UserHomeViewModelFactory factory = new UserHomeViewModelFactory(tokenManager, authRepository);
        homeViewModel = new ViewModelProvider(this, factory).get(UserHomeViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewYards);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        yardAdapter = new YardAdapter(getContext(), null);
        recyclerView.setAdapter(yardAdapter);

        homeViewModel.getAllYards().observe(getViewLifecycleOwner(), new Observer<List<YardResponseDTO>>() {
            @Override
            public void onChanged(List<YardResponseDTO> yards) {
                yardAdapter.setYards(yards);  // Assuming you have a `setYards` method in YardAdapter
                yardAdapter.notifyDataSetChanged();
            }
        });
    }
}