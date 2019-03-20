package com.example.logowanie.activities.activities;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.example.logowanie.activities.activities.UserDao;
import com.example.logowanie.activities.activities.UserRoomDatabase;
import com.example.logowanie.activities.activities.model.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepository {

    private UserDao mUserDao;
    private LiveData<List<User>>  mAllUsers;
    private MutableLiveData<List<User>> searchResults =
            new MutableLiveData<>();

     UserRepository(Application application) {
        UserRoomDatabase db=UserRoomDatabase.getDatabase(application);
        mUserDao= db.userDao();
       mAllUsers = mUserDao.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }





 //   public List<User> checkUser(String wpisanyemail){

    //    return mUserDao.findUser(wpisanyemail);
   // }

    public MutableLiveData<List<User>> getSearchResults() {
        return searchResults;
    }

    public void findUser(String name) {
        QueryAsyncTask task = new QueryAsyncTask(mUserDao);
        task.delegate = this;
        task.execute(name);
    }



    private void asyncFinished(List<User> results) {
        searchResults.setValue(results);
    }
    private static class QueryAsyncTask extends
            AsyncTask<String, Void, List<User>> {

        private UserDao asyncTaskDao;
        private UserRepository delegate = null;

        QueryAsyncTask(UserDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected List<User> doInBackground(final String... params) {
            return asyncTaskDao.findUser(params[0]);
        }

        @Override
        protected void onPostExecute(List<User> result) {
            delegate.asyncFinished(result);
        }
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
