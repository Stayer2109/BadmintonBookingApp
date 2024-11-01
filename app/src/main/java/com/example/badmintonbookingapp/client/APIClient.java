package com.example.badmintonbookingapp.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    // quang docker, check both ips in local and docker(linux), then config it in @xml/network_security_config
    /*private static String baseUrl = "http://172.30.60.233:8080/";
    private static String baseUrl = "http://172.30.48.0:8080/";*/
    private static String baseUrl = "http://192.168.1.54:8080";
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    public static <T> T getService(Class<T> serviceClass) {
        return getClient().create(serviceClass);
    }
}
