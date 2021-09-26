package com.example.demomessageapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient retrofitInstance;
    private Retrofit retrofit;


    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://rajalakshmisrinivasan.freshhr.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitClient getInstance() {
        if (retrofitInstance == null) {
            retrofitInstance = new RetrofitClient();
        }
        return retrofitInstance;
    }

    public FreshApi getApi() {
        return retrofit.create(FreshApi.class);
    }
}