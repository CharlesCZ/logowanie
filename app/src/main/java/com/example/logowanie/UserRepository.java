package com.example.logowanie;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.example.logowanie.model.User;

import java.util.List;

public class UserRepository {

    private UserDao mUserDao;
    private LiveData<List<User>>  mAllUsers;


     UserRepository(Application application) {
        UserRoomDatabase db=UserRoomDatabase.getFileDatabase(application);
        mUserDao= db.userDao();
       mAllUsers = mUserDao.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return mAllUsers;
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


    public void deleteUser(String name) {
        DeleteAsyncTask task = new DeleteAsyncTask(mUserDao);
        task.execute(name);
    }

    private static class DeleteAsyncTask extends AsyncTask<String, Void, Void> {

        private UserDao asyncTaskDao;

        DeleteAsyncTask(UserDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            asyncTaskDao.deleteUser(params[0]);
            return null;
        }
    }
}
