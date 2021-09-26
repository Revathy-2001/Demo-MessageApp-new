package com.example.demomessageapp;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class UserDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer,User>> userLiveDataSource = new MutableLiveData<>();
    @Override
    public DataSource create() {
        UserDataSource userDataSource = new UserDataSource();
        userLiveDataSource.postValue(userDataSource);
        return userDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, User>> getUserLiveDataSource() {
        return userLiveDataSource;
    }
}
