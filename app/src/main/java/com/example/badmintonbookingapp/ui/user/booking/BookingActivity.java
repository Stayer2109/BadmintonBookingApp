package com.example.badmintonbookingapp.ui.user.booking;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.badmintonbookingapp.MainActivity;
import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.adapter.SlotAdapter;
import com.example.badmintonbookingapp.dto.response.SlotResponseDTO;
import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.repository.BookingRepository;
import com.example.badmintonbookingapp.repository.SlotRepository;
import com.example.badmintonbookingapp.ui.user.order.OrderFragment;
import com.example.badmintonbookingapp.ui.user.order.SharedBookingViewModel;
import com.example.badmintonbookingapp.utils.TokenManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class BookingActivity extends AppCompatActivity {
    private BookingViewModel bookingViewModel;
    private SlotAdapter slotAdapter;
    private int yardId;
    private int selectedSlotId;
    private SharedBookingViewModel sharedViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


        yardId = getIntent().getIntExtra("yardId", 0);
        String yardName = getIntent().getStringExtra("yardName");


        TextView yardNameTextView = findViewById(R.id.yardNameTextView);
        yardNameTextView.setText(yardName);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSlots);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        slotAdapter = new SlotAdapter(slot -> {
            selectedSlotId = slot.getId();
            Toast.makeText(this, "Selected slot: " + selectedSlotId, Toast.LENGTH_SHORT).show();
        });
        recyclerView.setAdapter(slotAdapter);

        TokenManager tokenManager = TokenManager.getInstance(getApplicationContext());
        AuthRepository authRepository = new AuthRepository(tokenManager);
        SlotRepository slotRepository = new SlotRepository(tokenManager, authRepository);
        BookingRepository bookingRepository = new BookingRepository(this, tokenManager, authRepository);
        bookingViewModel = new ViewModelProvider(this, new BookingViewModelFactory(bookingRepository, slotRepository)).get(BookingViewModel.class);

        // Fetch slots from the API
        bookingViewModel.getSlotsByYardId(yardId); // Call this to fetch real data


        bookingViewModel.getSlots().observe(this, slots -> {
            if (slots != null) {
                slotAdapter.setSlots(slots); // Update the adapter with real data
            } else {
                Toast.makeText(this, "Failed to load slots.", Toast.LENGTH_SHORT).show(); // Handle error
            }

        });

        Button confirmBookingButton = findViewById(R.id.confirmBookingButton);
        confirmBookingButton.setOnClickListener(v -> {
            if (selectedSlotId != 0) {
                bookingViewModel.createBooking(yardId, selectedSlotId, getCurrentUserId());
            } else {
                Toast.makeText(this, "Please select a slot.", Toast.LENGTH_SHORT).show();
            }
        });


        bookingViewModel.getBookingCreationStatus().observe(this, created -> {
            if (created) {
                Toast.makeText(this, "Booking successful!", Toast.LENGTH_SHORT).show();


                sharedViewModel.setBookingConfirmed(true); // Notify OrderFragment



                finish();
            } else {
                Toast.makeText(this, "Booking failed.", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private int getCurrentUserId(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("user_data", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("user_id", 0);

    }

}