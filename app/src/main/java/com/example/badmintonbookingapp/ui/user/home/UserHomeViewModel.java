package com.example.badmintonbookingapp.ui.user.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.badmintonbookingapp.dto.TelephonesDTO;
import com.example.badmintonbookingapp.dto.response.YardResponseDTO;
import com.example.badmintonbookingapp.network.ApiCallback;
import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.repository.YardRepository;
import com.example.badmintonbookingapp.utils.TokenManager;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserHomeViewModel extends ViewModel {
    private final YardRepository yardRepository;
    private final MutableLiveData<List<YardResponseDTO>> allYards;
    private int currentPage = 0;
    private boolean isLastPage = false;

    public UserHomeViewModel(TokenManager tokenManager, AuthRepository authRepository) {
        yardRepository = new YardRepository(tokenManager, authRepository);
        allYards = new MutableLiveData<>(new ArrayList<>());
        loadNextPage(); // Load the first page initially
    }

    public void loadNextPage() {
        if (isLastPage) return; // Stop loading if there are no more pages

        yardRepository.fetchYardsByPage(currentPage, new ApiCallback<List<YardResponseDTO>>() {
            @Override
            public void onSuccess(List<YardResponseDTO> newYards) {
                if (newYards.isEmpty()) {
                    isLastPage = true; // Mark as last page if no items are returned
                } else {
                    List<YardResponseDTO> currentYards = allYards.getValue();
                    if (currentYards == null) {
                        currentYards = new ArrayList<>();
                    }
                    currentYards.addAll(newYards);
                    allYards.postValue(currentYards);
                    currentPage++;
                }
            }

            @Override
            public void onError(Throwable error) {
                Log.e("UserHomeViewModel", "Error loading yards: " + error.getMessage());
            }
        });
    }

    public LiveData<List<YardResponseDTO>> getAllYards() {
        return allYards;
    }
}
