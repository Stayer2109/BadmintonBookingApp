package com.example.badmintonbookingapp.ui.user.home;

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
    private YardRepository yardRepository;
    private LiveData<List<YardResponseDTO>> allYards;

    public UserHomeViewModel(TokenManager tokenManager, AuthRepository authRepository) {
        yardRepository = new YardRepository(tokenManager, authRepository);  // Pass dependencies
        allYards = yardRepository.getYards();
        yardRepository.fetchAllYards(); // Trigger the data fetch
    }

    public LiveData<List<YardResponseDTO>> getAllYards() {
        return allYards;
    }
}
