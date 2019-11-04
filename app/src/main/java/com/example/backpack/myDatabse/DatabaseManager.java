package com.example.backpack.myDatabse;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

//import com.google.gson.Gson;
//import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DatabaseManager extends AppCompatActivity {
    private static Context mContext;
    BackpackDatabase backpackDB;
    String onGetListString;
    Context context;
    List<BackpackTable> BackPackList = new ArrayList<>();
    List<BackpackTable> BackPackList2 ;
    Boolean isReady = false;
    public DatabaseManager(Context context){
        this.context = context;
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
                String result = "";
                for (BackpackTable backpackTable :BackPackList
                ) {
                    result += " -- " +backpackTable.getId() +" "+ backpackTable.getName() +" "+ backpackTable.getCounts() + "\n";
                }
                getBackpackTableListFromThread(BackPackList);
            }
        }).start();
        Thread.interrupted();
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

    public void onUpdate(BackpackTable backpackTable) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                backpackDB = BackpackDatabase.getInstance(context);
//                Person person0 = new Person(3,"Reza", "C");
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
                Log.i("==",backpackDB.toString());
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
