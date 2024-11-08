package com.example.badmintonbookingapp.network;

import com.example.badmintonbookingapp.dto.ApiResponse;
import com.example.badmintonbookingapp.dto.request.BookingOrdersRequestDTO;
import com.example.badmintonbookingapp.dto.response.BookingOrdersResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookingOrdersService {
    String requestMapping = "/api/v1/bookingOrder";

    @POST(requestMapping)
    Call<ApiResponse<BookingOrdersResponseDTO>> createBookingOrder(@Body BookingOrdersRequestDTO bookingOrdersRequestDTO);

    @GET(requestMapping)
    Call<ApiResponse<List<BookingOrdersResponseDTO>>> getAllBookingOrdersByUserId(@Query("customerId") Integer userId);

    @PATCH("/api/v1/bookingOrders/status/{id}")
    Call<String> updateBookingOrderStatus(@Path("id") Integer id);
}