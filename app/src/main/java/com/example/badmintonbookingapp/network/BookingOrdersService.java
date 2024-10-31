package com.example.badmintonbookingapp.network;

import com.example.badmintonbookingapp.dto.request.BookingOrdersRequestDTO;
import com.example.badmintonbookingapp.dto.response.BookingOrdersResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BookingOrdersService {
    @POST("/api/v1/bookingOrders/create")
    Call<List<BookingOrdersResponseDTO>> createBookingOrders(@Body List<BookingOrdersRequestDTO> bookingOrdersRequestDTOs);

    @GET("/api/v1/bookingOrders/user/{userId}")
    Call<List<BookingOrdersResponseDTO>> getAllBookingOrdersByUserId(@Path("userId") Integer userId);

    @PATCH("/api/v1/bookingOrders/status/{id}")
    Call<String> updateBookingOrderStatus(@Path("id") Integer id);
}