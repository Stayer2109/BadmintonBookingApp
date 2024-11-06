package com.example.badmintonbookingapp.repository;

import com.example.badmintonbookingapp.client.APIClient;
import com.example.badmintonbookingapp.dto.request.SlotRequestDTO;
import com.example.badmintonbookingapp.dto.response.SlotResponseDTO;
import com.example.badmintonbookingapp.network.SlotService;
import com.example.badmintonbookingapp.utils.TokenManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SlotRepository {

    private final SlotService slotService;
    private final AuthRepository authRepository;


    public SlotRepository(TokenManager tokenManager, AuthRepository authRepository) {
        Retrofit retrofit = APIClient.getClient(tokenManager, authRepository);
        slotService = retrofit.create(SlotService.class);
        this.authRepository = authRepository;
    }

    public void getSlotsByYardId(int yardId, Callback<List<SlotResponseDTO>> callback) {
        slotService.getSlotsByYardId(yardId).enqueue(new Callback<List<SlotResponseDTO>>() {
            @Override
            public void onResponse(Call<List<SlotResponseDTO>> call, Response<List<SlotResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, Response.success(response.body()));
                } else {
                    callback.onFailure(call, new Throwable("Failed to fetch slots by yard id " + yardId));
                }
            }

            @Override
            public void onFailure(Call<List<SlotResponseDTO>> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

    public void createNewSlot(int yardId, SlotRequestDTO slotRequestDTO, Callback<Object> callback) {
        Call<Object> call = slotService.createNewSlot(yardId, slotRequestDTO);
        call.enqueue(callback);
    }

    public void updateSlot(int slotId, SlotRequestDTO slotRequestDTO, Callback<SlotRequestDTO> callback) {
        Call<SlotRequestDTO> call = slotService.updateSlot(slotId, slotRequestDTO);
        call.enqueue(callback);
    }
}