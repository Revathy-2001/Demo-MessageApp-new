package com.example.demomessageapp;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataPersonRepository implements PersonRepository {

    MutableLiveData<List<User>> personsLiveData = new MutableLiveData<List<User>>();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://rajalakshmisrinivasan.freshhr.com")
            .addConverterFactory(GsonConverterFactory.create()).build();
    FreshApi freshApi = retrofit.create(FreshApi.class);
    Call<Users> call = freshApi.getUsers();


    public void loadPersons() {
        call.enqueue(new Callback<Users>() {

            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (!response.isSuccessful()) {
                    String result = "Code: " + response.code();
                    return;
                }
                personsLiveData.postValue(response.body().getUsers());
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                String result = t.getMessage();
                Log.e("Error",result);
            }

        });

    }

    @Override
    public MutableLiveData<List<User>> getLiveData() {
        return personsLiveData;
    }


}
