package com.example.badmintonbookingapp.ui.user.booking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.badmintonbookingapp.dto.request.BookingOrdersRequestDTO;
import com.example.badmintonbookingapp.dto.response.BookingOrdersResponseDTO;
import com.example.badmintonbookingapp.dto.response.SimpleYardResponseDTO;
import com.example.badmintonbookingapp.dto.response.SlotResponseDTO;
import com.example.badmintonbookingapp.network.BookingOrdersService;
import com.example.badmintonbookingapp.repository.BookingRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingViewModel extends ViewModel {

    private final BookingRepository bookingRepository;
    private final MutableLiveData<List<BookingOrdersResponseDTO>> bookingOrders = new MutableLiveData<>();
    private final MutableLiveData<Boolean> bookingCreationStatus = new MutableLiveData<>();
    private final MutableLiveData<String> bookingUpdateStatus = new MutableLiveData<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();


    public BookingViewModel(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    public LiveData<List<BookingOrdersResponseDTO>> getBookingOrders() {
        return bookingOrders;
    }

    public LiveData<Boolean> getBookingCreationStatus() {
        return bookingCreationStatus;
    }

    public LiveData<String> getBookingUpdateStatus() {
        return bookingUpdateStatus;
    }



    public void fetchBookingOrdersByUserId(int userId) {
        executorService.execute(() -> {
            bookingRepository.getAllBookingOrdersByUserId(userId, new Callback<List<BookingOrdersResponseDTO>>() {
                @Override
                public void onResponse(Call<List<BookingOrdersResponseDTO>> call, Response<List<BookingOrdersResponseDTO>> response) {
                    if (response.isSuccessful()) {
                        bookingOrders.postValue(response.body());

                    } else {
                        bookingOrders.postValue(null);

                    }
                }

                @Override
                public void onFailure(Call<List<BookingOrdersResponseDTO>> call, Throwable t) {
                    bookingOrders.postValue(null);
                    t.printStackTrace();
                }
            });

        });
    }


    public void createBooking(List<BookingOrdersRequestDTO> bookingRequestDTOs) {
        executorService.execute(() -> {

            bookingRepository.createBookingOrders(bookingRequestDTOs, new Callback<List<BookingOrdersResponseDTO>>() {
                @Override
                public void onResponse(Call<List<BookingOrdersResponseDTO>> call, Response<List<BookingOrdersResponseDTO>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        bookingOrders.postValue(response.body()); // Update LiveData with the new booking
                        bookingCreationStatus.postValue(true);
                    } else {
                        bookingCreationStatus.postValue(false);

                    }
                }

                @Override
                public void onFailure(Call<List<BookingOrdersResponseDTO>> call, Throwable t) {
                    bookingCreationStatus.postValue(false);

                }
            });
        });
    }


    public void updateBookingOrderStatus(int bookingId) {
        executorService.execute(() -> {
            bookingRepository.updateBookingOrderStatus(bookingId, new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        bookingUpdateStatus.postValue(response.body());
                    } else {
                        bookingUpdateStatus.postValue("Failed to update status");
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    bookingUpdateStatus.postValue("Failed to update status");
                    t.printStackTrace();
                }
            });
        });
    }
}
