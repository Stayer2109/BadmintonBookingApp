package com.example.badmintonbookingapp.ui.user.booking;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.adapter.SlotAdapter;
import com.example.badmintonbookingapp.dto.response.SlotResponseDTO;
import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.repository.BookingRepository;
import com.example.badmintonbookingapp.repository.SlotRepository;
import com.example.badmintonbookingapp.utils.TokenManager;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity {
    private BookingViewModel bookingViewModel;
    private SlotAdapter slotAdapter;
    private int yardId;
    private List<Integer> selectedSlotIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        yardId = getIntent().getIntExtra("yard_id", 0);
        String yardName = getIntent().getStringExtra("yardName");

        TextView yardNameTextView = findViewById(R.id.yardNameTextView);
        yardNameTextView.setText(yardName);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSlots);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the list of selected slot IDs
        selectedSlotIds = new ArrayList<>();

        // Update the SlotAdapter to handle multiple selections
        slotAdapter = new SlotAdapter(slot -> {
            int slotId = slot.getId();
            if (selectedSlotIds.contains(slotId)) {
                // Deselect the slot if it was already selected
                selectedSlotIds.remove(Integer.valueOf(slotId));
            } else {
                // Select the slot if it wasn't selected
                selectedSlotIds.add(slotId);
            }
        });
        recyclerView.setAdapter(slotAdapter);

        TokenManager tokenManager = TokenManager.getInstance(getApplicationContext());
        AuthRepository authRepository = new AuthRepository(tokenManager);
        BookingRepository bookingRepository = new BookingRepository(tokenManager, authRepository);
        bookingViewModel = new ViewModelProvider(this, new BookingViewModelFactory(tokenManager, authRepository, bookingRepository)).get(BookingViewModel.class);

        // Fetch slots from the API
        bookingViewModel.fetchSlotsByYardId(yardId);

        bookingViewModel.getSlots().observe(this, slots -> {
            if (slots != null) {
                slotAdapter.setSlots(slots); // Update the adapter with real data
            } else {
                Toast.makeText(this, "Failed to load slots.", Toast.LENGTH_SHORT).show();
            }
        });

        Button confirmBookingButton = findViewById(R.id.confirmBookingButton);
        confirmBookingButton.setOnClickListener(v -> {
            if (!selectedSlotIds.isEmpty()) {
                // Pass the list of selected slot IDs to the ViewModel
                bookingViewModel.createBooking(yardId, selectedSlotIds, getCurrentUserId());
            } else {
                Toast.makeText(this, "Please select at least one slot.", Toast.LENGTH_SHORT).show();
            }
        });

        bookingViewModel.getBookingCreationStatus().observe(this, created -> {
            if (created) {
                Toast.makeText(this, "Booking successful!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Booking failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getCurrentUserId() {
        return TokenManager.getInstance(getApplicationContext()).getId();
    }
}