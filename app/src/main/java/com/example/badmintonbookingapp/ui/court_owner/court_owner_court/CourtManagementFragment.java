package com.example.badmintonbookingapp.ui.court_owner.court_owner_court;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.adapter.OwnerYardAdapter;
import com.example.badmintonbookingapp.dto.request.YardRequestDTO;
import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.repository.YardRepository;
import com.example.badmintonbookingapp.utils.TokenManager;

import java.time.LocalTime;

public class CourtManagementFragment extends Fragment {

    private CourtManagementViewModel viewModel;
    private OwnerYardAdapter yardAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_court_management, container, false);
        TokenManager tokenManager = TokenManager.getInstance(requireContext());
        AuthRepository authRepository = new AuthRepository(tokenManager);

        int hostId = tokenManager.getId(); // Retrieve logged-in user ID as hostId

        // Initialize ViewModel with hostId
        viewModel = new ViewModelProvider(this, new CourtManagementViewModelFactory(tokenManager, authRepository, hostId)).get(CourtManagementViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_yards);
        Button btnAddYard = view.findViewById(R.id.btn_add_yard);

        // Set up RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        yardAdapter = new OwnerYardAdapter(getContext(), null);
        recyclerView.setAdapter(yardAdapter);

        // Observe the list of yards
        viewModel.getAllYards().observe(getViewLifecycleOwner(), yards -> {
            yardAdapter.setYards(yards);
        });

        btnAddYard = view.findViewById(R.id.btn_add_yard); // Assuming you have a button to add a yard
        btnAddYard.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddYardActivity.class);
            startActivity(intent);
        });


// Observe the created yard response
        viewModel.getCreatedYard().observe(getViewLifecycleOwner(), createdYard -> {
            if (createdYard != null) {
                Toast.makeText(getContext(), "Yard created successfully: " + createdYard.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


// get all yard
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_court_management, container, false);
//        TokenManager tokenManager = TokenManager.getInstance(requireContext());
//        AuthRepository authRepository = new AuthRepository(tokenManager);
//
//        // Initialize ViewModel
//        viewModel = new ViewModelProvider(this, new CourtManagementViewModelFactory(tokenManager, authRepository)).get(CourtManagementViewModel.class);
//
//        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_yards);
//        Button btnAddYard = view.findViewById(R.id.btn_add_yard);
//
//        // Set up RecyclerView
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        yardAdapter = new OwnerYardAdapter(getContext(), null);
//
//        Toast.makeText(getContext(), "Hello: " + tokenManager.getId(), Toast.LENGTH_SHORT).show();
//
//        recyclerView.setAdapter(yardAdapter);
//
//        viewModel.getAllYards().observe(getViewLifecycleOwner(), yards -> {
//            yardAdapter.setYards(yards);
//        });
//
//        // Infinite scroll listener
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (dy > 0 && layoutManager.findLastCompletelyVisibleItemPosition() == yardAdapter.getItemCount() - 1) {
//                    viewModel.loadNextPage(); // Load next page when reaching the end
//                }
//            }
//        });
//
//        // Handle button click for adding a yard
//        btnAddYard.setOnClickListener(v -> {
//            // Implement adding a yard logic here
//        });
//
//        return view;
//    }
}




