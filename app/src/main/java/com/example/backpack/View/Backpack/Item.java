package com.example.backpack.View.Backpack;

import android.widget.ImageButton;
import android.widget.ImageView;

public class Item {

    String itemName;
    int itemCount;
    int itemImage;
    Boolean condition;

    public Item(String itemName, int itemCount, int itemImage,Boolean condition) {
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.itemImage = itemImage;
        this.condition = condition;
    }
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }

    public Boolean getCondition() {
        return condition;
    }

    public void setCondition(Boolean condition) {
        this.condition = condition;
    }


}
