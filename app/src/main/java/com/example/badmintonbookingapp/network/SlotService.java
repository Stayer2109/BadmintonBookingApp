package com.example.badmintonbookingapp.network;
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

    @POST("/api/v1/slots/create/{yardId}")
    Call<Object> createNewSlot(@Path("yardId") Integer yardId, @Body SlotRequestDTO slotRequestDTO);

    @PATCH("/api/v1/slots/update/{slotId}")
    Call<SlotRequestDTO> updateSlot(@Path("slotId") Integer slotId, @Body SlotRequestDTO slotRequestDTO);

    @GET("/api/v1/slots/{yardId}")
    Call<List<SlotResponseDTO>> getSlotsByYardId(@Path("yardId") Integer yardId, @Query("pageNumber") int pageNumber);
}
