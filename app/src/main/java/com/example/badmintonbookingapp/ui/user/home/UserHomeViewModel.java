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
    //    private final MutableLiveData<List<YardResponseDTO>> yards = new MutableLiveData<>();
//
//    public UserHomeViewModel() {
//        loadTestYards(); // Use dummy data for testing
//    }
//
//    public LiveData<List<YardResponseDTO>> getYards() {
//        return yards;
//    }
//
//    private void loadTestYards() {
//        List<YardResponseDTO> testYards = new ArrayList<>();
//
//        testYards.add(new YardResponseDTO(
//                1, "Central Court", "123 Main St", 1, "Spacious court", true,
//                LocalTime.of(8, 0), LocalTime.of(22, 0), LocalTime.now(), LocalTime.now(), 1, 1, 1,
//                Arrays.asList(new TelephonesDTO(1, 1, "123-456-7890")), null, null
//        ));
//
//        testYards.add(new YardResponseDTO(
//                2, "Greenfield Court", "456 Side Rd", 1, "Beautiful surroundings", false,
//                LocalTime.of(7, 0), LocalTime.of(21, 0), LocalTime.now(), LocalTime.now(), 1, 1, 1,
//                Arrays.asList(new TelephonesDTO(2, 2, "098-765-4321")), null, null
//        ));
//
//        yards.setValue(testYards);
//    }
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
