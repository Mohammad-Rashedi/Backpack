package com.example.backpack.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.backpack.Repository.DatabaseManager;
import com.example.backpack.Repository.WebServiceManager;
import com.example.backpack.Room.BackpackDatabase;
import com.example.backpack.Room.BackpackTable;
import com.example.backpack.WebService.WeatherGsonClasses.WeatherFromWeb;

import java.util.List;

public class BackpackViewModel extends AndroidViewModel {
    DatabaseManager databaseManager;
    List<BackpackTable> BackPackList;
    BackpackDatabase backpackDB;
    WebServiceManager webServiceManager;
    public BackpackViewModel(@NonNull Application application) {
        super(application);
        databaseManager = new DatabaseManager(application);
        webServiceManager = new WebServiceManager();
    }

    public List<BackpackTable>  onGetList() {
        BackPackList = databaseManager.onGetList();
        if (BackPackList == null) {
            Log.i("==", "BackpackViewModel: on get lisy BackPackList is null");
        }
        return BackPackList;
    }
    public void onInsert(BackpackTable backpackTable) {
        databaseManager.onInsert(backpackTable);
    }
    public void onDelete(BackpackTable backpackTable) {
        databaseManager.onDelete(backpackTable);
    }
    public void onDeleteAll() {
        databaseManager.onDeleteAll();
    }
    public void onUpdate(BackpackTable backpackTable) {
        databaseManager.onUpdate(backpackTable);
    }
    public List<BackpackTable> getBackpackTableList(){
        BackPackList = databaseManager.getBackpackTableList();
        return BackPackList;
    }
    public BackpackDatabase onGetAll() {
        backpackDB = databaseManager.onGetAll();
        return backpackDB;
    }

    public WeatherFromWeb getWeatherFromWeb(String cityName){
        WeatherFromWeb weatherFromWeb = webServiceManager.getWeatherFromWeb(cityName);
        Log.i("==", "getWeatherFromWeb: "+weatherFromWeb);
        return weatherFromWeb;
    }

}
