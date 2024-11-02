package com.example.badmintonbookingapp.dto.response;

public class JwtAuthenticationResponse {
    private String token;
    private String refresh_token;

    public JwtAuthenticationResponse() {
    }

    public JwtAuthenticationResponse(String token, String refreshToken) {
        this.token = token;
        this.refresh_token = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refresh_token;
    }

    public void setRefreshToken(String refreshToken) {
        this.refresh_token = refreshToken;
    }
}
