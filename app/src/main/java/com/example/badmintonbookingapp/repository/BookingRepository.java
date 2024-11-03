package com.example.badmintonbookingapp.repository;
import android.content.Context;

import com.example.badmintonbookingapp.client.APIClient;
import com.example.badmintonbookingapp.dto.request.BookingOrdersRequestDTO;
import com.example.badmintonbookingapp.dto.response.BookingOrdersResponseDTO;
import com.example.badmintonbookingapp.network.BookingOrdersService;
import com.example.badmintonbookingapp.utils.TokenManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class BookingRepository {

    private BookingOrdersService bookingOrdersService;
    private final TokenManager tokenManager;  // Add TokenManager
    private final AuthRepository authRepository; // Add AuthRepository
    private final Context context;  // Add Context

    // Modified constructor to receive TokenManager, AuthRepository, and Context
    public BookingRepository(Context context, TokenManager tokenManager, AuthRepository authRepository) {
        this.tokenManager = tokenManager;  // Initialize TokenManager
        this.authRepository = authRepository; // Initialize AuthRepository
        this.context = context; // Initialize context

        Retrofit retrofit = APIClient.getClient(tokenManager, authRepository); // Use your APIClient
        bookingOrdersService = retrofit.create(BookingOrdersService.class);
    }


    public void getAllBookingOrdersByUserId(int userId, Callback<List<BookingOrdersResponseDTO>> callback) {
        Call<List<BookingOrdersResponseDTO>> call = bookingOrdersService.getAllBookingOrdersByUserId(userId);
        call.enqueue(callback);
    }




    public void createBookingOrders(List<BookingOrdersRequestDTO> bookingOrdersRequestDTOs, Callback<List<BookingOrdersResponseDTO>> callback) {

        Call<List<BookingOrdersResponseDTO>> call = bookingOrdersService.createBookingOrders(bookingOrdersRequestDTOs);
        call.enqueue(callback);
    }




    public void updateBookingOrderStatus(int bookingId, Callback<String> callback) {
        Call<String> call = bookingOrdersService.updateBookingOrderStatus(bookingId);
        call.enqueue(callback);

    }
}
