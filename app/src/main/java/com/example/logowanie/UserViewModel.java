package com.example.logowanie;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.logowanie.model.User;

import java.util.List;

public class UserViewModel  extends AndroidViewModel {

    private UserRepository mRepository;

    private LiveData<List<User>> mAllUsers;
    private MutableLiveData<List<User>> searchResults;
    private MutableLiveData<List<User>> usersListbyEmailAndPassword;
    public UserViewModel (Application application) {
        super(application);
        mRepository = new UserRepository(application);
        mAllUsers = mRepository.getAllUsers();
        searchResults = mRepository.getSearchResults();
        usersListbyEmailAndPassword=mRepository.getusersListbyEmailAndPassword();

    }


  public  MutableLiveData<List<User>> getSearchResults() {
        return searchResults;
    }


    public  void findUser(String wpisanyemail){
       mRepository.findUser(wpisanyemail);
    }

    public  MutableLiveData<List<User>> getUsersListbyEmailAndPassword() {
        return usersListbyEmailAndPassword;
    }

    public void findUserbyIdAndPassword(String wpisanyemail,String wpisanepassword){

      mRepository.findUserbyIdAndPassword(wpisanyemail, wpisanepassword);
    }

  public  LiveData<List<User>> getAllUsers() { return mAllUsers; }

    public void addUser(User user) { mRepository.insert(user); }

    public void deleteUser(String name) {
        mRepository.deleteUser(name);
    }
}
