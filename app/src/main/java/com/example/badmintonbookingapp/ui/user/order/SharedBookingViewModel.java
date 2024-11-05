package com.example.badmintonbookingapp.ui.user.order;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedBookingViewModel extends ViewModel {
    private final MutableLiveData<Boolean> bookingConfirmed = new MutableLiveData<>(false);

    public LiveData<Boolean> getBookingConfirmed() {
        return bookingConfirmed;
    }

    public void setBookingConfirmed(boolean confirmed) {
        bookingConfirmed.setValue(confirmed);
    }
}
