package com.example.badmintonbookingapp.network;

public interface ApiCallback<T> {
    void onSuccess(T result);
    void onError(Throwable error);
}
