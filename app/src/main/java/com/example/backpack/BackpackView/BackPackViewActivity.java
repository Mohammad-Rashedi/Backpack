package com.example.backpack.BackpackView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.backpack.BackpackItemChooserActivity;
import com.example.backpack.BackpackListActivity;
import com.example.backpack.BackpackListActivityNames.BackpackListNamesAdapter;
import com.example.backpack.HomeActivity;
import com.example.backpack.R;
import com.example.backpack.myDatabse.BackpackTable;
import com.example.backpack.myDatabse.DatabaseManager;
//import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BackPackViewActivity extends AppCompatActivity {
    @BindView(R.id.myListView)
    ListView myListView1;
    @BindView(R.id.textViewBackpackNameView)
    TextView textViewBackpackNameView;
    BackpackListAdapter myBackpackListAdapter;
    BackpackTable backpackTableChosed;
    int backPackId;
    String result;
    List<String> contentsList;
    List<String> countsList;
    List<String> ContentsCountsConditionsList;
    List<String> conditionsList;
    List<BackpackTable> backpackTables;
    List<BackpackTable> backpackTables2;
    DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_pack_view);
        ButterKnife.bind(this);
        backPackId = getIntent().getExtras().getInt("BackPackID");
        databaseManager = new DatabaseManager(this);
        backpackTables = databaseManager.onGetList();
        backpackTables2 = databaseManager.getBackpackTableList();
        result = "";
        for (BackpackTable backpackTableTemp :backpackTables2        ) {
            if(backPackId == backpackTableTemp.getId()){
                backpackTableChosed = backpackTableTemp;
            }
            result += " -- " + backpackTableTemp.getId() +" "+ backpackTableTemp.getName()
                            +" "+ backpackTableTemp.getContents()
                            +" "+ backpackTableTemp.getCounts() + "\n";
        }
        textViewBackpackNameView.setText(backpackTableChosed.getName());
        String contents = backpackTableChosed.getContents();
        String counts = backpackTableChosed.getCounts();
        String conditions = backpackTableChosed.getCondition();
        contentsList = Arrays.asList(contents.split("\\s*,\\s*"));
        countsList = Arrays.asList(counts.split("\\s*,\\s*"));
        conditionsList = Arrays.asList(conditions.split("\\s*,\\s*"));
        ContentsCountsConditionsList = new ArrayList<>();
        for(int j = 0 ; j< contentsList.size();j++){
            ContentsCountsConditionsList.add(contentsList.get(j) + ":" + countsList.get(j)+ ":" + conditionsList.get(j));
        }
        myBackpackListAdapter = new BackpackListAdapter(this, ContentsCountsConditionsList);
        myListView1.setAdapter(myBackpackListAdapter);
        myListView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textViewCondition = view.findViewById(R.id.textViewCondition);
                TextView textViewName = view.findViewById(R.id.textViewName);
                TextView textViewNumber = view.findViewById(R.id.textViewNumber);
                if(conditionsList.get(position).equals("true")){
                    conditionsList.set(position, String.valueOf(false));
                    textViewCondition.setBackgroundColor(Color.WHITE);
                    textViewName.setBackgroundColor(Color.WHITE);
                    textViewNumber.setBackgroundColor(Color.WHITE);
                }else{
                    conditionsList.set(position, String.valueOf(true));
                    textViewCondition.setBackgroundColor(Color.GREEN);
                    textViewName.setBackgroundColor(Color.GREEN);
                    textViewNumber.setBackgroundColor(Color.GREEN);
                }
                if(conditionsList.get(position).equals("true")){
                    textViewCondition.setText("  " + "دارم " + "  ");
                }else{
                    textViewCondition.setText("  " + "ندارم" + "  ");
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BackPackViewActivity.this, BackpackListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.buttonOkInBPView)
    public void onButtonOkInBPView(View view){
        DatabaseManager databaseManager = new DatabaseManager(getApplicationContext());
        for (int i =0 ; i<contentsList.size() ; i++){
            if (contentsList.get(i).charAt(0) == '[') {
                contentsList.set(i, contentsList.get(i).substring(1, contentsList.get(i).length()));
            }else if (contentsList.get(i).charAt(contentsList.get(i).length()-1)==']'){
                contentsList.set(i, contentsList.get(i).substring(0, contentsList.get(i).length()-1));
            }
            if (countsList.get(i).charAt(0) == '[') {
                countsList.set(i, countsList.get(i).substring(1, countsList.get(i).length()));
            }else if (countsList.get(i).charAt(countsList.get(i).length()-1)==']'){
                countsList.set(i, countsList.get(i).substring(0, countsList.get(i).length()-1));
            }
            if (conditionsList.get(i).charAt(0) == '[') {
                conditionsList.set(i, conditionsList.get(i).substring(1, conditionsList.get(i).length()));
            }else if (conditionsList.get(i).charAt(conditionsList.get(i).length()-1)==']'){
                conditionsList.set(i, conditionsList.get(i).substring(0, conditionsList.get(i).length()-1));
            }
        }

        BackpackTable backpackTable = new BackpackTable(backPackId, backpackTableChosed.getName(),
                contentsList.toString(),
                countsList.toString(),conditionsList.toString());
        databaseManager.onUpdate(backpackTable);
        Intent intent = new Intent(BackPackViewActivity.this, BackpackListActivity.class);
        startActivity(intent);
    }
}
