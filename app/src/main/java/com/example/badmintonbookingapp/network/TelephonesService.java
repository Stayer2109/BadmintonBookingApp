package com.example.badmintonbookingapp.network;
import com.example.badmintonbookingapp.dto.TelephonesDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TelephonesService {

    @POST("/api/telephones")
    Call<TelephonesDTO> create(@Body TelephonesDTO telephonesDTO);

    @PUT("/api/telephones/{id}")
    Call<TelephonesDTO> update(@Path("id") Integer id, @Body TelephonesDTO telephonesDTO);

    @GET("/api/telephones/{id}")
    Call<TelephonesDTO> getById(@Path("id") Integer id);

    @GET("/api/telephones")
    Call<List<TelephonesDTO>> getAll();

    @DELETE("/api/telephones/{id}")
    Call<Void> deleteTelephoneById(@Path("id") Integer id);
}
