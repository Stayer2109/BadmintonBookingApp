package com.example.badmintonbookingapp.ui.court_owner.court_owner_court;

import android.app.Dialog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.badmintonbookingapp.dto.request.YardRequestDTO;
import com.example.badmintonbookingapp.dto.response.YardResponseDTO;
import com.example.badmintonbookingapp.network.ApiCallback;
import com.example.badmintonbookingapp.repository.YardRepository;

import java.util.List;

public class CourtManagementViewModel extends ViewModel {

    private final YardRepository yardRepository;
    private final MutableLiveData<List<YardResponseDTO>> yardsLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private int currentPage = 1;

    // Constructor
    public CourtManagementViewModel(YardRepository yardRepository) {
        this.yardRepository = yardRepository;
    }

    // Expose LiveData for yards data
    public LiveData<List<YardResponseDTO>> getYards() {
        return yardsLiveData;
    }

    // Expose LiveData for loading state
    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    // Expose LiveData for error messages
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    // Method to load yards from the repository
    public void loadYardsByPage(int pageNumber) {
        isLoading.setValue(true); // Set loading state

        yardRepository.fetchYardsByPage(pageNumber, new ApiCallback<List<YardResponseDTO>>() {
            @Override
            public void onSuccess(List<YardResponseDTO> yards) {
                yardsLiveData.setValue(yards); // Set the yard data
                isLoading.setValue(false);     // Reset loading state
                currentPage = pageNumber;      // Update current page
            }

            @Override
            public void onError(Throwable t) {
                errorMessage.setValue("Failed to load yards: " + t.getMessage()); // Set error message
                isLoading.setValue(false); // Reset loading state
            }
        });
    }

    // Load the next page of yards
    public void loadNextPage() {
        loadYardsByPage(currentPage + 1);
    }

    // Load the previous page of yards
    public void loadPreviousPage() {
        if (currentPage > 1) {
            loadYardsByPage(currentPage - 1);
        }
    }

    public void createYard(YardRequestDTO yardRequestDTO, Dialog dialog) {
        yardRepository.createYard(yardRequestDTO, dialog);
    }
}

