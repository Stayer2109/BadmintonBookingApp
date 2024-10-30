package com.example.badmintonbookingapp.service;

import com.example.badmintonbookingapp.dto.UserDTO;
import com.example.badmintonbookingapp.dto.request.ChangePasswordRequest;
import com.example.badmintonbookingapp.dto.request.CreateUserRequestDTO;
import com.example.badmintonbookingapp.dto.response.CreateUserResponseDTO;
import com.example.badmintonbookingapp.dto.response.UserResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {

    @GET("api/v1/user/ping")
    Call<String> ping();

    @GET("api/v1/user/ping2/{test}")
    Call<String> pingV2(@Path("test") int test);

    @GET("api/v1/user/{id}")
    Call<UserResponseDTO> getUserById(@Path("id") Integer id);

    @GET("api/v1/user/username/{username}")
    Call<UserResponseDTO> getUserByUsername(@Path("username") String username);

    @GET("api/v1/user/email/{email}")
    Call<UserResponseDTO> getUserByEmail(@Path("email") String email);

    @GET("api/v1/user/search")
    Call<List<UserResponseDTO>> searchUsers(@Query("keyword") String keyword);

    @PATCH("api/v1/user")
    Call<Void> changePassword(@Body ChangePasswordRequest request);

    @PUT("api/v1/user/{id}")
    Call<UserResponseDTO> updateUserInfo(@Path("id") Integer id, @Body UserDTO userDTO);

    @GET("api/v1/user")
    Call<List<UserResponseDTO>> getAllUsers(
            @Query("page") Integer page,
            @Query("size") Integer size
    );

    @POST("api/v1/user/create")
    Call<CreateUserResponseDTO> createUser(@Body CreateUserRequestDTO createUserRequestDTO);

    @GET("api/v1/user/manager")
    Call<List<UserResponseDTO>> getAllStaffsByManager(
            @Query("managerId") Integer managerId,
            @Query("page") Integer page,
            @Query("size") Integer size
    );
}
