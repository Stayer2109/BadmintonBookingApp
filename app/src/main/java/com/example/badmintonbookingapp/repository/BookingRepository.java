package com.example.badmintonbookingapp.repository;

import android.content.Context;
import android.util.Log;

import com.example.badmintonbookingapp.client.APIClient;
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
    private final Context context;

    // Constructor to receive TokenManager, AuthRepository, and Context
    public BookingRepository(Context context, TokenManager tokenManager, AuthRepository authRepository) {
        this.tokenManager = tokenManager;
        this.authRepository = authRepository;
        this.context = context;

        Retrofit retrofit = APIClient.getClient(tokenManager, authRepository);
        bookingOrdersService = retrofit.create(BookingOrdersService.class);
    }

    // Get all booking orders by user ID
    public void getAllBookingOrdersByUserId(int userId, Callback<List<BookingOrdersResponseDTO>> callback) {
        Call<List<BookingOrdersResponseDTO>> call = bookingOrdersService.getAllBookingOrdersByUserId(userId);
        call.enqueue(new Callback<List<BookingOrdersResponseDTO>>() {
            @Override
            public void onResponse(Call<List<BookingOrdersResponseDTO>> call, Response<List<BookingOrdersResponseDTO>> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call, response);
                } else {
                    Log.e(TAG, "Failed to fetch booking orders. Error code: " + response.code());
                    callback.onResponse(call, Response.error(response.code(), response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<List<BookingOrdersResponseDTO>> call, Throwable t) {
                Log.e(TAG, "Error fetching booking orders", t);
                callback.onFailure(call, t);
            }
        });
    }

    // Create new booking orders
    public void createBookingOrders(List<BookingOrdersRequestDTO> bookingOrdersRequestDTOs, Callback<List<BookingOrdersResponseDTO>> callback) {
        Call<List<BookingOrdersResponseDTO>> call = bookingOrdersService.createBookingOrders(bookingOrdersRequestDTOs);
        call.enqueue(new Callback<List<BookingOrdersResponseDTO>>() {
            @Override
            public void onResponse(Call<List<BookingOrdersResponseDTO>> call, Response<List<BookingOrdersResponseDTO>> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call, response);
                } else {
                    Log.e(TAG, "Failed to create booking order. Error code: " + response.code() + ", Message: " + response.message());
                    callback.onResponse(call, Response.error(response.code(), response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<List<BookingOrdersResponseDTO>> call, Throwable t) {
                Log.e(TAG, "Error creating booking orders", t);
                callback.onFailure(call, t);
            }
        });
    }

    // Update booking order status
    public void updateBookingOrderStatus(int bookingId, Callback<String> callback) {
        Call<String> call = bookingOrdersService.updateBookingOrderStatus(bookingId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call, response);
                } else {
                    Log.e(TAG, "Failed to update booking order status. Error code: " + response.code() + ", Message: " + response.message());
                    callback.onResponse(call, Response.error(response.code(), response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "Error updating booking order status", t);
                callback.onFailure(call, t);
            }
        });
    }
}
