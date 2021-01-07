package com.example.proj.RoomPackage;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface DataDaoFavorites {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DataFavorites dataFavorites);

    @Delete
    void delete(DataFavorites dataFavorites);

    @Query("DELETE FROM data_table_favorites")
    void deleteAll();

    @Query("SELECT * from data_table_favorites")
    LiveData<List<DataFavorites>> getAllDataFavorites();
}