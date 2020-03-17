package com.limaefdua.bootcamp.room.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//
// Created by maftuhin on 10/23/2019.
//
@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "email")
    public String email;
}
