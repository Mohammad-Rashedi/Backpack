package com.example.backpack.myDatabse;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "backpack_list_table")
public class BackpackTable {

    public void setCounts(String counts) {
        this.counts = counts;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "BackpackName-column")
    private String name;
    @ColumnInfo(name = "Contents_column")
    private String contents;
    @ColumnInfo(name = "Counts_column")
    private String counts;
    @ColumnInfo(name = "Contents_condition_column")
    private String condition;

    public BackpackTable(int id, String name , String contents , String counts ,String condition){
        this.id = id;
        this.name = name;
        this.contents = contents;
        this.counts = counts;
        this.condition = condition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getCounts() {
        return counts;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Ignore
    public BackpackTable( String name , String contents , String counts ,String condition){
        this.id = id;
        this.name = name;
        this.contents = contents;
        this.counts = counts;
        this.condition = condition;
    }
}
