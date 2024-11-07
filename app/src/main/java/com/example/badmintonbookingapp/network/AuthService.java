package com.example.badmintonbookingapp.network;

import com.example.badmintonbookingapp.dto.ApiResponse;
import com.example.badmintonbookingapp.dto.request.SignInRequest;
import com.example.badmintonbookingapp.dto.request.SignUpRequest;
import com.example.badmintonbookingapp.dto.response.AuthResponse;
import com.example.badmintonbookingapp.dto.response.UserResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthService {
     String requestMapping = "/api/v1/auth";

     @POST(requestMapping + "/signin")
     Call<ApiResponse<AuthResponse>> signIn(@Body SignInRequest loginRequestDTO);

     @POST(requestMapping + "/signup")
     Call<ApiResponse<AuthResponse>> signUp(@Body SignUpRequest signUpRequestDTO);
}
