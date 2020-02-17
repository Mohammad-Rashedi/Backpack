package com.example.backpack.View.Backpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.example.backpack.R;
import com.example.backpack.Room.BackpackTable;
import com.example.backpack.View.HomeActivity;
import com.example.backpack.ViewModel.BackpackViewModel;
//import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BackPackItemListActivity extends AppCompatActivity {

    @BindView(R.id.recyclerViewBackpackItemListListView)
    RecyclerView recyclerViewBackpackItemListListView;
    @BindView(R.id.toolbar_bp_view)
    Toolbar toolbar;
    @BindView(R.id.toolbar_bp_view_title)
    TextView textViewTitleToolbar;
    BackpackItemListAdapter myBackpackItemListAdapter;
    BackpackTable backpackTableChosed;
    int backPackId;
    List<String> contentsList;
    List<Integer> countsList;
    List<Boolean> conditionsList;
    BackpackConverter backpackConverter;
    List<String> itemsName = new ArrayList<>();
    List<Item> items = new ArrayList<>();
    List<Integer> itemsCount = new ArrayList<>();
    List<Boolean> itemsCondition = new ArrayList<>();
    String itemsCountString = "";
    List<Integer> imagesAddress = new ArrayList();
    private LayoutInflater inflater;
    private Slide enterTransition;
    private Slide exitTransition;
    private ActivityOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_pack_view);
        ButterKnife.bind(this);
        initializeTransitions();
        toolbarInitializing();


        inflater = getLayoutInflater();

        backpackConverter = new BackpackConverter();

             backPackId = getIntent().getExtras().getInt("BackPackID");
        BackpackViewModel backpackViewModel = new BackpackViewModel(getApplication());

        List<BackpackTable> backpackTables = backpackViewModel.onGetList();
        List<BackpackTable> backpackTables2 = backpackViewModel.getBackpackTableList();

        for (BackpackTable backpackTableTemp :backpackTables2        ) {
            if(backPackId == backpackTableTemp.getId()){
                backpackTableChosed = backpackTableTemp;
            }}
        String BPName = backpackTableChosed.getName();
        textViewTitleToolbar.setText(BPName);
        String counts = backpackTableChosed.getCounts();
        String conditions = backpackTableChosed.getCondition();
        String contents = backpackTableChosed.getContents();
        countsList = backpackConverter.BPCountsListToItemCountsList(counts);
        conditionsList = backpackConverter.BPConditionListToItemConditionList(conditions);
        contentsList = backpackConverter.BPNameListToItemNamesList(contents);
        String backpackTableChoseName = backpackTableChosed.getName();
        GetImagesAddress getImageAddresses = new GetImagesAddress();
        imagesAddress = getImageAddresses.getImagesAddress();

        for (int i = 0 ; i<countsList.size();i++){
            items.add(new Item(contentsList.get(i),countsList.get(i),imagesAddress.get(i),conditionsList.get(i)));

        }
        Log.i("==", "onCreate: contentsList"+ contentsList);
        Log.i("==", "onCreate: countsList"+ countsList);
        Log.i("==", "onCreate: conditionsList"+ conditionsList);
        Log.i("==", "onCreate: backpackTableChoseName"+ backpackTableChoseName);
        Log.i("==", "onCreate: items"+ items +"  "+ items.size());

        recyclerViewBackpackItemListListView.setLayoutManager(new LinearLayoutManager(this));
        myBackpackItemListAdapter = new BackpackItemListAdapter(items,this);
        recyclerViewBackpackItemListListView.setAdapter(myBackpackItemListAdapter);
        recyclerViewBackpackItemListListView.setHasFixedSize(true);
    }

    private void initializeTransitions() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            enterTransition = new Slide();
            enterTransition.setSlideEdge(Gravity.RIGHT);
            enterTransition.setDuration(500);
            enterTransition.setInterpolator(new AnticipateOvershootInterpolator());
            getWindow().setEnterTransition(enterTransition);
            getWindow().setAllowEnterTransitionOverlap(false);

            exitTransition = new Slide();
            exitTransition.setSlideEdge(Gravity.RIGHT);
            exitTransition.setDuration(500);
            getWindow().setExitTransition(exitTransition);
            getWindow().setAllowReturnTransitionOverlap(false);

        }
    }

    private void toolbarInitializing() {
        toolbar.inflateMenu(R.menu.menu_bp_view);
        toolbar.setNavigationIcon(R.drawable.back_button_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.save_bp_view:
                        ConvertItemsToBP(items);
                        break;
                }
                return true;
            }
        });
    }

    private void ConvertItemsToBP(List<Item> items) {
        List<Boolean> conditionsList = new ArrayList<>();

        for (int i = 0 ; i<items.size();i++){
            conditionsList.add(items.get(i).getCondition());
        }
        String condition = backpackConverter.ItemConditionListToBPConditionList(conditionsList);
        backpackTableChosed.setCondition(condition);
        updateBP(backpackTableChosed);
    }

    private void updateBP(BackpackTable backpackTableChosed) {
        BackpackViewModel backpackViewModel = new BackpackViewModel(getApplication());
        backpackViewModel.onUpdate(backpackTableChosed);
        Intent intent = new Intent(BackPackItemListActivity.this, BackpackListActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions.makeSceneTransitionAnimation(this);
            startActivity(intent,options.toBundle());
        }else {
            startActivity(intent);
        }
    }

    @OnClick(R.id.toolbar_bp_view_title_home)
    public void onHome(){
        Intent intent = new Intent(BackPackItemListActivity.this, HomeActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions.makeSceneTransitionAnimation(this);
            startActivity(intent,options.toBundle());

        }else {
            startActivity(intent);

        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BackPackItemListActivity.this, BackpackListActivity.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions.makeSceneTransitionAnimation(this);
            startActivity(intent,options.toBundle());
        }else {
            startActivity(intent);
        }
    }

}
