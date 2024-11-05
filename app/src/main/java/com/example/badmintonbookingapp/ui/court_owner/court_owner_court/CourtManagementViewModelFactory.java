package com.example.badmintonbookingapp.ui.court_owner.court_owner_court;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.utils.TokenManager;

public class CourtManagementViewModelFactory implements ViewModelProvider.Factory {


    private final TokenManager tokenManager;
    private final AuthRepository authRepository;
    private final int hostId;

    public CourtManagementViewModelFactory(TokenManager tokenManager, AuthRepository authRepository, int hostId) {
        this.tokenManager = tokenManager;
        this.authRepository = authRepository;
        this.hostId = hostId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CourtManagementViewModel.class)) {
            return (T) new CourtManagementViewModel(tokenManager, authRepository, hostId);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
