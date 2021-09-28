package com.example.demomessageapp;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import java.security.PublicKey;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDataSource extends PageKeyedDataSource<Integer,User> {
    public static final int PAGE = 1;
    public static final int PER_PAGE = 10;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, User> callback) {
       RetrofitClient
               .getInstance()
               .getApi()
               .getUsers(PAGE,PER_PAGE)
               .enqueue(new Callback<Users>() {
                   @Override
                   public void onResponse(Call<Users> call, Response<Users> response) {
                       callback.onResult(response.body().getUsers(),null,PAGE + 1);
                   }
                   @Override
                   public void onFailure(Call<Users> call, Throwable t) {
                      String error_msg = t.getMessage();
                       Log.e("Error",error_msg);
                   }
               });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, User> callback) {
         RetrofitClient
                 .getInstance()
                 .getApi()
                 .getUsers(params.key,PER_PAGE)
                 .enqueue(new Callback<Users>() {
                     @Override
                     public void onResponse(Call<Users> call, Response<Users> response) {
                        if(response.body() != null) {
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body().getUsers(),key);
                        }
                     }

                     @Override
                     public void onFailure(Call<Users> call, Throwable t) {
                         String error_msg = t.getMessage();
                         Log.e("Error",error_msg);
                     }
                 });

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, User> callback) {

        RetrofitClient
                .getInstance()
                .getApi()
                .getUsers(params.key,PER_PAGE)
                .enqueue(new Callback<Users>() {
                    @Override
                    public void onResponse(Call<Users> call, Response<Users> response) {

                        if(response.body() != null) {
                            Integer key = (params.key < response.body().getMeta().getTotalPages()) ? params.key + 1 : null;
                            callback.onResult(response.body().getUsers(),key);
                        }

                    }

                    @Override
                    public void onFailure(Call<Users> call, Throwable t) {
                        String error_msg = t.getMessage();
                        Log.e("Error",error_msg);
                    }
                });
    }
}
