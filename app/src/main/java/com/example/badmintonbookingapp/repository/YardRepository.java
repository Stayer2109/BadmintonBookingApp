package com.example.badmintonbookingapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.badmintonbookingapp.client.APIClient;
import com.example.badmintonbookingapp.dto.ApiResponse;
import com.example.badmintonbookingapp.dto.request.YardRequestDTO;
import com.example.badmintonbookingapp.dto.response.YardResponseDTO;
import com.example.badmintonbookingapp.network.ApiCallback;
import com.example.badmintonbookingapp.network.YardService;
import com.example.badmintonbookingapp.utils.TokenManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YardRepository {
    private YardService yardService;
    private MutableLiveData<List<YardResponseDTO>> yardsLiveData;
    private MutableLiveData<YardResponseDTO> yardLiveData;

    public YardRepository(TokenManager tokenManager, AuthRepository authRepository) {
        yardService = APIClient.getService(YardService.class, tokenManager, authRepository);
        yardsLiveData = new MutableLiveData<>();
        yardLiveData = new MutableLiveData<>();
    }

    public void fetchYardsByPage(int pageNumber, ApiCallback<List<YardResponseDTO>> callback) {
        yardService.getAllYards(pageNumber).enqueue(new Callback<ApiResponse<List<YardResponseDTO>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<YardResponseDTO>>> call, Response<ApiResponse<List<YardResponseDTO>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<YardResponseDTO> data = response.body().getData();
                    yardsLiveData.postValue(data);
                    callback.onSuccess(data);
                    Log.d("YardRepository", "Fetched yards on page " + pageNumber + ": " + response.body().toString());
                } else {
                    String errorMessage = response.message() != null ? response.message() : "Unknown error";
                    callback.onError(new Throwable("Failed to fetch yards on page " + pageNumber + ": " + errorMessage));
                    Log.e("YardRepository", "Failed to fetch yards on page " + pageNumber + ": " + errorMessage);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<YardResponseDTO>>> call, Throwable t) {
                callback.onError(t);
                Log.e("YardRepository", "Error fetching yards on page " + pageNumber, t);
            }
        });
    }

    public void fetchYardsByHostId(int hostId, ApiCallback<List<YardResponseDTO>> callback) {
        yardService.getAllYardsByHostId(hostId).enqueue(new Callback<ApiResponse<List<YardResponseDTO>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<YardResponseDTO>>> call, Response<ApiResponse<List<YardResponseDTO>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse<List<YardResponseDTO>> apiResponse = response.body();
                    callback.onSuccess(apiResponse.getData());
                } else {
                    callback.onError(new Throwable("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<YardResponseDTO>>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public void getYardById(int id) {
        yardService.getYardById(id).enqueue(new Callback<ApiResponse<YardResponseDTO>>() {
            @Override
            public void onResponse(Call<ApiResponse<YardResponseDTO>> call, Response<ApiResponse<YardResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse<YardResponseDTO> apiResponse = response.body();
                    yardLiveData.postValue(apiResponse.getData());
                    Log.d("YardRepository", "Fetched yard: " + apiResponse.getData());
                } else {
                    Log.e("YardRepository", "Failed to fetch yard: " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<YardResponseDTO>> call, Throwable throwable) {
                Log.e("YardRepository", "Error fetching yard by ID", throwable);
            }
        });
    }

    public void createYard(YardRequestDTO yard, ApiCallback<YardResponseDTO> callback) {
        yardService.createYard(yard).enqueue(new Callback<ApiResponse<YardResponseDTO>>() {
            @Override
            public void onResponse(Call<ApiResponse<YardResponseDTO>> call, Response<ApiResponse<YardResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse<YardResponseDTO> apiResponse = response.body();
                    callback.onSuccess(apiResponse.getData());
                    Log.d("YardRepository", "Created yard: " + apiResponse.getData());
                } else {
                    callback.onError(new Throwable("Failed to create yard: " + response.message()));
                    Log.e("YardRepository", "Failed to create yard: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<YardResponseDTO>> call, Throwable throwable) {
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
