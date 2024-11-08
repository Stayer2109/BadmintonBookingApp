package com.example.badmintonbookingapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.badmintonbookingapp.client.APIClient;
import com.example.badmintonbookingapp.dto.ApiResponse;
import com.example.badmintonbookingapp.dto.request.SlotRequestDTO;
import com.example.badmintonbookingapp.dto.response.SlotResponseDTO;
import com.example.badmintonbookingapp.dto.response.YardResponseDTO;
import com.example.badmintonbookingapp.network.ApiCallback;
import com.example.badmintonbookingapp.network.SlotService;
import com.example.badmintonbookingapp.utils.TokenManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SlotRepository {
    private final String TAG = SlotRepository.class.getSimpleName();

    private final SlotService slotService;
    private final AuthRepository authRepository;
    private MutableLiveData<List<SlotResponseDTO>> slotsLiveData;
    private MutableLiveData<SlotResponseDTO> slotLiveData;

    public SlotRepository(TokenManager tokenManager, AuthRepository authRepository) {
        slotsLiveData = new MutableLiveData<>();
        slotLiveData = new MutableLiveData<>();
        Retrofit retrofit = APIClient.getClient(tokenManager, authRepository);
        slotService = retrofit.create(SlotService.class);
        this.authRepository = authRepository;
    }

    // Lấy danh sách slot theo YardId
    public void fetchSlotsByYardId(int yardId, ApiCallback<List<SlotResponseDTO>> callback) {
        slotService.getSlotsByYardId(yardId).enqueue(new Callback<ApiResponse<List<SlotResponseDTO>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<SlotResponseDTO>>> call, Response<ApiResponse<List<SlotResponseDTO>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<SlotResponseDTO> data = response.body().getData();
                    slotsLiveData.postValue(data);
                    callback.onSuccess(data);
                    Log.d(TAG, "onResponse: " + data);
                } else {
                    callback.onError(new Throwable("Error"));
                    Log.d(TAG, "onResponse: " + response);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<SlotResponseDTO>>> call, Throwable t) {
                callback.onError(t);
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public LiveData<List<SlotResponseDTO>> getSlots() {
        return slotsLiveData;
    }
}
