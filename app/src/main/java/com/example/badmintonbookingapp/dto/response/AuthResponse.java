package com.example.badmintonbookingapp.dto.response;

public class AuthResponse {
    private int userId;
    private String username;
    private int role;
    private String token;

    public AuthResponse() {
    }

    public AuthResponse(int userId, String username, int role, String token) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
