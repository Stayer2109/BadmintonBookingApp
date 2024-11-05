package com.example.badmintonbookingapp.ui.court_owner.court_owner_court;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.dto.request.YardRequestDTO;
import com.example.badmintonbookingapp.dto.response.YardResponseDTO;
import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.utils.TokenManager;

import java.time.LocalTime;

public class AddYardActivity extends AppCompatActivity {

    private EditText yardName, yardAddress, provinceId, yardDescription;
    private TimePicker openTime, closeTime;
    private CourtManagementViewModel courtManagementViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_yard);

        // Initialize TokenManager and AuthRepository
        TokenManager tokenManager = TokenManager.getInstance(getApplicationContext());
        AuthRepository authRepository = new AuthRepository(tokenManager);

        // Retrieve hostId from TokenManager
        Integer hostId = tokenManager.getId();
        if (hostId == null) {
            Toast.makeText(this, "Failed to retrieve Host ID", Toast.LENGTH_SHORT).show();
            return;
        }

        // Set up ViewModel with a custom factory that passes hostId
        CourtManagementViewModelFactory factory = new CourtManagementViewModelFactory(tokenManager, authRepository, hostId);
        courtManagementViewModel = new ViewModelProvider(this, factory).get(CourtManagementViewModel.class);

        // Initialize UI components
        yardName = findViewById(R.id.yard_name);
        yardAddress = findViewById(R.id.yard_address);
        provinceId = findViewById(R.id.province_id);
        yardDescription = findViewById(R.id.yard_description);
        openTime = findViewById(R.id.open_time);
        closeTime = findViewById(R.id.close_time);
        Button btnAdd = findViewById(R.id.btn_add);

        // Set up button click listener for creating a yard
        btnAdd.setOnClickListener(v -> {
            String name = yardName.getText().toString().trim();
            String address = yardAddress.getText().toString().trim();
            String provinceIdStr = provinceId.getText().toString().trim();
            String description = yardDescription.getText().toString().trim();

            if (name.isEmpty() || address.isEmpty() || provinceIdStr.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int provinceIdValue;
            try {
                provinceIdValue = Integer.parseInt(provinceIdStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid province ID format", Toast.LENGTH_SHORT).show();
                return;
            }

            LocalTime openTimeValue = LocalTime.of(openTime.getHour(), openTime.getMinute());
            LocalTime closeTimeValue = LocalTime.of(closeTime.getHour(), closeTime.getMinute());
            int status = 1; // Assuming 1 for active status; adjust based on your use case

            // Create YardRequestDTO
            YardRequestDTO yardRequestDTO = new YardRequestDTO(
                    name, address, provinceIdValue, description, status, openTimeValue, closeTimeValue, hostId
            );

            // Call ViewModel to create the yard
            courtManagementViewModel.createYard(yardRequestDTO);
        });

        // Observe LiveData for created yard
        courtManagementViewModel.getCreatedYard().observe(this, new Observer<YardResponseDTO>() {
            @Override
            public void onChanged(YardResponseDTO yardResponseDTO) {
                if (yardResponseDTO != null) {
                    Toast.makeText(AddYardActivity.this, "Yard created successfully!", Toast.LENGTH_SHORT).show();
                    finish(); // Close activity after successful creation
                } else {
                    Toast.makeText(AddYardActivity.this, "Failed to create yard", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


