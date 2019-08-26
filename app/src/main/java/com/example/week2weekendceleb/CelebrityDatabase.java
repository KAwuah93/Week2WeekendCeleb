package com.example.week2weekendceleb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Celebrity.class, version = 1)
public abstract class CelebrityDatabase extends RoomDatabase {

    //instance of our database - issa singleton
    private static CelebrityDatabase instance;

    public abstract CelebDao celebDao();

    public static synchronized CelebrityDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
            CelebrityDatabase.class, "celebrity_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    };
}
