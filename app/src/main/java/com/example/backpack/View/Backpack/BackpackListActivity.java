package com.example.backpack.View.Backpack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BackpackListActivity extends AppCompatActivity {
    AlertDialog.Builder builer;
    LayoutInflater inflater;
    View view;
    int dialogID;
    Intent intentGoToChooser;
    @BindView(R.id.recyclerViewBackpackList)
    RecyclerView recyclerViewBackpackList;

    @BindView(R.id.toolbar_bp_list)
    Toolbar toolbar;
    @BindView(R.id.BPListViewCoordinatorLayout)
    View viewBPListCoordinatorLayout;
    List<String> myList =new ArrayList<>();
    BackpackViewModel backpackViewModel;
    BackpackListAdapter myBackpackListAdapter;
    Slide enterTransition = null;
    Slide exitTransition = null;
    private ActivityOptions options;
    private Slide exitTransitionHome;
    int previousActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backpack_list);
        ButterKnife.bind(this);
        previousActivity = getIntent().getIntExtra("HomeActivityIntent", 0);
        initializeTransitions(previousActivity);
        toolbarInitializing();
        backpackViewModel = new BackpackViewModel(getApplication());
        List<BackpackTable> backpackTables = backpackViewModel.onGetList();
        List<BackpackTable> backpackTables2 = backpackViewModel.getBackpackTableList();
        recyclerViewBackpackList.setLayoutManager(new LinearLayoutManager(this));
        myBackpackListAdapter = new BackpackListAdapter(backpackTables2,this);
        recyclerViewBackpackList.setAdapter(myBackpackListAdapter);
        recyclerViewBackpackList.setHasFixedSize(true);
        backpackViewModel = ViewModelProviders.of(this)
                .get(BackpackViewModel.class);

        myBackpackListAdapter.setOnItemClickListener(new BackpackListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(BackpackTable backpackTable) {
                int id = backpackTable.getId();
                Log.i("==ttt", "onItemClick: "+backpackTable + id);
                Intent intent = new Intent(BackpackListActivity.this, BackPackItemListActivity.class);
                intent.putExtra("BackPackID",id );
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition(exitTransition);
                    options = ActivityOptions.makeSceneTransitionAnimation(BackpackListActivity.this);
                    startActivity(intent,options.toBundle());
                }else {
                    startActivity(intent);
                }
            }
        });

        //swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                BackpackTable bpAt = myBackpackListAdapter.getBPAt(viewHolder.getAdapterPosition());
                Log.i("==ttt", "onSwiped: "+bpAt.getName());
                String name1 = bpAt.getName();

                builer = new AlertDialog.Builder(new ContextThemeWrapper(BackpackListActivity.this,
                        R.style.MyDialogStyleDeleteBP));

                builer.setMessage("آیا "+ name1 +" پاک شود؟");
//                builer.setMessage( name1 );
                builer.setPositiveButton("بله",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        backpackViewModel.onDelete(bpAt);
                        finish();
                        startActivity(getIntent());
                    }
                });
                builer.setNegativeButton("خیر",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                   myBackpackListAdapter.notifyDataSetChanged();
                    }
                });
                builer.create();
                builer.show();

            }
        }).attachToRecyclerView(recyclerViewBackpackList);
    }

    private void initializeTransitions(int previousActivity) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            enterTransition = new Slide();
            enterTransition.setSlideEdge(Gravity.LEFT);
            enterTransition.setDuration(500);
            if (previousActivity==1){
                enterTransition.setSlideEdge(Gravity.RIGHT);
                previousActivity = 0;
            }
            enterTransition.setInterpolator(new AnticipateOvershootInterpolator());
            getWindow().setEnterTransition(enterTransition);
            getWindow().setAllowEnterTransitionOverlap(false);
            getWindow().setReturnTransition(enterTransition);

            exitTransition = new Slide();
            exitTransition.setSlideEdge(Gravity.LEFT);
            exitTransition.setDuration(500);

            exitTransitionHome = new Slide();
            exitTransitionHome.setSlideEdge(Gravity.RIGHT);
            exitTransitionHome.setDuration(500);

            getWindow().setAllowReturnTransitionOverlap(false);

        }
    }

    private void toolbarInitializing() {
        toolbar.inflateMenu(R.menu.menu_bp_list_activity);
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
                    case R.id.clearAll:
                        Toast toast = Toast.makeText(getApplicationContext(), "تمام کوله پشتی ها پاک شد",
                                Toast.LENGTH_LONG);
                        View view = toast.getView();
                        toast.show();

                    backpackViewModel.onDeleteAll();
                        startActivity(getIntent());
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bp_list_activity, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle Item selection
        switch (item.getItemId()) {
            case R.id.clearAll:
                Log.i("==", "onOptionsItemSelected: clear");
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.fab_add_bp)
    public void onFabAddBP(){
        builer = new AlertDialog.Builder(this);
        dialogID = R.layout.new_backpacklist_dialog;
        inflater = getLayoutInflater();
        view = inflater.inflate(dialogID, null);

        EditText editTextBackpackListName = view.findViewById(R.id.editTextBackpacklistName);
        builer.setView(view).setPositiveButton("تایید", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String BackpackListName = editTextBackpackListName.getText().toString();
                if (BackpackListName.matches("^ +.*") || BackpackListName.matches("")){
                    Toast.makeText(getApplicationContext(),"لطفا یک نام وارد کنید...",Toast.LENGTH_LONG).show();

                }else {
                    intentGoToChooser = new Intent(BackpackListActivity.this, BackpackItemChooserActivity.class);
                    intentGoToChooser.putExtra("BackPackName", BackpackListName);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setExitTransition(exitTransition);
                        options = ActivityOptions.makeSceneTransitionAnimation(BackpackListActivity.this);
                        startActivity(intentGoToChooser,options.toBundle());
                    }else {
                        startActivity(intentGoToChooser);
                    }
                }
            }
        });
        builer.setView(view).setNegativeButton("لغو", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builer.create();
        builer.show();

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BackpackListActivity.this, HomeActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(exitTransitionHome);
            options = ActivityOptions.makeSceneTransitionAnimation(this);
            startActivity(intent,options.toBundle());
        }else {
            startActivity(intent);

        }
    }

}
