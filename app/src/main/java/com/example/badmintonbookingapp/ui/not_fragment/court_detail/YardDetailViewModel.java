package com.example.badmintonbookingapp.ui.not_fragment.court_detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.badmintonbookingapp.dto.response.YardResponseDTO;
import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.repository.YardRepository;
import com.example.badmintonbookingapp.utils.TokenManager;

public class YardDetailViewModel extends ViewModel {
    private YardRepository yardRepository;

    public YardDetailViewModel(TokenManager tokenManager, AuthRepository authRepository) {
        yardRepository = new YardRepository(tokenManager, authRepository);
    }

    // Method to fetch yard detail by ID
    public void fetchYardDetail(int id) {
        yardRepository.getYardById(id);
    }

    // LiveData to observe the yard detail
    public LiveData<YardResponseDTO> getYardDetail() {
        return yardRepository.getYard();
    }
}
