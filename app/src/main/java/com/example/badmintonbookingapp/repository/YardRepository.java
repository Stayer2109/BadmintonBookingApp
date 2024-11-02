package com.example.badmintonbookingapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.badmintonbookingapp.client.APIClient;
import com.example.badmintonbookingapp.dto.response.YardResponseDTO;
import com.example.badmintonbookingapp.dto.response.wrapper.YardResponseWrapper;
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

    public YardRepository(TokenManager tokenManager, AuthRepository authRepository) {
        // Use the updated APIClient to get YardService with AuthInterceptor included
        yardService = APIClient.getService(YardService.class, tokenManager, authRepository);
        yardsLiveData = new MutableLiveData<>();
    }

    public void fetchAllYards() {
        yardService.getAllYards(0).enqueue(new Callback<YardResponseWrapper>() {
            @Override
            public void onResponse(Call<YardResponseWrapper> call, Response<YardResponseWrapper> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Extract the list of yards from the wrapper object
                    yardsLiveData.postValue(response.body().getData());

                    for (YardResponseDTO yard : response.body().getData()) {
                        Log.d("YardRepository", yard.toString());
                    }

                } else {
                    Log.e("YardRepository", "Failed to fetch yards: " +
                            response.toString());
                }
            }

            @Override
            public void onFailure(Call<YardResponseWrapper> call, Throwable t) {
                Log.e("YardRepository", "Error fetching yards: " + t.getMessage());
            }
        });
    }

    public LiveData<List<YardResponseDTO>> getYards() {
        return yardsLiveData;
    }
}
