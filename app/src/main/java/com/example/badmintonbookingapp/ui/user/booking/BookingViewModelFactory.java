package com.example.badmintonbookingapp.ui.user.booking;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.badmintonbookingapp.repository.BookingRepository;
import com.example.badmintonbookingapp.repository.SlotRepository;

public class BookingViewModelFactory implements ViewModelProvider.Factory {

    private final BookingRepository bookingRepository;
    private final SlotRepository slotRepository;

    public BookingViewModelFactory(BookingRepository bookingRepository, SlotRepository slotRepository) {
        this.bookingRepository = bookingRepository;
        this.slotRepository = slotRepository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BookingViewModel.class)) {
            return (T) new BookingViewModel(bookingRepository, slotRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}