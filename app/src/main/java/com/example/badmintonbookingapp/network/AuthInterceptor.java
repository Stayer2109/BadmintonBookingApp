package com.example.badmintonbookingapp.network;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.utils.TokenManager;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    private TokenManager tokenManager;
    private AuthRepository authRepository;

    public AuthInterceptor(TokenManager tokenManager, AuthRepository authRepository) {
        this.tokenManager = tokenManager;
        this.authRepository = authRepository;
    }

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        String accessToken = tokenManager.getAccessToken();
        Request request = chain.request();

        if (accessToken != null) {
            request = request.newBuilder()
                    .addHeader("Authorization", "Bearer " + accessToken)
                    .build();
        }

        Response response = chain.proceed(request);

        // If token expired, attempt to refresh
        if (response.code() == 401) {
            Log.e("AuthInterceptor", "Token expired: " + response);
        } else if (response.code() == 403) {
            Log.e("AuthInterceptor", "Access forbidden: " + response);
        }
        return response;
    }
}
