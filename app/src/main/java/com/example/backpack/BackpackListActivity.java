package com.example.backpack;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.backpack.BackpackListActivityNames.BackpackListNamesAdapter;
import com.example.backpack.BackpackView.BackPackViewActivity;
import com.example.backpack.myDatabse.BackpackTable;
import com.example.backpack.myDatabse.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BackpackListActivity extends AppCompatActivity {
    AlertDialog.Builder builder;
    LayoutInflater inflater;
    View view;
    int dialogID;
    Intent intentGoToChooser;
    @BindView(R.id.listviewBackpackList)
    ListView myListView;
    List<String> myList =new ArrayList<>();
    DatabaseManager databaseManager;
    BackpackListNamesAdapter myBackpackListNamesAdapter;
    String result;
    String name;
    List<BackpackTable> backpackTables;
    List<BackpackTable> backpackTables2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backpack_list);
        ButterKnife.bind(this);
        databaseManager = new DatabaseManager(this);
        //these two work together
        backpackTables = databaseManager.onGetList();
        backpackTables2 = databaseManager.getBackpackTableList();
        result = "";
        name = "";
        for (BackpackTable backpackTableTemp :backpackTables2        ) {
            name = backpackTableTemp.getName()+"";
            myList.add(name);
        }
        myBackpackListNamesAdapter = new BackpackListNamesAdapter(this, backpackTables2);
        myListView.setAdapter(myBackpackListNamesAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BackpackTable backpackTable = backpackTables2.get(position);
                Intent intent = new Intent(BackpackListActivity.this, BackPackViewActivity.class);
                int idPut = backpackTable.getId();
                intent.putExtra("BackPackID",idPut );
                startActivity(intent);
            }
        });

        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String name = myList.get(position);
                builder = new AlertDialog.Builder(BackpackListActivity.this);
                builder.setMessage("آیا "+ name +" پاک شود؟");
                BackpackTable backpackTable = backpackTables2.get(position);
                databaseManager = new DatabaseManager(getApplicationContext());
                databaseManager.onDelete(backpackTable);
                builder.setPositiveButton("بله",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseManager.onDelete(backpackTable);
                        finish();
                        startActivity(getIntent());
                    }
                });
                builder.setNegativeButton("خیر",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create();
                builder.show();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BackpackListActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @OnClick (R.id.imageButtonAddBackpackList)
    public void onImageButtonAddBackpackList(View view){
        builder = new AlertDialog.Builder(this);
        dialogID = R.layout.new_backpacklist_dialog;
        inflater = getLayoutInflater();
        view = inflater.inflate(dialogID, null);
        EditText editTextBackpackListName = view.findViewById(R.id.editTextBackpacklistName);
        builder.setView(view).setPositiveButton("تایید", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String BackpackListName = editTextBackpackListName.getText().toString();
//                Toast.makeText(getApplicationContext(),BackpackListName,Toast.LENGTH_LONG).show();
                if (BackpackListName.matches("^ +.*") || BackpackListName.matches("")){
                    Toast.makeText(getApplicationContext(),"لطفا یک نام وارد کنید...",Toast.LENGTH_LONG).show();
                }else {
                    intentGoToChooser = new Intent(BackpackListActivity.this, BackpackItemChooserActivity.class);
                    intentGoToChooser.putExtra("BackPackName", BackpackListName);
                    startActivity(intentGoToChooser);
                }
            }
        });
        builder.setView(view).setNegativeButton("لغو", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create();
        builder.show();
    }
}
