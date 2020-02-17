package com.example.backpack.Repository;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

//import com.google.gson.Gson;
//import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.backpack.Room.BackpackDatabase;
import com.example.backpack.Room.BackpackTable;

public class DatabaseManager extends AppCompatActivity {
    BackpackDatabase backpackDB;
    Context context;
    List<BackpackTable> BackPackList;
    List<BackpackTable> BackPackList2 ;
    Boolean isReady = false;
    public DatabaseManager(Application application){
        context = application.getApplicationContext();
        backpackDB = BackpackDatabase.getInstance(context);

    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        backpackDB = BackpackDatabase.getInstance(context);
    }

    public List<BackpackTable> onGetList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                backpackDB = BackpackDatabase.getInstance(context);
                BackPackList = backpackDB.backpackDAO().getBackpackList();
                getBackpackTableListFromThread(BackPackList);
            }
        }).start();
        Thread.interrupted();

        if (BackPackList == null) {
        }
        return BackPackList;
    }

    public void onInsert(BackpackTable backpackTable) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                backpackDB = BackpackDatabase.getInstance(context);
                backpackDB.backpackDAO().insertBackpackTable(backpackTable);
            }
        }).start();
        Thread.interrupted();
    }

    public void onDelete(BackpackTable backpackTable) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                backpackDB = BackpackDatabase.getInstance(context);
                backpackDB.backpackDAO().deleteBackpackTable(backpackTable);
            }
        }).start();
        Thread.interrupted();
    }
    public void onDeleteAll() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                backpackDB = BackpackDatabase.getInstance(context);
                backpackDB.backpackDAO().deleteAll();
            }
        }).start();
        Thread.interrupted();
    }

    public void onUpdate(BackpackTable backpackTable) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                backpackDB = BackpackDatabase.getInstance(context);
                backpackDB.backpackDAO().updateBackpackTable(backpackTable);
            }
        }).start();
        Thread.interrupted();
    }

    public BackpackDatabase onGetAll() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                backpackDB = BackpackDatabase.getInstance(context);
            }
        }).start();
        Thread.interrupted();
        return backpackDB;
    }

public void getBackpackTableListFromThread(List<BackpackTable> backpackTableList){
    BackPackList2 = backpackTableList;
    isReady = true;
}
   public List<BackpackTable> getBackpackTableList(){
        while(!isReady){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            Thread.interrupted();
        }
       return BackPackList2;
   }

}
