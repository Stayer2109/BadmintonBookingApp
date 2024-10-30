package com.example.badmintonbookingapp.service;

import com.example.badmintonbookingapp.dto.request.YardCheckinRequestDTO;
import com.example.badmintonbookingapp.dto.response.YardCheckinResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface YardCheckInService {

    @GET("/api/v1/checkIn/findByYardId")
    Call<List<YardCheckinResponseDTO>> findAllYardCheckIn(@Query("yardId") Integer yardId);

    @POST("/api/v1/checkIn/create")
    Call<YardCheckinResponseDTO> checkIn(@Body YardCheckinRequestDTO requestDTO);

    @PATCH("/api/v1/checkIn/status")
    Call<YardCheckinResponseDTO> updateStatus(@Body YardCheckinRequestDTO requestDTO);
}
