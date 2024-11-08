package com.example.badmintonbookingapp.dto;

public class ApiResponse<T> {
    private T data;
    private String additionalData;
    private String message;
    private int statusCode;
    private String code;

    public ApiResponse() {
    }

    public ApiResponse(T data, String additionalData, String message, int statusCode, String code) {
        this.data = data;
        this.additionalData = additionalData;
        this.message = message;
        this.statusCode = statusCode;
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "data=" + data +
                ", additionalData='" + additionalData + '\'' +
                ", message='" + message + '\'' +
                ", statusCode=" + statusCode +
                ", code='" + code + '\'' +
                '}';
    }
}

