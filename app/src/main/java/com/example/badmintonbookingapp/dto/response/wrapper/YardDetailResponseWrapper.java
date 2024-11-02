package com.example.badmintonbookingapp.dto.response.wrapper;

import com.example.badmintonbookingapp.dto.response.YardResponseDTO;

public class YardDetailResponseWrapper {
    public YardResponseDTO data;
    public String status;
    public String message;

    public YardResponseDTO getData() {
        return data;
    }

    public void setData(YardResponseDTO data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
