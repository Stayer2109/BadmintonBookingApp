package com.example.badmintonbookingapp.repository;

import android.content.Context;
import android.util.Log;

import com.example.badmintonbookingapp.client.APIClient;
import com.example.badmintonbookingapp.dto.ApiResponse;
import com.example.badmintonbookingapp.dto.request.BookingOrdersRequestDTO;
import com.example.badmintonbookingapp.dto.response.BookingOrdersResponseDTO;
import com.example.badmintonbookingapp.network.BookingOrdersService;
import com.example.badmintonbookingapp.utils.TokenManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BookingRepository {

    private static final String TAG = "BookingRepository";

    private final BookingOrdersService bookingOrdersService;
    private final TokenManager tokenManager;
    private final AuthRepository authRepository;

    // Constructor to receive TokenManager, AuthRepository, and Context
    public BookingRepository(TokenManager tokenManager, AuthRepository authRepository) {
        this.tokenManager = tokenManager;
        this.authRepository = authRepository;

        Retrofit retrofit = APIClient.getClient(tokenManager, authRepository);
        bookingOrdersService = retrofit.create(BookingOrdersService.class);
    }

    // Get all booking orders by user ID
    public void getAllBookingOrdersByUserId(int userId, Callback<ApiResponse<List<BookingOrdersResponseDTO>>> callback) {
        // Change the API call type to match the expected response type
        Call<ApiResponse<List<BookingOrdersResponseDTO>>> call = bookingOrdersService.getAllBookingOrdersByUserId(userId);

        call.enqueue(new Callback<ApiResponse<List<BookingOrdersResponseDTO>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<BookingOrdersResponseDTO>>> call, Response<ApiResponse<List<BookingOrdersResponseDTO>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, response);
                } else {
                    Log.e(TAG, "Failed to fetch booking orders. Error code: " + response);
                    callback.onResponse(call, Response.error(response.code(), response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<BookingOrdersResponseDTO>>> call, Throwable t) {
                Log.e(TAG, "Error fetching booking orders", t);
                callback.onFailure(call, t);
            }
        });
    }

    // Create new booking orders
    public void createBookingOrders(BookingOrdersRequestDTO bookingOrdersRequestDTO, Callback<ApiResponse<BookingOrdersResponseDTO>> callback) {
        if (bookingOrdersRequestDTO == null) {
            Log.e(TAG, "BookingOrdersRequestDTO is null");
            callback.onFailure(null, new Throwable("Booking request cannot be null"));
            return;
        }

        Call<ApiResponse<BookingOrdersResponseDTO>> call = bookingOrdersService.createBookingOrder(bookingOrdersRequestDTO);
        call.enqueue(new Callback<ApiResponse<BookingOrdersResponseDTO>>() {
            @Override
            public void onResponse(Call<ApiResponse<BookingOrdersResponseDTO>> call, Response<ApiResponse<BookingOrdersResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, response);
                    Log.d(TAG, "Booking created successfully: " + response.body().getData());
                } else {
                    Log.e(TAG, "Failed to create booking order. Error code: " + response.code() + ", Message: " + response.message());
                    callback.onResponse(call, Response.error(response.code(), response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<BookingOrdersResponseDTO>> call, Throwable t) {
                Log.e(TAG, "Error creating booking orders", t);
                callback.onFailure(call, t);
            }
        });
    }
}
