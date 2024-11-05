    package com.example.badmintonbookingapp.ui.court_owner.court_owner_court;

    import android.app.Dialog;
    import android.util.Log;

    import androidx.lifecycle.LiveData;
    import androidx.lifecycle.MutableLiveData;
    import androidx.lifecycle.ViewModel;

    import com.example.badmintonbookingapp.dto.request.YardRequestDTO;
    import com.example.badmintonbookingapp.dto.response.YardResponseDTO;
    import com.example.badmintonbookingapp.network.ApiCallback;
    import com.example.badmintonbookingapp.repository.AuthRepository;
    import com.example.badmintonbookingapp.repository.YardRepository;
    import com.example.badmintonbookingapp.utils.TokenManager;

    import java.util.ArrayList;
    import java.util.List;

    public class CourtManagementViewModel extends ViewModel {

        private final YardRepository yardRepository;
        private final MutableLiveData<List<YardResponseDTO>> allYards;
        private final MutableLiveData<YardResponseDTO> createdYard = new MutableLiveData<>();
        private final int hostId;
        private int currentPage = 0;
        private boolean isLastPage = false;

        public CourtManagementViewModel(TokenManager tokenManager, AuthRepository authRepository, int hostId) {
            yardRepository = new YardRepository(tokenManager, authRepository);
            allYards = new MutableLiveData<>(new ArrayList<>());
            this.hostId = hostId;
            loadYardsByHostId();
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

        public void loadYardsByHostId() {
            yardRepository.fetchYardsByHostId(hostId, new ApiCallback<List<YardResponseDTO>>() {
                @Override
                public void onSuccess(List<YardResponseDTO> yards) {
                    allYards.postValue(yards);
                }

                @Override
                public void onError(Throwable error) {
                    Log.e("CourtManagementViewModel", "Error loading yards by host ID: " + error.getMessage());
                }
            });
        }

        public void createYard(YardRequestDTO yardRequestDTO) {
            yardRepository.createYard(yardRequestDTO, new ApiCallback<YardResponseDTO>() {
                @Override
                public void onSuccess(YardResponseDTO result) {
                    createdYard.postValue(result);
                    // Reset after posting, so it doesn’t trigger again
                    createdYard.postValue(null);
                }

                @Override
                public void onError(Throwable error) {
                    Log.e("CourtManagementViewModel", "Error creating yard: " + error.getMessage());
                }
            });
        }


        public LiveData<YardResponseDTO> getCreatedYard() {
            return createdYard;
        }

        public LiveData<List<YardResponseDTO>> getAllYards() {
            return allYards;
        }
    }