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
        Call<List<SlotResponseDTO>> call = slotService.getSlotsByYardId(yardId);
        call.enqueue(callback);
    }

    public void createNewSlot(int yardId, SlotRequestDTO slotRequestDTO, Callback<Object> callback) {
        Call<Object> call = slotService.createNewSlot(yardId, slotRequestDTO);
        call.enqueue(callback);
    }

    public void updateSlot(int slotId, SlotRequestDTO slotRequestDTO, Callback<SlotRequestDTO> callback){
        Call<SlotRequestDTO> call = slotService.updateSlot(slotId, slotRequestDTO);
        call.enqueue(callback);
    }
}