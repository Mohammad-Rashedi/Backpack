package com.example.backpack;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.backpack.BackpackView.BackPackViewActivity;
import com.example.backpack.myDatabse.BackpackTable;
import com.example.backpack.myDatabse.DatabaseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class BackpackItemChooserActivity extends AppCompatActivity {
    @BindView(R.id.textViewBackpackName)
    TextView textViewBackpackName;
    @BindView(R.id.textViewTemp)
    TextView textViewTemp;
    @BindView(R.id.chooserLinearLayout)
    LinearLayout chooserLinearLayout;
    String backpackName;
    Intent intent;
    AlertDialog.Builder builer;
    LayoutInflater inflater;
    String tempList;
    List<String> itemsName = new ArrayList<>();
    List<Integer> itemsCount = new ArrayList<>();
    List<Boolean> itemsCondition = new ArrayList<>();
    View view;
    DatabaseManager databaseManager;
    int dialogID;
    int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backpack_item_chooser);
        ButterKnife.bind(this);
        intent = getIntent();
            if((intent.getExtras().getString("BackPackName")) != null) {
            backpackName = getIntent().getExtras().getString("BackPackName");
            tempList = "";
            textViewBackpackName.setText(backpackName);
            } else {
            Toast.makeText(getApplicationContext(), "null!", Toast.LENGTH_LONG).show();
        }
                //initializing
                initialNames();
                initialCount();
                initialCondition();
                showTempBox();
    }

    @Override
    public void onBackPressed() {
        builer = new AlertDialog.Builder(this);
        builer.setMessage("آیا مطمین هستید به عقب برگردید؟");
        builer.setPositiveButton("بله",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            finish();
            }
        });
        builer.setNegativeButton("خیر",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builer.create();
        builer.show();
    }

    @OnClick(R.id.buttonCancel)
    public void onCancel() {
        Intent intent = new Intent(BackpackItemChooserActivity.this, BackpackListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.buttonOk)
    public void onOk() {
        BackpackTable backpackTable = new BackpackTable(backpackName, itemsName.toString(),
                                        itemsCount.toString(), itemsCondition.toString());
        databaseManager = new DatabaseManager(this);
        databaseManager.onInsert(backpackTable);
        Intent intent = new Intent(BackpackItemChooserActivity.this, BackpackListActivity.class);
        startActivity(intent);

    }

    @OnLongClick({R.id.imageButtonBasement, R.id.imageButtonBlanket, R.id.imageButtonBrush,
            R.id.imageButtonCharger, R.id.imageButtonClothes, R.id.imageButtonDrug, R.id.imageButtonFireLighter,
            R.id.imageButtonFlashlight, R.id.imageButtonHeadphones, R.id.imageButtonPortablecharger,
            R.id.imageButtonMatches, R.id.imageButtonOther, R.id.imageButtonSlippers,
            R.id.imageButtonShaverMachin, R.id.imageButtonSunGlasses, R.id.imageButtonToothpaste,
            R.id.imageButtonTowel, R.id.imageButtonWarmClothes})

    public void onItemLongClicked(View view) {
        itemId = view.getId();
        switch (itemId) {
            case R.id.imageButtonBasement:
                setCountToZero(16);
                break;
            case R.id.imageButtonBlanket:
                setCountToZero(15);
                break;
            case R.id.imageButtonBrush:
                setCountToZero(3);
                break;
            case R.id.imageButtonCharger:
                setCountToZero(1);
                break;
            case R.id.imageButtonClothes:
                setCountToZero(6);
                break;
            case R.id.imageButtonDrug:
                setCountToZero(11);
                break;
            case R.id.imageButtonFireLighter:
                setCountToZero(13);
                break;
            case R.id.imageButtonFlashlight:
                setCountToZero(12);
                break;
            case R.id.imageButtonHeadphones:
                setCountToZero(0);
                break;
            case R.id.imageButtonPortablecharger:
                setCountToZero(2);
                break;
            case R.id.imageButtonMatches:
                setCountToZero(14);
                break;
            case R.id.imageButtonSlippers:
                setCountToZero(9);
                break;
            case R.id.imageButtonShaverMachin:
                setCountToZero(5);
                break;
            case R.id.imageButtonSunGlasses:
                setCountToZero(10);
                break;
            case R.id.imageButtonToothpaste:
                setCountToZero(4);
                break;
            case R.id.imageButtonTowel:
                setCountToZero(8);
                break;
            case R.id.imageButtonWarmClothes:
                setCountToZero(7);
                break;
            case R.id.imageButtonOther:
                break;
        }
    }

    @OnClick({R.id.imageButtonBasement, R.id.imageButtonBlanket, R.id.imageButtonBrush,
            R.id.imageButtonCharger, R.id.imageButtonClothes, R.id.imageButtonDrug, R.id.imageButtonFireLighter,
            R.id.imageButtonFlashlight, R.id.imageButtonHeadphones, R.id.imageButtonPortablecharger,
            R.id.imageButtonMatches, R.id.imageButtonOther, R.id.imageButtonSlippers,
            R.id.imageButtonShaverMachin, R.id.imageButtonSunGlasses, R.id.imageButtonToothpaste,
            R.id.imageButtonTowel, R.id.imageButtonWarmClothes})
    public void onItemClicked(View view) {
        itemId = view.getId();
        switch (itemId) {
            case R.id.imageButtonBasement:
                setCount(16);
                break;
            case R.id.imageButtonBlanket:
                setCount(15);
                break;
            case R.id.imageButtonBrush:
                setCount(3);
                break;
            case R.id.imageButtonCharger:
                setCount(1);
                break;
            case R.id.imageButtonClothes:
                setCount(6);
                break;
            case R.id.imageButtonDrug:
                setCount(11);
                break;
            case R.id.imageButtonFireLighter:
                setCount(13);
                break;
            case R.id.imageButtonFlashlight:
                setCount(12);
                break;
            case R.id.imageButtonHeadphones:
                setCount(0);
                break;
            case R.id.imageButtonPortablecharger:
                setCount(2);
                break;
            case R.id.imageButtonMatches:
                setCount(14);
                break;
            case R.id.imageButtonSlippers:
                setCount(9);
                break;
            case R.id.imageButtonShaverMachin:
                setCount(5);
                break;
            case R.id.imageButtonSunGlasses:
                setCount(10);
                break;
            case R.id.imageButtonToothpaste:
                setCount(4);
                break;
            case R.id.imageButtonTowel:
                setCount(8);
                break;
            case R.id.imageButtonWarmClothes:
                setCount(7);
                break;
            case R.id.imageButtonOther:
                throwItemDialog();
                break;
        }
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
                Toast.makeText(getApplicationContext(), itemCountString + " عدد " + itemName +
                                                    " به لیست اضافه شد", Toast.LENGTH_LONG).show();
                newItemAdd(itemName, newItemCount);
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

    private void showTempBox() {
        tempList = "";
        for (int i = 0; i < itemsName.size(); i++) {
            tempList += itemsName.get(i) + "\n" + itemsCount.get(i) + " عدد" + "\n" + "\n";
        }
        textViewTemp.setText(tempList);
        Log.i("==", tempList + "");

    }

    private void initialCount() {
        for (int i = 0; i < itemsName.size(); i++) {
            itemsCount.add(0);
        }
    }

    private void initialNames() {
        itemsName.clear();
        itemsName.add("هندزفری");
        itemsName.add("شارژر");
        itemsName.add("پاور بانک");
        itemsName.add("مسواک");
        itemsName.add("خمیرد ندان");
        itemsName.add("ریش تراش");
        itemsName.add("لباس");
        itemsName.add("لباس گرم");
        itemsName.add("حوله");
        itemsName.add("دمپایی");
        itemsName.add("عینک آفتابی");
        itemsName.add("دارو");
        itemsName.add("چراغ قوه");
        itemsName.add("فندک");
        itemsName.add("کبریت");
        itemsName.add("پتو");
        itemsName.add("زیرانداز");
    }

    private void initialCondition() {
        for (int i = 0; i < itemsName.size(); i++) {
            itemsCondition.add(false);
        }
    }

    private void setCount(int itemNumner) {
        itemsCount.set(itemNumner, itemsCount.get(itemNumner) + 1);
        showTempBox();
        Log.i("==", itemsCount + "");
    }

    private void setCountToZero(int itemNumner) {
        itemsCount.set(itemNumner, 0);

        showTempBox();
        Log.i("==", itemsCount + "");
    }

    private void newItemAdd(String name, int count) {
        itemsName.add(name);
        itemsCount.add(count);
        itemsCondition.add(false);
        showTempBox();
    }
}
