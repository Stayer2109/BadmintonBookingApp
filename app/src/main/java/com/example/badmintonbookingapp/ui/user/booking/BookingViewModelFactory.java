package com.example.badmintonbookingapp.ui.user.booking;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.badmintonbookingapp.repository.AuthRepository; // Import AuthRepository
import com.example.badmintonbookingapp.utils.TokenManager;

import com.example.badmintonbookingapp.repository.BookingRepository;


public class BookingViewModelFactory implements ViewModelProvider.Factory {

    private final BookingRepository repository;

    public BookingViewModelFactory(BookingRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BookingViewModel.class)) {
            return (T) new BookingViewModel(repository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
