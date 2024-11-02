package com.example.badmintonbookingapp.ui.not_fragment.court_detail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.ui.user.home.UserHomeViewModel;
import com.example.badmintonbookingapp.utils.TokenManager;

public class YardDetailViewModelFactory implements ViewModelProvider.Factory {
    private final TokenManager tokenManager;
    private final AuthRepository authRepository;

    public YardDetailViewModelFactory(TokenManager tokenManager, AuthRepository authRepository) {
        this.tokenManager = tokenManager;
        this.authRepository = authRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(YardDetailViewModel.class)) {
            return (T) new YardDetailViewModel(tokenManager, authRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
