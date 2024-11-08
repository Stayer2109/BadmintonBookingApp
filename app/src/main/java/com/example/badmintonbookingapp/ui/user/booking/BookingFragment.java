package com.example.badmintonbookingapp.ui.user.booking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.example.badmintonbookingapp.utils.TokenManager;

public class BookingFragment extends Fragment {
    private BookingViewModel bookingViewModel;
    private BookingAdapter bookingAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_booking, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.bookingsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bookingAdapter = new BookingAdapter();
        recyclerView.setAdapter(bookingAdapter);

        TokenManager tokenManager = TokenManager.getInstance(requireContext());
        AuthService authService = APIClient.getService(AuthService.class, tokenManager, null);
        AuthRepository authRepository = new AuthRepository(authService, tokenManager);
        BookingRepository bookingRepository = new BookingRepository(tokenManager, authRepository);
        SlotRepository slotRepository = new SlotRepository(tokenManager, authRepository); // Create SlotRepository

//        bookingViewModel = new ViewModelProvider(this, new BookingViewModelFactory(bookingRepository, slotRepository)).get(BookingViewModel.class);
//
//        bookingViewModel.getBookingOrders().observe(getViewLifecycleOwner(), bookingOrders -> {
//            if (bookingOrders != null) {
//                bookingAdapter.submitList(bookingOrders);
//            }
//        });
//        bookingViewModel.fetchBookingOrdersByUserId(123); // Replace with actual user ID
    }
}