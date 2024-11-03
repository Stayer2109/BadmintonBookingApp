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

import java.util.ArrayList;
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

        TokenManager tokenManager = TokenManager.getInstance(requireContext());
        AuthRepository authRepository = new AuthRepository(tokenManager);

        UserHomeViewModelFactory factory = new UserHomeViewModelFactory(tokenManager, authRepository);
        homeViewModel = new ViewModelProvider(this, factory).get(UserHomeViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewYards);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        yardAdapter = new YardAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(yardAdapter);

        homeViewModel.getAllYards().observe(getViewLifecycleOwner(), yards -> {
            yardAdapter.setYards(yards);
        });

        // Infinite scroll listener
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && layoutManager.findLastCompletelyVisibleItemPosition() == yardAdapter.getItemCount() - 1) {
                    homeViewModel.loadNextPage(); // Load next page when reaching the end
                }
            }
        });
    }
}