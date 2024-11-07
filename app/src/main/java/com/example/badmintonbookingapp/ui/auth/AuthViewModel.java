package com.example.badmintonbookingapp.ui.auth;// viewmodel/AuthViewModel.java
import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.badmintonbookingapp.dto.request.SignUpRequest;
import com.example.badmintonbookingapp.dto.response.AuthResponse;
import com.example.badmintonbookingapp.dto.response.UserResponseDTO;
import com.example.badmintonbookingapp.network.ApiCallback;
import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.utils.TokenManager;

public class AuthViewModel extends AndroidViewModel {
    private AuthRepository authRepository;
    private MutableLiveData<AuthResponse> signInResponse = new MutableLiveData<>();
    private MutableLiveData<UserResponseDTO> accountInfo = new MutableLiveData<>();
    private MutableLiveData<Throwable> error = new MutableLiveData<>();
    private TokenManager tokenManager;

    public AuthViewModel(Application application) {
        super(application);
        // Use application context for TokenManager
        tokenManager = TokenManager.getInstance(application);
        authRepository = new AuthRepository(tokenManager);
    }

    public LiveData<AuthResponse> getSignInResponse() {
        return signInResponse;
    }

    public LiveData<Throwable> getError() {
        return error;
    }

    // Method to sign in
    public void signIn(String username, String password) {
        authRepository.signIn(username, password, new ApiCallback<AuthResponse>() {
            @Override
            public void onSuccess(AuthResponse result) {
                signInResponse.setValue(result);
            }

            @Override
            public void onError(Throwable err) {
                error.setValue(err);
            }
        });
    }

    // Method to get user information
    public LiveData<UserResponseDTO> getAccountInfo() {
        return accountInfo;
    }

    // Method to Sign up
    public void register(SignUpRequest signUpRequest) {
        // Call register method from repository, on success show success message, on error show error message
        authRepository.register(signUpRequest, new ApiCallback<AuthResponse>() {
            @Override
            public void onSuccess(AuthResponse result) {
                Toast.makeText(getApplication(), "Registration successful!", Toast.LENGTH_SHORT).show();
                signInResponse.setValue(result);
            }

            @Override
            public void onError(Throwable err) {
                error.setValue(err);
            }
        });
    }

    // Method to logout
    public void logout() {
        authRepository.logout();
    }
}
