package com.example.badmintonbookingapp.dto;

public class ApiResponse<T> {
    private Integer status;
    private Integer code;
    private String message;
    private T data;
    private String error;

    // Getters and Setters
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public Integer getCode() { return code; }
    public void setCode(Integer code) { this.code = code; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}

