package com.example.badmintonbookingapp.dto.response.wrapper;

import com.example.badmintonbookingapp.dto.response.UserResponseDTO;

public class UserResponseWrapper {
    public UserResponseDTO data;
    public String status;
    public String message;

    public UserResponseDTO getData() {
        return data;
    }

    public void setData(UserResponseDTO data) {
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
