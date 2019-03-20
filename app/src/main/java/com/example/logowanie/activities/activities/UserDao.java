package com.example.logowanie.activities.activities;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.logowanie.activities.activities.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

    @Query("DELETE FROM user_table")
    void deleteAll();

    @Query("SELECT * from user_table ORDER BY id ASC")
   LiveData<List<User>> getAllUsers();

    @Query("Select * from user_table WHERE user_table.email =:wpisanyemail")
   List<User> findUser(String wpisanyemail); //ale chodzi ci o boolean

    @Query("Select * from user_table WHERE user_table.email =:wpisanyemail AND user_table.password =:wpisanepassword ")
    List<User> findUserbyEmailAndPassword(String wpisanyemail,String wpisanepassword); //ale chodzi ci o boolean

    @Query("DELETE FROM user_table WHERE Name = :name")
    void deleteUser(String name);
}
