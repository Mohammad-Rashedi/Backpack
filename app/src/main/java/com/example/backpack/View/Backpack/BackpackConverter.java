package com.example.backpack.View.Backpack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackpackConverter {
    String BPCountsList = "";
    String BPConditionsList = "";
    String BPNamesList = "";
    List<Integer> itemCountsList = new ArrayList();
    List<Boolean> itemConditionList = new ArrayList();
    List<String> itemNameList = new ArrayList();


    public BackpackConverter() {
    }

    public String ItemCountsListToBPCountsList(List<Integer> itemCounts){
        for (int i = 0 ; i<itemCounts.size();i++){
            BPCountsList += itemCounts.get(i);
        }
        return BPCountsList;
    }

    public List<Integer> BPCountsListToItemCountsList(String BPCounts){
        String[] split = BPCounts.split("/");

        for (String s: split) {
            itemCountsList.add(Integer.valueOf(s));
        }
        return itemCountsList;
    }

public String ItemConditionListToBPConditionList(List<Boolean> itemCondition){
    for (int i = 0 ; i<itemCondition.size();i++){
        BPConditionsList += itemCondition.get(i) + "/";
    }
    return BPConditionsList;
}

    public List<Boolean> BPConditionListToItemConditionList(String BPCondition){
        String[] split = BPCondition.split("/");

        for (String s: split) {
            itemConditionList.add(Boolean.valueOf(s));
        }
        return itemConditionList;
    }

    public String ItemNamesListToBPNamesList(List<String> itemName){
        for (int i = 0 ; i<itemName.size();i++){
            BPNamesList += itemName.get(i) + "/";
        }
        return BPNamesList;

    }

    public List<String> BPNameListToItemNamesList(String BPName){
        String[] split = BPName.split("/");

        for (String s: split) {
            itemNameList.add(s);
        }
        return itemNameList;
    }

}
