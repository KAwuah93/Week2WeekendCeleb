package com.example.week2weekendceleb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CelebDao {

    @Insert
    void insert(Celebrity celebrity);

    @Update
    void update(Celebrity celebrity);

    @Delete
    void delete(Celebrity celebrity);

    //Custom Queries are created with the @Query() method

    @Query("SELECT * from celebrity_table")
    LiveData<List<Celebrity>> selectAllCelebs();

}
