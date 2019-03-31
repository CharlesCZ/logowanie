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
    private MutableLiveData<List<User>> usersListbyEmailAndPassword;
    private UserRoomDatabase mDb;


    public UserViewModel (Application application) {
        super(application);
        mRepository = new UserRepository(application);
        mAllUsers = mRepository.getAllUsers();
        usersListbyEmailAndPassword=mRepository.getusersListbyEmailAndPassword();
      createDb();;

    }

    public void createDb(){

        mDb=UserRoomDatabase.getFileDatabase(this.getApplication());

    }
    public LiveData<List<User>> findUserByEmail(String email){
        return mDb.userDao().findUserByEmail(email);

    }

public  LiveData<List<User>> findUserByEmailAndPassword(String wpisanyemail,String wpisanepassword){

        return mDb.userDao().findUserByEmailAndPassword(wpisanyemail,wpisanepassword);
}




    public  MutableLiveData<List<User>> getUsersListbyEmailAndPassword() {
        return usersListbyEmailAndPassword;
    }




    public void findUserbyIdAndPassword(String wpisanyemail,String wpisanepassword){

      mRepository.findUserbyIdAndPassword(wpisanyemail, wpisanepassword);
    }

  public  LiveData<List<User>> getAllUsers() { return mAllUsers; }

    public void addUser(User user) { mRepository.insert(user); }


}
