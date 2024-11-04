package com.example.badmintonbookingapp.ui.court_owner.court_owner_court;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.dto.response.YardResponseDTO;
import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.repository.YardRepository;
import com.example.badmintonbookingapp.utils.TokenManager;

import java.util.List;


public class CourtManagementFragment extends Fragment {

    private YardRepository yardRepository;
    private ListView yardListView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get instances of TokenManager and AuthRepository
        TokenManager tokenManager = TokenManager.getInstance(getContext());
        AuthRepository authRepository = new AuthRepository(tokenManager); // Adjust this if necessary

        // Initialize YardRepository
        yardRepository = new YardRepository(tokenManager, authRepository);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_court_management, container, false);
        yardListView = view.findViewById(R.id.yard_list_view);
        Button btnAddYard = view.findViewById(R.id.btn_add_yard);

        yardRepository.getYards().observe(getViewLifecycleOwner(), new Observer<List<YardResponseDTO>>() {
            @Override
            public void onChanged(List<YardResponseDTO> yards) {
                ArrayAdapter<YardResponseDTO> adapter = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_list_item_1, yards);
                yardListView.setAdapter(adapter);
            }
        });

        btnAddYard.setOnClickListener(v -> {
            AddYardActivity dialog = new AddYardActivity();
            dialog.show(getChildFragmentManager(), "AddYardDialog");
        });

        return view;
    }

}

