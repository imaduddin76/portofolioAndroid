package com.limaefdua.bootcamp.room.dao;
//
// Created by maftuhin on 10/23/2019.
//

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.limaefdua.bootcamp.room.table.User;

import retrofit2.http.DELETE;

@Dao
public interface UserDao {
    @Insert
    long register(User user);

    @Query("SELECT * from user where username=:username AND password=:password LIMIT 1")
    User userLogin(String username, String password);
}
