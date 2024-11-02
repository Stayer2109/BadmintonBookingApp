package com.example.badmintonbookingapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
    private YardService yardService;  // Changed from YardApi to YardService
    private MutableLiveData<List<YardResponseDTO>> yardsLiveData;
    private TokenManager token;

    public YardRepository(TokenManager tokenManager, AuthRepository authRepository) {
        // Use the updated APIClient to get YardService with AuthInterceptor included
        yardService = APIClient.getService(YardService.class, tokenManager, authRepository);
        this.token = tokenManager;
        yardsLiveData = new MutableLiveData<>();
    }

    public void fetchAllYards() {
        yardService.getAllYards(0).enqueue(new Callback<List<YardResponseDTO>>() {
            @Override
            public void onResponse(Call<List<YardResponseDTO>> call, Response<List<YardResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    yardsLiveData.postValue(response.body());
                } else {
                    Log.e("YardRepository", "Failed to fetch yards: " +
                            token.getAccessToken());
                }
            }

            @Override
            public void onFailure(Call<List<YardResponseDTO>> call, Throwable t) {
                Log.e("YardRepository", "Error fetching yards: " + t.getMessage());
            }
        });
    }

    public LiveData<List<YardResponseDTO>> getYards() {
        return yardsLiveData;
    }
}
