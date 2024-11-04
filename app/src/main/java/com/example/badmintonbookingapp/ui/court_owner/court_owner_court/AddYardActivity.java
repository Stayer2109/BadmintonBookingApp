package com.example.badmintonbookingapp.ui.court_owner.court_owner_court;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.dto.request.YardRequestDTO;
import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.repository.YardRepository;
import com.example.badmintonbookingapp.utils.TokenManager;

import java.time.LocalTime;

public class AddYardActivity extends DialogFragment {

    private YardRepository yardRepository;

    private Integer hostId; // Assuming this is passed to the dialog

    // Optionally, provide a way to set hostId
    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get instances of TokenManager and AuthRepository
        TokenManager tokenManager = TokenManager.getInstance(getContext());
        AuthRepository authRepository = new AuthRepository(tokenManager); // Adjust this if necessary

        // Initialize YardRepository
        yardRepository = new YardRepository(tokenManager, authRepository);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.add_yard);

        EditText yardName = dialog.findViewById(R.id.yard_name);
        EditText yardAddress = dialog.findViewById(R.id.yard_address);
        EditText provinceId = dialog.findViewById(R.id.province_id);
        EditText yardDescription = dialog.findViewById(R.id.yard_description);
        TimePicker openTimePicker = dialog.findViewById(R.id.open_time);
        TimePicker closeTimePicker = dialog.findViewById(R.id.close_time);
        Button btnAdd = dialog.findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(v -> {
            String name = yardName.getText().toString();
            String address = yardAddress.getText().toString();
            String provinceIdStr = provinceId.getText().toString();
            String description = yardDescription.getText().toString();

            Log.d("AddYardActivity", "Name: " + name);
            Log.d("AddYardActivity", "Address: " + address);
            Log.d("AddYardActivity", "Province ID: " + provinceIdStr);
            Log.d("AddYardActivity", "Description: " + description);
            Log.d("AddYardActivity", "Open Time: " + openTimePicker.toString());

            if (name.isEmpty() || address.isEmpty() || provinceIdStr.isEmpty() || description.isEmpty()) {
                // Show error message
                Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            Integer province = Integer.valueOf(provinceIdStr);
            LocalTime openTime = LocalTime.of(openTimePicker.getHour(), openTimePicker.getMinute());
            LocalTime closeTime = LocalTime.of(closeTimePicker.getHour(), closeTimePicker.getMinute());

            YardRequestDTO newYardRequest = new YardRequestDTO(
                    name,
                    address,
                    province,
                    description,
                    1, // Assuming the yard is active by default
                    openTime,
                    closeTime,
                    hostId // Include the hostId in the request
            );

            yardRepository.createYard(newYardRequest, dialog);
        });

        return dialog;
    }
}


