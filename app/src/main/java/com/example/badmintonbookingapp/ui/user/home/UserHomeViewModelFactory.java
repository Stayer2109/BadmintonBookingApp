package com.example.badmintonbookingapp.ui.user.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.utils.TokenManager;

public class UserHomeViewModelFactory implements ViewModelProvider.Factory {
    private final TokenManager tokenManager;
    private final AuthRepository authRepository;

    public UserHomeViewModelFactory(TokenManager tokenManager, AuthRepository authRepository) {
        this.tokenManager = tokenManager;
        this.authRepository = authRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserHomeViewModel.class)) {
            return (T) new UserHomeViewModel(tokenManager, authRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
