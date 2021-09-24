package com.example.demomessageapp;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public interface PersonRepository {
   public MutableLiveData<List<User>> getLiveData();
   public  void  loadPersons();
}
