package com.example.badmintonbookingapp.ui.court_owner.court_owner_court;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.repository.YardRepository;
import com.example.badmintonbookingapp.utils.TokenManager;

public class CourtManagementViewModelFactory implements ViewModelProvider.Factory {


    private final TokenManager tokenManager;
    private final AuthRepository authRepository;

    public CourtManagementViewModelFactory(TokenManager tokenManager, AuthRepository authRepository) {
        this.tokenManager = tokenManager;
        this.authRepository = authRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CourtManagementViewModel.class)) {
            return (T) new CourtManagementViewModel(tokenManager, authRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
