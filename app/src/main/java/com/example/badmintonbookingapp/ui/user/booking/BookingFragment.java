package com.example.badmintonbookingapp.ui.user.booking;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.adapter.BookingAdapter;
import com.example.badmintonbookingapp.client.APIClient;
import com.example.badmintonbookingapp.dto.request.BookingOrdersRequestDTO;
import com.example.badmintonbookingapp.dto.response.BookingOrdersResponseDTO;
import com.example.badmintonbookingapp.network.AuthService;
import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.repository.BookingRepository;
import com.example.badmintonbookingapp.utils.TokenManager;
import com.google.android.material.button.MaterialButton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class BookingFragment extends Fragment {

    private BookingViewModel mViewModel;
    private RecyclerView recyclerView;
    private BookingAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_booking, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BookingAdapter();
        recyclerView.setAdapter(adapter);

        TokenManager tokenManager = new TokenManager(requireContext());
        AuthService authService = APIClient.getService(AuthService.class, tokenManager, null); // AuthRepository not needed for AuthService
        AuthRepository authRepository = new AuthRepository(authService, tokenManager);
        BookingRepository repository = new BookingRepository(requireContext(), tokenManager, authRepository);
        mViewModel = new ViewModelProvider(this, new BookingViewModelFactory(repository)).get(BookingViewModel.class);

        MaterialButton createBookingButton = view.findViewById(R.id.createBookingButton);
        EditText yardIdInput = view.findViewById(R.id.yardIdInput);
        EditText userIdInput = view.findViewById(R.id.userIdInput);
        EditText slotIdInput = view.findViewById(R.id.slotIdInput);


        createBookingButton.setOnClickListener(v -> {
            try {
                int yardId = Integer.parseInt(yardIdInput.getText().toString());
                int userId = Integer.parseInt(userIdInput.getText().toString());
                int slotId = Integer.parseInt(slotIdInput.getText().toString());


                List<BookingOrdersRequestDTO> bookingRequests = new ArrayList<>();
                bookingRequests.add(new BookingOrdersRequestDTO());
                bookingRequests.get(0).setYardId(yardId);
                bookingRequests.get(0).setUserId(userId);
                bookingRequests.get(0).setSlotId(slotId);


                mViewModel.createBooking(bookingRequests);

            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Invalid input. Please enter numbers.", Toast.LENGTH_SHORT).show();
            }
        });

        mViewModel.getBookingOrders().observe(getViewLifecycleOwner(), bookingOrders -> {
            if (bookingOrders != null) {
                adapter.submitList(bookingOrders);
                for (BookingOrdersResponseDTO booking : bookingOrders) {
                    if (booking.getBookingAt() != null) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        String formattedDateTime = booking.getBookingAt().format(formatter);
                        System.out.println("Booking Time: " + formattedDateTime); // Or log it
                    }

                }
            } else {
                Toast.makeText(getContext(), "Failed to load bookings", Toast.LENGTH_SHORT).show();
            }
        });


        mViewModel.getBookingCreationStatus().observe(getViewLifecycleOwner(), success -> {
            String message = success ? "Booking created successfully" : "Failed to create booking";
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            if (success) {
                // Refresh the booking list after successful creation
                mViewModel.fetchBookingOrdersByUserId(Integer.parseInt(userIdInput.getText().toString()));
            }
        });

        mViewModel.fetchBookingOrdersByUserId(123);  // Fetch initial data.  Replace 123 with the actual userID later


    }
}
