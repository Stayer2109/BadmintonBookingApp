package com.example.badmintonbookingapp.ui.user.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.badmintonbookingapp.dto.TelephonesDTO;
import com.example.badmintonbookingapp.dto.response.YardResponseDTO;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserHomeViewModel extends ViewModel {
    //    private final YardRepository yardRepository;
//    private final MutableLiveData<List<YardResponseDTO>> yards = new MutableLiveData<>();
//
//    public UserHomeViewModel(YardRepository yardRepository) {
//        this.yardRepository = yardRepository;
//        loadYards();
//    }
//
//    public LiveData<List<YardResponseDTO>> getYards() {
//        return yards;
//    }
//
//    private void loadYards() {
//        yardRepository.getYards(new ApiCallback<List<YardResponseDTO>>() {
//            @Override
//            public void onSuccess(List<YardResponseDTO> data) {
//                yards.postValue(data);
//            }
//
//            @Override
//            public void onError(Throwable error) {
//                // Log the error or display an error message to the user
//                error.printStackTrace();
//                // You might want to set yards to an empty list or a specific error state
//                yards.postValue(null);  // or handle with an error-specific LiveData
//            }
//        });
//    }
    private final MutableLiveData<List<YardResponseDTO>> yards = new MutableLiveData<>();

    public UserHomeViewModel() {
        loadTestYards(); // Use dummy data for testing
    }

    public LiveData<List<YardResponseDTO>> getYards() {
        return yards;
    }

    private void loadTestYards() {
        List<YardResponseDTO> testYards = new ArrayList<>();

        testYards.add(new YardResponseDTO(
                1, "Central Court", "123 Main St", 1, "Spacious court", true,
                LocalTime.of(8, 0), LocalTime.of(22, 0), LocalTime.now(), LocalTime.now(), 1, 1, 1,
                Arrays.asList(new TelephonesDTO(1, 1, "123-456-7890")), null, null
        ));

        testYards.add(new YardResponseDTO(
                2, "Greenfield Court", "456 Side Rd", 1, "Beautiful surroundings", false,
                LocalTime.of(7, 0), LocalTime.of(21, 0), LocalTime.now(), LocalTime.now(), 1, 1, 1,
                Arrays.asList(new TelephonesDTO(2, 2, "098-765-4321")), null, null
        ));

        yards.setValue(testYards);
    }
}
