package com.example.backpack.Room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = BackpackTable.class,exportSchema = false,version = 3)
public abstract class BackpackDatabase extends RoomDatabase {

    private static final String DB_NAME = "BackpackList_db";
    private static BackpackDatabase instance;

    public static synchronized BackpackDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BackpackDatabase.class, DB_NAME).
                    fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract BackpackDAO backpackDAO();


}
