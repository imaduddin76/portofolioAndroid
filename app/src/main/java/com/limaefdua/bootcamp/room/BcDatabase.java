package com.limaefdua.bootcamp.room;
//
// Created by maftuhin on 10/23/2019.
//

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.limaefdua.bootcamp.room.dao.UserDao;
import com.limaefdua.bootcamp.room.table.User;

@Database(entities = {User.class}, version = 1)
public abstract class BcDatabase extends RoomDatabase {
    private static BcDatabase INSTANCE;

    public abstract UserDao userDao();

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };

    public static BcDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context,
                    BcDatabase.class, "bc-room")
                    .addMigrations(MIGRATION_1_2)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
