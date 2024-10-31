package com.example.badmintonbookingapp.network;

import com.example.badmintonbookingapp.dto.YardImagesDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface YardImageService {

    @GET("/api/v1/yardImages/get-images/{yardId}")
    Call<List<YardImagesDTO>> getImagesByYardId(@Path("yardId") Integer yardId);

    @POST("/api/v1/yardImages/add-new-image")
    Call<Void> addNewImage(@Body YardImagesDTO yardImagesDTO);

    @DELETE("/api/v1/yardImages/delete/{imageId}")
    Call<Void> deleteYardImage(@Path("imageId") Integer imageId);
}
