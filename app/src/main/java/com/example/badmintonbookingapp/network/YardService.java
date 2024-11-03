package com.example.badmintonbookingapp.network;

import com.example.badmintonbookingapp.dto.request.YardRequestDTO;
import com.example.badmintonbookingapp.dto.response.YardResponseDTO;
import com.example.badmintonbookingapp.dto.response.wrapper.YardDetailResponseWrapper;
import com.example.badmintonbookingapp.dto.response.wrapper.YardResponseWrapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface YardService {
    String requestMapping = "/api/v1/yards";

    @POST(requestMapping + "/create")
    Call<YardResponseDTO> createYard(@Body YardRequestDTO yardRequestDTO);

    @GET(requestMapping + "/province-ids")
    Call<List<Integer>> getProvinceIds();

    @PUT(requestMapping + "/update/{id}")
    Call<YardResponseDTO> updateYard(@Path("id") Integer id, @Body YardRequestDTO yardRequestDTO);

    @GET(requestMapping + "/{id}")
    Call<YardResponseDTO> getYardById(@Path("id") Integer id);

    @GET(requestMapping)
    Call<YardResponseWrapper> getAllYards(@Query("pageNumber") int pageNumber);

    @GET(requestMapping + "/active")
    Call<List<YardResponseDTO>> getAllActiveYards(@Query("pageNumber") int pageNumber);

    @GET(requestMapping + "/search")
    Call<List<YardResponseDTO>> getYardByName(@Query("name") String name, @Query("pageNumber") int pageNumber);

    @GET(requestMapping + "/getByHost/{hostId}")
    Call<List<YardResponseDTO>> getAllYardsByHostId(@Path("hostId") Integer hostId);

    @GET(requestMapping + "/{yardId}/active-slots")
    Call<YardResponseDTO> getYardDetailActiveSlots(@Path("yardId") Integer yardId);

    @GET(requestMapping + "/getRandom")
    Call<Object> getRandomYard();

    @POST(requestMapping + "/{yardId}/add-telephones")
    Call<YardResponseDTO> addTelephonesToYard(@Path("yardId") Integer yardId, @Body List<String> telephones);

    @POST("yards/{yardId}/add-images")
    Call<YardResponseDTO> addImagesToYard(@Path("yardId") Integer yardId, @Body List<String> imageUrls);
}

