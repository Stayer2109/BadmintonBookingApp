package com.example.badmintonbookingapp.utils;// utils/TokenManager.java
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

public class TokenManager {
    private static final String PREF_NAME = "badminton_app_prefs";
    private static final String KEY_ACCESS_TOKEN = null;
    private static final String KEY_REFRESH_TOKEN = null;

    private static TokenManager instance;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    // Private constructor to prevent direct instantiation
    public TokenManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    // Singleton pattern to get the single instance of TokenManager
    public static synchronized TokenManager getInstance(Context context) {
        if (instance == null) {
            instance = new TokenManager(context);
        }
        return instance;
    }

    public void saveTokens(String accessToken, String refreshToken) {
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.putString(KEY_REFRESH_TOKEN, refreshToken);
        editor.apply();
    }

    public String getAccessToken() {
        String accessToken = prefs.getString(KEY_ACCESS_TOKEN, null);
        return accessToken;
    }

    public String getRole() {
        String accessToken = getAccessToken();
        if (accessToken == null) return null;

        try {
            // Split the JWT into its parts and decode the payload
            String[] parts = accessToken.split("\\.");
            if (parts.length < 2) return null;

            // Decode payload (second part of JWT) from Base64
            String payload = new String(Base64.decode(parts[1], Base64.URL_SAFE));
            JSONObject jsonObject = new JSONObject(payload);

            // Extract "role" field from JSON payload
            return jsonObject.optString("role", null);
        } catch (JSONException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer getId() {
        String accessToken = getAccessToken();
        if (accessToken == null) return null;

        try {
            String[] parts = accessToken.split("\\.");
            if (parts.length < 2) return null;

            // Decode payload
            String payload = new String(Base64.decode(parts[1], Base64.URL_SAFE));
            JSONObject jsonObject = new JSONObject(payload);

            // Extract "id" filed from JSON payload
            return jsonObject.optInt("id", -1);
        } catch (JSONException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getRefreshToken() {
        return prefs.getString(KEY_REFRESH_TOKEN, null);
    }

    public boolean IsExpired (){
        String accessToken = getAccessToken();
        if (accessToken == null) return true;

        try {
            // Split the JWT into its parts and decode the payload
            String[] parts = accessToken.split("\\.");
            if (parts.length < 2) return true;

            // Decode payload (second part of JWT) from Base64
            String payload = new String(Base64.decode(parts[1], Base64.URL_SAFE));
            JSONObject jsonObject = new JSONObject(payload);

            // Extract "exp" field from JSON payload
            long exp = jsonObject.optLong("exp", 0);
            long now = System.currentTimeMillis() / 1000;
            return now >= exp;
        } catch (JSONException | IllegalArgumentException e) {
            e.printStackTrace();
            return true;
        }
    }

    public String getUsername() {
        String accessToken = getAccessToken();
        if (accessToken == null) return null;

        try {
            // Split the JWT into its parts and decode the payload
            String[] parts = accessToken.split("\\.");
            if (parts.length < 2) return null;

            // Decode payload (second part of JWT) from Base64
            String payload = new String(Base64.decode(parts[1], Base64.URL_SAFE));
            JSONObject jsonObject = new JSONObject(payload);

            // Extract "username" field from JSON payload
            return jsonObject.optString("sub", null);
        } catch (JSONException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void clearTokens() {
        editor.remove(KEY_ACCESS_TOKEN);
        editor.remove(KEY_REFRESH_TOKEN);
        editor.apply();
    }
}
