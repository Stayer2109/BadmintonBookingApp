package com.example.badmintonbookingapp.network;

import com.example.badmintonbookingapp.dto.ApiResponse;
import com.example.badmintonbookingapp.dto.request.SlotRequestDTO;
import com.example.badmintonbookingapp.dto.response.SlotResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SlotService {
    String requestMapping = "/api/v1/slots";

    @POST(requestMapping + "/create/{yardId}")
    Call<Object> createNewSlot(@Path("yardId") Integer yardId, @Body SlotRequestDTO slotRequestDTO);

    @PATCH(requestMapping + "/update/{slotId}")
    Call<SlotRequestDTO> updateSlot(@Path("slotId") Integer slotId, @Body SlotRequestDTO slotRequestDTO);

    @GET(requestMapping)
    Call<ApiResponse<List<SlotResponseDTO>>> getSlotsByYardId(@Query("yardId") Integer yardId);
}
