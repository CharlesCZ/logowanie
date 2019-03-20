package com.example.logowanie.activities.activities;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.logowanie.activities.activities.model.User;

import java.util.List;

public class UserViewModel  extends AndroidViewModel {

    private UserRepository mRepository;

    private LiveData<List<User>> mAllUsers;
    private MutableLiveData<List<User>> searchResults;

    public UserViewModel (Application application) {
        super(application);
        mRepository = new UserRepository(application);
        mAllUsers = mRepository.getAllUsers();
        searchResults = mRepository.getSearchResults();

    }


  public  MutableLiveData<List<User>> getSearchResults() {
        return searchResults;
    }


    public  void findUser(String wpisanyemail){
       mRepository.findUser(wpisanyemail);
    }

    public boolean checkUser(String wpisanyemail,String wpisanepassword){

        if(mRepository.checkUser(wpisanyemail,wpisanepassword).getValue()!=null)
        {    System.out.println(mRepository.checkUser(wpisanyemail,wpisanepassword).getValue().getEmail()+" "+ true);
            return true;}

        else return false;
    }

  public  LiveData<List<User>> getAllUsers() { return mAllUsers; }

    public void addUser(User user) { mRepository.insert(user); }

    public void deleteUser(String name) {
        mRepository.deleteUser(name);
    }
}
