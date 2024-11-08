package com.example.badmintonbookingapp.ui.user.booking;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.badmintonbookingapp.dto.ApiResponse;
import com.example.badmintonbookingapp.dto.request.BookingOrdersRequestDTO;
import com.example.badmintonbookingapp.dto.response.BookingOrdersResponseDTO;
import com.example.badmintonbookingapp.dto.response.SlotResponseDTO;
import com.example.badmintonbookingapp.network.ApiCallback;
import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.repository.BookingRepository;
import com.example.badmintonbookingapp.repository.SlotRepository;
import com.example.badmintonbookingapp.utils.TokenManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingViewModel extends ViewModel {

    private static final String TAG = "BookingViewModel";

    private final SlotRepository slotRepository;
    private final BookingRepository bookingRepository;
    private final MutableLiveData<List<SlotResponseDTO>> slots = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<List<BookingOrdersResponseDTO>> orders = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Boolean> bookingCreationStatus = new MutableLiveData<>();
    private final MutableLiveData<List<BookingOrdersResponseDTO>> booksLiveData = new MutableLiveData<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public BookingViewModel(TokenManager tokenManager, AuthRepository authRepository, BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
        this.slotRepository = new SlotRepository(tokenManager, authRepository);
    }

    public void createBooking(int yardId, List<Integer> slotIds, int userId) {
        executorService.execute(() -> {
            // Create a BookingOrdersRequestDTO object with a list of slotIds
            BookingOrdersRequestDTO bookingRequest = new BookingOrdersRequestDTO(userId, slotIds, getCurrentDate());

            // Send the BookingOrdersRequestDTO to the API
            bookingRepository.createBookingOrders(bookingRequest, new Callback<ApiResponse<BookingOrdersResponseDTO>>() {
                @Override
                public void onResponse(Call<ApiResponse<BookingOrdersResponseDTO>> call, Response<ApiResponse<BookingOrdersResponseDTO>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        bookingCreationStatus.postValue(true);
                        Log.d(TAG, "Booking created successfully. Response: " + response.body().getData());
                    } else {
                        bookingCreationStatus.postValue(false);
                        Log.e(TAG, "Failed to create booking. Error code: " + response.code() + ", Message: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse<BookingOrdersResponseDTO>> call, Throwable throwable) {
                    bookingCreationStatus.postValue(false);
                    Log.e(TAG, "Error creating booking", throwable);
                }
            });
        });
    }

    public void fetchBooksByUserId(int userId, ApiCallback<List<BookingOrdersResponseDTO>> callback) {
        // Gọi phương thức getAllBookingOrdersByUserId() với 2 đối số: userId và callback
        bookingRepository.getAllBookingOrdersByUserId(userId, new Callback<ApiResponse<List<BookingOrdersResponseDTO>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<BookingOrdersResponseDTO>>> call, Response<ApiResponse<List<BookingOrdersResponseDTO>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<BookingOrdersResponseDTO> data = response.body().getData();
                    callback.onSuccess(data);
                    Log.d("OrderViewModel", "Fetched booking orders: " + data.size() + " items.");
                } else {
                    String errorMessage = response.message() != null ? response.message() : "Unknown error";
                    callback.onError(new Throwable("Failed to fetch booking orders: " + errorMessage));
                    Log.e("OrderViewModel", "Failed to fetch booking orders: " + errorMessage);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<BookingOrdersResponseDTO>>> call, Throwable t) {
                callback.onError(t);
                Log.e("OrderViewModel", "Error fetching booking orders", t);
            }
        });
    }

    public void fetchSlotsByYardId(int yardId) {
        slotRepository.fetchSlotsByYardId(yardId, new ApiCallback<List<SlotResponseDTO>>() {
            @Override
            public void onSuccess(List<SlotResponseDTO> result) {
                slots.postValue(result);
                Log.d(TAG, "Fetched slots for yard ID " + yardId + ": " + result.size());
            }

            @Override
            public void onError(Throwable error) {
                Log.e(TAG, "Error fetching slots: " + error.getMessage());
            }
        });
    }

    public LiveData<List<BookingOrdersResponseDTO>> getOrders() {
        return orders;
    }

    public LiveData<List<SlotResponseDTO>> getSlots() {
        return slots;
    }

    public LiveData<Boolean> getBookingCreationStatus() {
        return bookingCreationStatus;
    }

    public LiveData<List<BookingOrdersResponseDTO>> getBooksLiveData() {
        return booksLiveData;
    }

    private String getCurrentDate() {
        // Cách lấy ngày hiện tại theo định dạng mong muốn
        return java.time.LocalDate.now().toString(); // Định dạng yyyy-MM-dd
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        executorService.shutdown();
    }
}
