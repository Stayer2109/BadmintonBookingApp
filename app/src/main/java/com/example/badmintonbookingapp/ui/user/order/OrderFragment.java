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
import com.example.badmintonbookingapp.dto.response.BookingOrdersResponseDTO;
import com.example.badmintonbookingapp.network.ApiCallback;
import com.example.badmintonbookingapp.network.AuthService;
import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.repository.BookingRepository;
import com.example.badmintonbookingapp.repository.SlotRepository;
import com.example.badmintonbookingapp.ui.user.booking.BookingViewModel;
import com.example.badmintonbookingapp.ui.user.booking.BookingViewModelFactory;
import com.example.badmintonbookingapp.utils.TokenManager;

import java.util.List;


public class OrderFragment extends Fragment {
    private BookingViewModel bookingViewModel;
    private BookingAdapter bookingAdapter;


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

        TokenManager tokenManager = TokenManager.getInstance(getContext());
        AuthRepository authRepository = new AuthRepository(tokenManager);
        BookingRepository bookingRepository = new BookingRepository(tokenManager, authRepository);

        // Truyền thêm BookingRepository vào BookingViewModelFactory
        BookingViewModelFactory factory = new BookingViewModelFactory(tokenManager, authRepository, bookingRepository);
        bookingViewModel = new ViewModelProvider(this, factory).get(BookingViewModel.class);

        Toast.makeText(getContext(), "" + getUserId(), Toast.LENGTH_SHORT).show();

        bookingViewModel.fetchBooksByUserId(getUserId(), new ApiCallback<List<BookingOrdersResponseDTO>>() {
            @Override
            public void onSuccess(List<BookingOrdersResponseDTO> result) {
                bookingAdapter.setBookings(result);
                Toast.makeText(getContext(), "Fetched booking orders successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable error) {
                Toast.makeText(getContext(), "Failed to fetch booking orders: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public int getUserId() {
        return TokenManager.getInstance(getContext()).getId();
    }
}