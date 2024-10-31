package com.example.badmintonbookingapp.repository;

import com.example.badmintonbookingapp.client.APIClient;
import com.example.badmintonbookingapp.dto.request.SignInRequest;
import com.example.badmintonbookingapp.dto.response.JwtAuthenticationResponse;
import com.example.badmintonbookingapp.dto.response.UserResponseDTO;
import com.example.badmintonbookingapp.network.ApiCallback;
import com.example.badmintonbookingapp.network.AuthService;
import com.example.badmintonbookingapp.utils.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private AuthService authService;
    private TokenManager tokenManager;

    public AuthRepository(TokenManager tokenManager) {
        authService = APIClient.getService(AuthService.class);
        this.tokenManager = tokenManager;
    }

    public void signIn(String username, String password, final ApiCallback<JwtAuthenticationResponse> callback) {
        SignInRequest signInRequest = new SignInRequest(username, password);
        authService.signIn(signInRequest).enqueue(new Callback<JwtAuthenticationResponse>() {
            @Override
            public void onResponse(Call<JwtAuthenticationResponse> call, Response<JwtAuthenticationResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError(new Throwable("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<JwtAuthenticationResponse> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public String refreshToken() {
        String refreshToken = tokenManager.getRefreshToken();
        if (refreshToken == null) return null;

        try {
            Call<JwtAuthenticationResponse> call = authService.refreshToken();
            Response<JwtAuthenticationResponse> response = call.execute();

            if (response.isSuccessful() && response.body() != null) {
                String newAccessToken = response.body().getToken();
                tokenManager.saveTokens(newAccessToken, refreshToken);
                return newAccessToken;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Call<UserResponseDTO> getUserInfo() {
        return authService.getAccount();
    }
}
