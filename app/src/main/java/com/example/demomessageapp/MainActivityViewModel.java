package com.example.demomessageapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demomessageapp.databinding.RowItemBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityViewModel extends ViewModel {
      public LiveData<PagedList<User>> userPagedList;
      public LiveData<PageKeyedDataSource<Integer,User>> userDataSource;

      public MainActivityViewModel(){
          UserDataSourceFactory userDataSourceFactory = new UserDataSourceFactory();
          userDataSource = userDataSourceFactory.getUserLiveDataSource();
          PagedList.Config config =
                  (new PagedList.Config.Builder())
                  .setEnablePlaceholders(false)
                  .setPageSize(UserDataSource.PER_PAGE)
                  .build();
          userPagedList = (new LivePagedListBuilder(userDataSourceFactory,config)).build();
      }

//
//      MutableLiveData<List<User>> mutableLiveData;
//
//
//      PersonRepository dataPersonRepository = new DataPersonRepository();
//      public MainActivityViewModel(){
//          dataPersonRepository.loadPersons();
//          mutableLiveData = dataPersonRepository.getLiveData();
//    }
}
