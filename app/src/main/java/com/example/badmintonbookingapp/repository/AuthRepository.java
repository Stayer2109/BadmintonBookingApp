package com.example.badmintonbookingapp.repository;

import com.example.badmintonbookingapp.client.APIClient;
import com.example.badmintonbookingapp.dto.ApiResponse;
import com.example.badmintonbookingapp.dto.request.SignInRequest;
import com.example.badmintonbookingapp.dto.request.SignUpRequest;
import com.example.badmintonbookingapp.dto.response.AuthResponse;
import com.example.badmintonbookingapp.dto.response.UserResponseDTO;
import com.example.badmintonbookingapp.network.ApiCallback;
import com.example.badmintonbookingapp.network.AuthService;
import com.example.badmintonbookingapp.utils.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private final AuthService authService; // Make final
    private final TokenManager tokenManager;

    public AuthRepository(TokenManager tokenManager) {
        // Pass both TokenManager and AuthRepository to APIClient.getService
        this.tokenManager = tokenManager;
        authService = APIClient.getService(AuthService.class, tokenManager, this);
    }
    public AuthRepository(AuthService authService, TokenManager tokenManager) { // Corrected Constructor
        this.authService = authService;  // Initialize directly
        this.tokenManager = tokenManager;
    }

    public void signIn(String username, String password, final ApiCallback<AuthResponse> callback) {
        SignInRequest signInRequest = new SignInRequest(username, password);
        authService.signIn(signInRequest).enqueue(new Callback<ApiResponse<AuthResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<AuthResponse>> call, Response<ApiResponse<AuthResponse>> response) {
                if (response.isSuccessful()) {
                    tokenManager.saveTokens(response.body().getData().getToken());
                    callback.onSuccess(response.body().getData());
                } else {
                    callback.onError(new Throwable("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<AuthResponse>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public void register(SignUpRequest request, ApiCallback<AuthResponse> apiCallback) {
        authService.signUp(request).enqueue(new Callback<ApiResponse<AuthResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<AuthResponse>> call, Response<ApiResponse<AuthResponse>> response) {
                if (response.isSuccessful()) {
                    tokenManager.saveTokens(response.body().getData().getToken());
                    apiCallback.onSuccess(response.body().getData());
                } else {
                    apiCallback.onError(new Throwable("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<AuthResponse>> call, Throwable t) {
                apiCallback.onError(t);
            }
        });
    }

    public void logout() {
        tokenManager.clearTokens();
    }
}
