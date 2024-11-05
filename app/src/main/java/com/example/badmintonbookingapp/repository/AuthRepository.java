package com.example.badmintonbookingapp.repository;

import com.example.badmintonbookingapp.client.APIClient;
import com.example.badmintonbookingapp.dto.ApiResponse;
import com.example.badmintonbookingapp.dto.request.SignInRequest;
import com.example.badmintonbookingapp.dto.request.SignUpRequest;
import com.example.badmintonbookingapp.dto.response.JwtAuthenticationResponse;
import com.example.badmintonbookingapp.dto.response.UserResponseDTO;
import com.example.badmintonbookingapp.dto.response.wrapper.UserResponseWrapper;
import com.example.badmintonbookingapp.network.ApiCallback;
import com.example.badmintonbookingapp.network.AuthService;
import com.example.badmintonbookingapp.utils.TokenManager;
import com.google.gson.JsonObject;

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

    public void signIn(String username, String password, final ApiCallback<JwtAuthenticationResponse> callback) {
        SignInRequest signInRequest = new SignInRequest(username, password);
        authService.signIn(signInRequest).enqueue(new Callback<JwtAuthenticationResponse>() {
            @Override
            public void onResponse(Call<JwtAuthenticationResponse> call, Response<JwtAuthenticationResponse> response) {
                if (response.isSuccessful()) {
                    tokenManager.saveTokens(response.body().getToken(), response.body().getRefreshToken());
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
        /*SignInRequest signInRequest = new SignInRequest(username, password);
        authService.refreshToken(signInRequest).enqueue(new Callback<JwtAuthenticationResponse>() {
            @Override
            public void onResponse(Call<JwtAuthenticationResponse> call, Response<JwtAuthenticationResponse> response) {
                if (response.isSuccessful()) {
                    tokenManager.saveTokens(response.body().getToken(), response.body().getRefreshToken());
                    callback.onSuccess(response.body());
                } else {
                    callback.onError(new Throwable("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<JwtAuthenticationResponse> call, Throwable t) {
                callback.onError(t);
            }
        });*/
        return null;
    }

    public void getAccount (final ApiCallback<UserResponseDTO> callback) {
        authService.getAccount().enqueue(new Callback<ApiResponse<JsonObject>>() {
            @Override
            public void onResponse(Call<ApiResponse<JsonObject>> call, Response<ApiResponse<JsonObject>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserResponseDTO userResponseDTO = new UserResponseDTO();
                    /*userResponseDTO.setEmail(response.body().getData().get("email").getAsString());
                    userResponseDTO.setFirstName(response.body().getData().get("first_name").getAsString());
                    userResponseDTO.setLastName(response.body().getData().get("last_name").getAsString());*/
                    callback.onSuccess(userResponseDTO);
                } else {
                    callback.onError(new Throwable("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<JsonObject>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public void register(SignUpRequest request, ApiCallback<JwtAuthenticationResponse> apiCallback) {
        authService.signUp(request).enqueue(new Callback<JwtAuthenticationResponse>() {
            @Override
            public void onResponse(Call<JwtAuthenticationResponse> call, Response<JwtAuthenticationResponse> response) {
                if (response.isSuccessful()) {
                    JwtAuthenticationResponse jwtAuthenticationResponse = response.body();
                    tokenManager.saveTokens(jwtAuthenticationResponse.getToken(), jwtAuthenticationResponse.getRefreshToken());
                    apiCallback.onSuccess(null);
                } else {
                    apiCallback.onError(new Throwable("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<JwtAuthenticationResponse> call, Throwable t) {
                apiCallback.onError(t);
            }
        });
    }

    public void logout(ApiCallback<Void> apiCallback) {
        authService.logOut().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    tokenManager.clearTokens();
                    apiCallback.onSuccess(null);
                } else {
                    apiCallback.onError(new Throwable("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                apiCallback.onError(t);
            }
        });
    }
}
