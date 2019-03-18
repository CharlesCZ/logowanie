package com.example.logowanie.activities.activities;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.logowanie.activities.activities.UserDao;
import com.example.logowanie.activities.activities.UserRoomDatabase;
import com.example.logowanie.activities.activities.model.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepository {

    private UserDao mUserDao;
    private LiveData<List<User>>  mAllUsers;

     UserRepository(Application application) {
        UserRoomDatabase db=UserRoomDatabase.getDatabase(application);
        mUserDao= db.userDao();
       mAllUsers = mUserDao.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }





    public LiveData<User> checkUser(String wpisanyemail){

        return mUserDao.checkUser(wpisanyemail);
    }

   public LiveData<User>  checkUser(String wpisanyemail,String wpisanepassword){

        return mUserDao.checkUser(wpisanyemail,wpisanepassword);
    }

    public void insert (User user) {
        new insertAsyncTask(mUserDao).execute(user);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
