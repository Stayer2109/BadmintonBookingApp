package com.example.badmintonbookingapp.network;

import com.example.badmintonbookingapp.dto.ApiResponse;
import com.example.badmintonbookingapp.dto.request.YardRequestDTO;
import com.example.badmintonbookingapp.dto.response.YardResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface YardService {
    String requestMapping = "/api/v1/yards";
    @GET(requestMapping)
    Call<ApiResponse<List<YardResponseDTO>>> getAllYards(@Query("pageNumber") int pageNumber);

    @GET(requestMapping + "/host/{hostId}")
    Call<ApiResponse<List<YardResponseDTO>>> getAllYardsByHostId(@Path("hostId") int hostId);

    @GET(requestMapping + "/{id}")
    Call<ApiResponse<YardResponseDTO>> getYardById(@Path("id") int id);

    @POST(requestMapping)
    Call<ApiResponse<YardResponseDTO>> createYard(@Body YardRequestDTO yard);
}

