package com.example.badmintonbookingapp.repository;

import com.example.badmintonbookingapp.client.APIClient;
import com.example.badmintonbookingapp.dto.response.YardResponseDTO;
import com.example.badmintonbookingapp.network.ApiCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.badmintonbookingapp.network.YardService;
import com.example.badmintonbookingapp.utils.TokenManager;

import java.util.List;

public class YardRepository {
    private YardService yardService;
    private TokenManager tokenManager;

    public YardRepository(TokenManager tokenManager) {
        yardService = APIClient.getService(YardService.class);
        this.tokenManager = tokenManager;
    }

    public void getYards(final ApiCallback<List<YardResponseDTO>> callback) {
        String token = tokenManager.getAccessToken();
        yardService.getAllYards(1).enqueue(new Callback<List<YardResponseDTO>>() {
            @Override
            public void onResponse(Call<List<YardResponseDTO>> call, Response<List<YardResponseDTO>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError(new Throwable("Failed to load yards"));
                }
            }

            @Override
            public void onFailure(Call<List<YardResponseDTO>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

}
