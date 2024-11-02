package com.example.badmintonbookingapp.client;

import com.example.badmintonbookingapp.network.AuthInterceptor;
import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.utils.TokenManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    // quang docker, check both ips in local and docker(linux), then config it in @xml/network_security_config
    /*private static String baseUrl = "http://172.30.60.233:8080/";
    private static String baseUrl = "http://172.30.48.0:8080/";*/
    private static String baseUrl = "http://192.168.1.54:8080/api/v1/";
    private static Retrofit retrofit;

    public static Retrofit getClient(TokenManager tokenManager, AuthRepository authRepository) {
        if (retrofit == null) {
            // Add AuthInterceptor to OkHttpClient
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new AuthInterceptor(tokenManager, authRepository))  // AuthInterceptor should be added here
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static <T> T getService(Class<T> serviceClass, TokenManager tokenManager, AuthRepository authRepository) {
        return getClient(tokenManager, authRepository).create(serviceClass);
    }
}
