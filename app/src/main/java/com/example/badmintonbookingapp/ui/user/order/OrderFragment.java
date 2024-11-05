package com.example.badmintonbookingapp.ui.user.order;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.badmintonbookingapp.network.AuthService;
import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.repository.BookingRepository;
import com.example.badmintonbookingapp.repository.SlotRepository;
import com.example.badmintonbookingapp.ui.user.booking.BookingViewModel;
import com.example.badmintonbookingapp.ui.user.booking.BookingViewModelFactory;
import com.example.badmintonbookingapp.utils.TokenManager;


public class OrderFragment extends Fragment {
    private BookingViewModel bookingViewModel;
    private BookingAdapter bookingAdapter;
    private SharedBookingViewModel sharedViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        RecyclerView recyclerView = view.findViewById(R.id.orderRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        bookingAdapter = new BookingAdapter();
        recyclerView.setAdapter(bookingAdapter);


        TokenManager tokenManager = TokenManager.getInstance(requireContext()); //If using it
        AuthService authService = APIClient.getService(AuthService.class, tokenManager, null); //If using it
        AuthRepository authRepository = new AuthRepository(authService, tokenManager); // If using it
        BookingRepository bookingRepository = new BookingRepository(requireContext(), tokenManager, authRepository);
        SlotRepository slotRepository = new SlotRepository(tokenManager, authRepository);


        bookingViewModel = new ViewModelProvider(this, new BookingViewModelFactory(bookingRepository, slotRepository)).get(BookingViewModel.class);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedBookingViewModel.class);




        bookingViewModel.getBookingOrders().observe(getViewLifecycleOwner(), bookingOrders -> {
            if (bookingOrders != null) {
                bookingAdapter.submitList(bookingOrders);
            } else {
                Toast.makeText(requireContext(), "Failed to load bookings", Toast.LENGTH_SHORT).show();
            }
        });

        sharedViewModel.getBookingConfirmed().observe(getViewLifecycleOwner(), confirmed -> {
            if (confirmed) {
                int userId = getCurrentUserId(); // Get the actual user ID
                bookingViewModel.fetchBookingOrdersByUserId(userId);
                sharedViewModel.setBookingConfirmed(false); // Reset the flag
            }
        });



        int userId = getCurrentUserId();
        bookingViewModel.fetchBookingOrdersByUserId(userId);


    }


    public void refreshBookings(int userId) {
        bookingViewModel.fetchBookingOrdersByUserId(userId);

    }


    private int getCurrentUserId(){
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("user_data", Context.MODE_PRIVATE);

        return sharedPreferences.getInt("user_id", 0); // 0 is a placeholder default value, change if needed
    }



}