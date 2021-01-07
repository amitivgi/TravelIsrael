package com.example.proj.RoomPackage;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataFavorites.class}, version = 1, exportSchema = false)
public abstract class DataRoomDatabaseFavorites extends RoomDatabase {

    public abstract DataDaoFavorites dataDao();

    private static volatile DataRoomDatabaseFavorites INSTANCE;

    public static DataRoomDatabaseFavorites getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DataRoomDatabaseFavorites.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DataRoomDatabaseFavorites.class, "data_table_favorites")
                            .allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }

}
