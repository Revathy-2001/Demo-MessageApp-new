package com.example.demomessageapp;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataPersonRepository implements PersonRepository {

    MutableLiveData<List<User>> personsLiveData = new MutableLiveData<List<User>>();

    Call<Users> call = RetrofitClient
                       .getInstance()
                       .getApi()
                       .getUsers(1,10);


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
