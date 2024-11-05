package com.example.badmintonbookingapp.repository;

import android.app.Dialog;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.badmintonbookingapp.client.APIClient;
import com.example.badmintonbookingapp.dto.request.YardRequestDTO;
import com.example.badmintonbookingapp.dto.response.YardResponseDTO;
import com.example.badmintonbookingapp.dto.response.wrapper.YardResponseWrapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.badmintonbookingapp.network.ApiCallback;
import com.example.badmintonbookingapp.network.YardService;
import com.example.badmintonbookingapp.utils.TokenManager;

import java.util.List;

public class YardRepository {
    private YardService yardService;
    private MutableLiveData<List<YardResponseDTO>> yardsLiveData;
    private MutableLiveData<YardResponseDTO> yardLiveData;

    public YardRepository(TokenManager tokenManager, AuthRepository authRepository) {
        yardService = APIClient.getService(YardService.class, tokenManager, authRepository);
        yardsLiveData = new MutableLiveData<>();
        yardLiveData = new MutableLiveData<>(); // Initialize yardLiveData here
    }

    public void fetchYardsByPage(int pageNumber, ApiCallback<List<YardResponseDTO>> callback) {
        yardService.getAllYards(pageNumber).enqueue(new Callback<YardResponseWrapper>() {
            @Override
            public void onResponse(Call<YardResponseWrapper> call, Response<YardResponseWrapper> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getData());
                    Log.d("YardRepository", "onResponse: " + response);
                    Log.d("YardRepository", "Fetched yards on page " + pageNumber + ": " + response.body().getData());
                } else {
                    callback.onError(new Throwable("Failed to fetch yards on page " + pageNumber));
                    Log.e("YardRepository", "Failed to fetch yards on page " + pageNumber);
                }
            }

            @Override
            public void onFailure(Call<YardResponseWrapper> call, Throwable t) {
                callback.onError(t);
                Log.e("YardRepository", "Error fetching yards on page " + pageNumber, t);
            }
        });
    }

    public void fetchYardsByHostId(int hostId, ApiCallback<List<YardResponseDTO>> callback) {
        yardService.getAllYardsByHostId(hostId).enqueue(new retrofit2.Callback<List<YardResponseDTO>>() {
            @Override
            public void onResponse(Call<List<YardResponseDTO>> call, Response<List<YardResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError(new Throwable("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<List<YardResponseDTO>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    // Fetch yard details by ID and update yardLiveData
    public void getYardById(int id) {
        yardService.getYardById(id).enqueue(new Callback<YardResponseDTO>() {
            @Override
            public void onResponse(Call<YardResponseDTO> call, Response<YardResponseDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    yardLiveData.postValue(response.body());
                    Log.d("YardRepository", "Fetched yard: " + response.body());
                } else {
                    Log.e("YardRepository", "Failed to fetch yard: " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<YardResponseDTO> call, Throwable throwable) {

            }
        });
    }

    public void createYard(YardRequestDTO yard, ApiCallback<YardResponseDTO> callback) {
        yardService.createYard(yard).enqueue(new Callback<YardResponseDTO>() {
            @Override
            public void onResponse(Call<YardResponseDTO> call, Response<YardResponseDTO> response) {
                if (response.isSuccessful()) {
                    YardResponseDTO yardResponse = response.body();

                    if (yardResponse != null) {
                        Log.d("YardRepository", "Full YardResponse: " + yardResponse);
                        callback.onSuccess(yardResponse);
                    } else {
                        // Handle the null response scenario
                        Log.e("YardRepository", "Received null YardResponse from the server");
                        callback.onError(new Throwable("Server returned no data for the created yard"));
                    }
                } else {
                    callback.onError(new Throwable("Failed to create yard: " + response.message()));
                    Log.e("YardRepository", "Failed to create yard: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<YardResponseDTO> call, Throwable throwable) {
                // Call onError on the callback in case of a network failure or other issues
                callback.onError(throwable);
                Log.e("YardRepository", "Error creating yard", throwable);
            }
        });
    }

    public LiveData<List<YardResponseDTO>> getYards() {
        return yardsLiveData;
    }

    public LiveData<YardResponseDTO> getYard() {
        return yardLiveData;
    }
}
