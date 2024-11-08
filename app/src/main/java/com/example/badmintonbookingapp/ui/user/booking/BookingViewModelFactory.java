package com.example.badmintonbookingapp.ui.user.booking;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.repository.BookingRepository;
import com.example.badmintonbookingapp.utils.TokenManager;

public class BookingViewModelFactory implements ViewModelProvider.Factory {
    private final TokenManager tokenManager;
    private final AuthRepository authRepository;
    private final BookingRepository bookingRepository;

    public BookingViewModelFactory(TokenManager tokenManager, AuthRepository authRepository, BookingRepository bookingRepository) {
        this.tokenManager = tokenManager;
        this.authRepository = authRepository;
        this.bookingRepository = bookingRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BookingViewModel.class)) {
            return (T) new BookingViewModel(tokenManager, authRepository, bookingRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
