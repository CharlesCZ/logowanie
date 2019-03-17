package com.example.logowanie.activities.activities;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.logowanie.activities.activities.model.User;

import java.util.List;

public class UserViewModel  extends AndroidViewModel {

    private UserRepository mRepository;

    private LiveData<List<User>> mAllUsers;

    public UserViewModel (Application application) {
        super(application);
        mRepository = new UserRepository(application);
        mAllUsers = mRepository.getAllUsers();
    }

    public  boolean checkUser(String wpisanyemail){

        if(mRepository.checkUser(wpisanyemail)!=null)
            return true;
        else return false;

    }

    public boolean checkUser(String wpisanyemail,String wpisanepassword){

        if(mRepository.checkUser(wpisanyemail,wpisanepassword)!=null)
            return true;
        else return false;
    }

    LiveData<List<User>> getAllUSers() { return mAllUsers; }

    public void insert(User user) { mRepository.insert(user); }
}
