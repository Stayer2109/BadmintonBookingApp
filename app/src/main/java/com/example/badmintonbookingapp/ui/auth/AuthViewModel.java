package com.example.badmintonbookingapp.ui.auth;// viewmodel/AuthViewModel.java
import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.badmintonbookingapp.dto.response.JwtAuthenticationResponse;
import com.example.badmintonbookingapp.dto.response.UserResponseDTO;
import com.example.badmintonbookingapp.network.ApiCallback;
import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.utils.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends AndroidViewModel {
    private AuthRepository authRepository;
    private MutableLiveData<JwtAuthenticationResponse> signInResponse = new MutableLiveData<>();
    private MutableLiveData<UserResponseDTO> userInfo = new MutableLiveData<>();
    private MutableLiveData<Throwable> error = new MutableLiveData<>();

    public AuthViewModel(Application application) {
        super(application);
        // Use application context for TokenManager
        TokenManager tokenManager = TokenManager.getInstance(application);
        authRepository = new AuthRepository(tokenManager);
    }

    public LiveData<JwtAuthenticationResponse> getSignInResponse() {
        return signInResponse;
    }

    public LiveData<Throwable> getError() {
        return error;
    }

    // Method to sign in
    public void signIn(String username, String password) {
        authRepository.signIn(username, password, new ApiCallback<JwtAuthenticationResponse>() {
            @Override
            public void onSuccess(JwtAuthenticationResponse result) {
                signInResponse.setValue(result);
            }

            @Override
            public void onError(Throwable err) {
                error.setValue(err);
            }
        });
    }

    // Method to get user information
    public LiveData<UserResponseDTO> getUserInfo() {
        return userInfo;
    }

    // Method to fetch user information
    public void fetchUserInfo() {
        authRepository.getUserInfo().enqueue(new Callback<UserResponseDTO>() {
            @Override
            public void onResponse(Call<UserResponseDTO> call, Response<UserResponseDTO> response) {
                if (response.isSuccessful()) {
                    userInfo.setValue(response.body());
                } else {
                    error.setValue(new Throwable("Failed to fetch user info: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<UserResponseDTO> call, Throwable t) {
                error.setValue(t);
            }
        });
    }
}
