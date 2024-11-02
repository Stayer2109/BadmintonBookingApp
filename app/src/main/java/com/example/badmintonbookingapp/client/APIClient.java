package com.example.badmintonbookingapp.client;

import com.example.badmintonbookingapp.network.AuthInterceptor;
import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.utils.LocalTimeDeserializer;
import com.example.badmintonbookingapp.utils.TokenManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalTime;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    //check both ips in local and docker(linux), then config it in @xml/network_security_config
    //quang docker
    //private static String baseUrl = "http://172.30.60.233:8080";
    //quang local
    private static String baseUrl = "http://192.168.56.1:8080";
    //phong local
    //private static String baseUrl = "http://192.168.1.54:8080";

    // Get Retrofit client with AuthInterceptor added for token handling
    private static Retrofit retrofit;

    public static Retrofit getClient(TokenManager tokenManager, AuthRepository authRepository) {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalTime.class, new LocalTimeDeserializer()) // Register the LocalTime deserializer
                    .create();

            // Add AuthInterceptor to OkHttpClient
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new AuthInterceptor(tokenManager, authRepository))  // AuthInterceptor should be added here
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static <T> T getService(Class<T> serviceClass, TokenManager tokenManager, AuthRepository authRepository) {
        return getClient(tokenManager, authRepository).create(serviceClass);
    }
}
