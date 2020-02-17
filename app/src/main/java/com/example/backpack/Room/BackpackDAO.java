package com.example.backpack.Room;

//import com.google.gson.JsonObject;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BackpackDAO {

    @Query("Select * from backpack_list_table")
    List<BackpackTable> getBackpackList();

    @Insert
    long insertBackpackTable(BackpackTable backpackTable);

    @Update
    int updateBackpackTable(BackpackTable backpackTable);

    @Delete
    int deleteBackpackTable(BackpackTable backpackTable);

    @Query("SELECT * FROM backpack_list_table ORDER BY id")
     List<BackpackTable> loadAllBackpackTables();

    @Query("DELETE FROM backpack_list_table")
    void deleteAll();
}
