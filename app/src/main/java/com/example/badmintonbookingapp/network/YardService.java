package com.example.badmintonbookingapp.network;

import com.example.badmintonbookingapp.dto.request.YardRequestDTO;
import com.example.badmintonbookingapp.dto.response.YardResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface YardService {
    @POST("yards/create")
    Call<YardResponseDTO> createYard(@Body YardRequestDTO yardRequestDTO);

    @GET("yards/province-ids")
    Call<List<Integer>> getProvinceIds();

    @PUT("yards/update/{id}")
    Call<YardResponseDTO> updateYard(@Path("id") Integer id, @Body YardRequestDTO yardRequestDTO);

    @GET("yards/{id}")
    Call<YardResponseDTO> getYardById(@Path("id") Integer id);

    @GET("yards")
    Call<List<YardResponseDTO>> getAllYards(@Query("pageNumber") int pageNumber);

    @GET("yards/active")
    Call<List<YardResponseDTO>> getAllActiveYards(@Query("pageNumber") int pageNumber);

    @GET("yards/search")
    Call<List<YardResponseDTO>> getYardByName(@Query("name") String name, @Query("pageNumber") int pageNumber);

    @GET("yards/getByHost/{hostId}")
    Call<List<YardResponseDTO>> getAllYardsByHostId(@Path("hostId") Integer hostId);

    @GET("yards/{yardId}/active-slots")
    Call<YardResponseDTO> getYardDetailActiveSlots(@Path("yardId") Integer yardId);

    @GET("yards/getRandom")
    Call<Object> getRandomYard();

    @POST("yards/{yardId}/add-telephones")
    Call<YardResponseDTO> addTelephonesToYard(@Path("yardId") Integer yardId, @Body List<String> telephones);

    @POST("yards/{yardId}/add-images")
    Call<YardResponseDTO> addImagesToYard(@Path("yardId") Integer yardId, @Body List<String> imageUrls);
}

