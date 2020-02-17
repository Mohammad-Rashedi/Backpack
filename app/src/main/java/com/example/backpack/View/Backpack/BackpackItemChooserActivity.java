package com.example.backpack.View.Backpack;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.ActivityOptions;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.backpack.R;
import com.example.backpack.Room.BackpackTable;
import com.example.backpack.View.HomeActivity;
import com.example.backpack.ViewModel.BackpackViewModel;

import java.util.ArrayList;
import java.util.List;

public class BackpackItemChooserActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_bp_item_title)
    TextView textViewTitleToolbar;
    @BindView(R.id.toolbar_bp_item_chooser2)
    Toolbar toolbar;
    String backpackName;
    Intent intent;
    AlertDialog.Builder builer;
    LayoutInflater inflater;
    String tempList;
    List<String> itemsName = new ArrayList<>();
    List<Item> items = new ArrayList<>();
    @BindView(R.id.recyclerViewBackpackItemChooser)
    RecyclerView recyclerViewBackpackItemList;
    BackpackItemChooserAdapter myBackpackItemChooserAdapter;
    List<Integer> itemsCount = new ArrayList<>();
    List<Boolean> itemsCondition = new ArrayList<>();
    String itemsCountString = "";
    int value;
    int dialogID;
    View view;
    private Slide enterTransition;
    private Slide exitTransition;
    private ActivityOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backpack_item_chooser);
        ButterKnife.bind(this);
        initializeTransitions();
        toolbarInitializing();
        itemsInitializing();
        inflater = getLayoutInflater();
        recyclerViewInitializing();
        intent = getIntent();
        if ((intent.getExtras().getString("BackPackName")) != null) {
            backpackName = getIntent().getExtras().getString("BackPackName");
            textViewTitleToolbar.setText(backpackName);

        } else {
        }

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

    private void recyclerViewInitializing() {
        recyclerViewBackpackItemList.setLayoutManager(new LinearLayoutManager(this));
        myBackpackItemChooserAdapter = new BackpackItemChooserAdapter(items,this);
        recyclerViewBackpackItemList.setAdapter(myBackpackItemChooserAdapter);
        recyclerViewBackpackItemList.setHasFixedSize(true);
        myBackpackItemChooserAdapter.setOnItemClickListener(new BackpackItemChooserAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Item item) {
            }
        });

    }

    private void itemsInitializing() {
//        items.clear();
        Item item1 = new Item("هندزفری", 0, R.drawable.headphones,false);
        Item item2 = new Item("شارژر", 0, R.drawable.charger,false);
        Item item3 = new Item("پاور بانک", 0, R.drawable.portablecharger,false);
        Item item4 = new Item("مسواک", 0, R.drawable.brush,false);
        Item item5 = new Item("خمیرد ندان", 0, R.drawable.toothpaste,false);
        Item item6 = new Item("ریش تراش", 0, R.drawable.shavermachin,false);
        Item item7 = new Item("لباس", 0, R.drawable.clothes,false);
        Item item8 = new Item("لباس گرم", 0, R.drawable.warmclothes,false);
        Item item9 = new Item("حوله", 0, R.drawable.towel,false);
        Item item10 = new Item("دمپایی", 0, R.drawable.slippers,false);
        Item item11 = new Item("عینک آفتابی", 0, R.drawable.sunglasses,false);
        Item item12 = new Item("دارو", 0, R.drawable.drug,false);
        Item item13 = new Item("چراغ قوه", 0, R.drawable.flashlight,false);
        Item item14 = new Item("فندک", 0, R.drawable.firelighter,false);
        Item item15 = new Item("کبریت", 0, R.drawable.matches,false);
        Item item16 = new Item("پتو", 0, R.drawable.blanket,false);
        Item item17 = new Item("زیرانداز", 0, R.drawable.basement,false);
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);
        items.add(item7);
        items.add(item8);
        items.add(item9);
        items.add(item10);
        items.add(item11);
        items.add(item12);
        items.add(item13);
        items.add(item14);
        items.add(item15);
        items.add(item16);
        items.add(item17);
    }

    private void toolbarInitializing() {
        toolbar.inflateMenu(R.menu.menu_bp_item_chooser);
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
                switch (id) {
                    case R.id.clearAll:
                        Toast.makeText(getApplicationContext(), "تمام آیتم ها پاک شد", Toast.LENGTH_LONG).show();
                        Log.i("==", "onOptionsItemSelected: clear");
                        itemsResetCount();
                        recyclerViewInitializing();
                        break;
                    case R.id.deleteBPChooser:


                        Intent intent = new Intent(BackpackItemChooserActivity.this, BackpackListActivity.class);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            options = ActivityOptions.makeSceneTransitionAnimation(BackpackItemChooserActivity.this);

                            startActivity(intent,options.toBundle());

                        }else {
                            startActivity(intent);

                        }
                        break;
                    case R.id.saveBPChooser:
                        for (int i = 0 ; i<items.size();i++){
                            Item item1 = items.get(i);
                            item1.getItemCount();
                            itemsCountString += (item1.getItemCount() + "/");
                        }


                for (int i =0 ;i<items.size();i++ ){
                    itemsCondition.add(false);
                    itemsName.add(items.get(i).getItemName());}
                        BackpackConverter backpackConverter = new BackpackConverter();
                        String s = backpackConverter.ItemConditionListToBPConditionList(itemsCondition);
                        String s2 = backpackConverter.ItemNamesListToBPNamesList(itemsName);
                        Log.i("==", "onMenuItemClick: "+itemsName.size());
                        Log.i("==", "onMenuItemClick: "+itemsCondition.size());
                        BackpackTable backpackTable = new BackpackTable(backpackName, s2,
                                itemsCountString, s);
                        BackpackViewModel backpackViewModel = new BackpackViewModel(getApplication());
                        backpackViewModel.onInsert(backpackTable);

                        intent = new Intent(BackpackItemChooserActivity.this, BackpackListActivity.class);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            options = ActivityOptions.makeSceneTransitionAnimation(BackpackItemChooserActivity.this);
                            startActivity(intent,options.toBundle());
                        }else {
                            startActivity(intent);

                        }
                        Log.i("==", "onOptionsItemSelected: save");
                        break;
                }

                return true;
            }
        });

    }

    private void itemsResetCount() {
        for (int i =0 ; i< items.size(); i ++){
            items.get(i).setItemCount(0);
        }
    }

    @OnClick(R.id.toolbar_bp_item_title_home)
    public void onHome() {
        Intent intent = new Intent(BackpackItemChooserActivity.this, HomeActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions.makeSceneTransitionAnimation(this);
            startActivity(intent,options.toBundle());
        }else {
            startActivity(intent);

        }
    }

    @Override
    public void onBackPressed() {

        builer = new AlertDialog.Builder(new ContextThemeWrapper(this,
                R.style.MyDialogStyleDeleteBP));
        builer.setMessage("آیا مطمین هستید به عقب برگردید؟");
        builer.setPositiveButton("بله", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(BackpackItemChooserActivity.this, BackpackListActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    options = ActivityOptions.makeSceneTransitionAnimation(BackpackItemChooserActivity.this);
                    startActivity(intent,options.toBundle());
                }else {
                    startActivity(intent);

                }
            }
        });
        builer.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builer.create();
        builer.show();
    }

    private void throwItemDialog() {
        builer = new AlertDialog.Builder(this);
        dialogID = R.layout.new_item_dialog;
        inflater = getLayoutInflater();
        view = inflater.inflate(dialogID, null);
        EditText editTextItemName = view.findViewById(R.id.editTextItemName);
        EditText editTextItemCount = view.findViewById(R.id.editTextItemCount);
        builer.setView(view).setPositiveButton("تایید", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String itemName = editTextItemName.getText().toString();
                String itemCountString = editTextItemCount.getText().toString();
                int newItemCount = Integer.parseInt(itemCountString);
                Toast.makeText(getApplicationContext(), itemCountString + " عدد " + itemName + " به لیست اضافه شد", Toast.LENGTH_LONG).show();

            }
        });
        builer.setView(view).setNegativeButton("لغو", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "cancel clicked!", Toast.LENGTH_LONG).show();
            }
        });

        builer.create();
        builer.show();

    }


}
