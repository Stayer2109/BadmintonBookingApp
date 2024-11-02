package com.example.badmintonbookingapp.dto.response.wrapper;

import com.example.badmintonbookingapp.dto.response.YardResponseDTO;

import java.util.List;

public class YardResponseWrapper {
    private List<YardResponseDTO> data;
    private String status;
    private String message;

    // Getters and setters
    public List<YardResponseDTO> getData() {
        return data;
    }

    public void setData(List<YardResponseDTO> data) {
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
