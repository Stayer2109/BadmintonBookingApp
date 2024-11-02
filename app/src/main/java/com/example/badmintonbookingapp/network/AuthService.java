package com.example.badmintonbookingapp.network;

import com.example.badmintonbookingapp.dto.request.SignInRequest;
import com.example.badmintonbookingapp.dto.request.SignUpRequest;
import com.example.badmintonbookingapp.dto.response.JwtAuthenticationResponse;
import com.example.badmintonbookingapp.dto.response.UserResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthService {
     String requestMapping = "/api/v1/auth";

     @POST(requestMapping + "/signin")
     Call<JwtAuthenticationResponse> signIn(@Body SignInRequest loginRequestDTO);

     @POST(requestMapping + "/refresh")
     Call<JwtAuthenticationResponse> refreshToken();

     @POST(requestMapping + "/signup")
     Call<JwtAuthenticationResponse> signUp(@Body SignUpRequest signUpRequestDTO);

     @POST(requestMapping + "/logout")
     Call<Void> logOut();

     @GET(requestMapping + "/account")
     Call<UserResponseDTO> getAccount();
}
