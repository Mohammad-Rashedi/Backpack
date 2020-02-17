package com.example.backpack.View.Weather;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.backpack.R;
import com.example.backpack.Repository.CityNamesRepository;
import com.example.backpack.ViewModel.BackpackViewModel;
import com.example.backpack.WebService.WeatherGsonClasses.WeatherFromWeb;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeatherHomeActivity extends AppCompatActivity {
    BackpackViewModel backpackViewModel;
    NetworkInfo info;
    NetworkInfo[] infoAll;
    List<WeatherItem> itemsIran = new ArrayList();
    List<WeatherItem> itemsWorld = new ArrayList();
    List<WeatherItem> itemsCurrent = new ArrayList();
    List<WeatherItem> itemsWorldOffline = new ArrayList();
    List<WeatherItem> itemsIranOffline = new ArrayList();
    List<WeatherFromWeb> weathersFromWeb = new ArrayList<>();
    WeatherAdapter weatherAdapter;
    @BindView(R.id.toolbar_weather_home)
    Toolbar toolbar;
    @BindView(R.id.recyclerViewWeatherHome)
    RecyclerView recyclerViewWeatherHome;
    CityNamesRepository cityNamesRepository;
    ArrayList<String> cityNamesWorldEnglishSolid;
    ArrayList<String> cityNamesIranEnglishSolid;
    ArrayList<String> cityNamesIranPersian;
    ArrayList<String> cityNamesWorldPersian;
    Boolean recyclerViewInitialized = false;
    Boolean isConnected = false;
    @BindView(R.id.imageButtonWorldWHome)
    ImageButton imageButtonWorld;
    @BindView(R.id.imageButtonIranWHome)
    ImageButton imageButtonIran;
    @BindView(R.id.WeatherHomeCoordinatorLayout)
    View viewWeatherHomeCoordinatorLayout;
    boolean imageButtonWorldClickable = false;
    boolean imageButtonIranClickable = false;
    Boolean worldButtonActivated = false;
    Boolean iranButtonActivated = false;
    private Boolean refreshButtonClickable = false;
    private Fade enterTransition;
    private Fade exitTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_home);
        ButterKnife.bind(this);
        initializeTransitions();
        backpackViewModel = new BackpackViewModel(this.getApplication());
        cityNamesRepository = new CityNamesRepository();
        toolbarInitializing();
        initializeItems();
        imageButtonIranClickable(true);
        imageButtonWorldClickable(true);
        refreshButtonClickable(true);

    }

    private void initializeTransitions() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            enterTransition = new Fade();
            enterTransition.setDuration(500);
            enterTransition.setInterpolator(new AnticipateOvershootInterpolator());
            getWindow().setEnterTransition(enterTransition);
            getWindow().setAllowEnterTransitionOverlap(false);

            exitTransition = new Fade();
            exitTransition.setInterpolator(new AnticipateOvershootInterpolator());
            exitTransition.setDuration(500);
            getWindow().setExitTransition(exitTransition);
            getWindow().setAllowReturnTransitionOverlap(false);

        }
    }

    private void refreshWeather() {
        if (getConnection()) {
            if (iranButtonActivated && refreshButtonClickable){
                throwWaitingSnackbar();
                Log.i("==", "refreshWeather: iran");
                new getWeathersIranFromWebAsync().execute();
                imageButtonWorld.setBackgroundDrawable(ContextCompat.getDrawable(this,
                        R.drawable.world_map_deactivated));
                imageButtonIran.setBackgroundDrawable(ContextCompat.getDrawable(this,
                        R.drawable.iran_map_activated));
            }else if (worldButtonActivated && refreshButtonClickable){
                throwWaitingSnackbar();
                Log.i("==", "refreshWeather: world");
                imageButtonWorld.setBackgroundDrawable(ContextCompat.getDrawable(this,
                        R.drawable.world_map_activated));
                imageButtonIran.setBackgroundDrawable(ContextCompat.getDrawable(this,
                        R.drawable.iran_map_deactivated));
                new getWeathersWorldFromWebAsync().execute();

            }else if (!iranButtonActivated && !worldButtonActivated){
                Log.i("==", "refreshWeather: iran2222");
                throwWaitingSnackbar();
                iranButtonActivated = true;
                imageButtonWorld.setBackgroundDrawable(ContextCompat.getDrawable(this,
                        R.drawable.world_map_deactivated));
                imageButtonIran.setBackgroundDrawable(ContextCompat.getDrawable(this,
                        R.drawable.iran_map_activated));
                new getWeathersIranFromWebAsync().execute();
            }

        }else {
            Toast.makeText(this, "به اینترنت دسترسی ندارید", Toast.LENGTH_LONG).show();

        }
    }

    private void getIranWeather() {
        if (getConnection()) {
            imageButtonWorldBackgroundActivated(false);
            imageButtonIranBackgroundActivated(true);
            imageButtonIranClickable(false);
            imageButtonWorldClickable(false);
            refreshButtonClickable(false);
            throwWaitingSnackbar();
        new getWeathersIranFromWebAsync().execute();}
    }

    private void getWorldWeather() {
        if (getConnection()) {
            imageButtonIranClickable(false);
            imageButtonWorldClickable(false);
            refreshButtonClickable(false);
            imageButtonIranBackgroundActivated(false);
            imageButtonWorldBackgroundActivated(true);
            throwWaitingSnackbar();
        new getWeathersWorldFromWebAsync().execute();}

    }

    @OnClick(R.id.imageButtonIranWHome)
    public void onImageButtonIranWHome(View view) {

        if (imageButtonIranClickable) {
            if (!iranButtonActivated) {
                if (itemsIran.isEmpty()) {
                    getIranWeather();
                } else {
                    imageButtonIranBackgroundActivated(true);
                    imageButtonWorldBackgroundActivated(false);
                    initializeRecyclerView(itemsIranOffline);
                }
                worldButtonActivated(false);
                iranButtonActivated(true);
            }
        }
    }

    @OnClick(R.id.imageButtonWorldWHome)
    public void onImageButtonWorldWHome(View view) {

        if (imageButtonWorldClickable) {
            if (!worldButtonActivated) {
                if (itemsWorld.isEmpty()) {
                    getWorldWeather();
                } else {
                    imageButtonIranBackgroundActivated(false);
                    imageButtonWorldBackgroundActivated(true);
                    initializeRecyclerView(itemsWorldOffline);
                }
                worldButtonActivated(true);
                iranButtonActivated(false);
            }
        }
    }




    private class getWeathersIranFromWebAsync extends AsyncTask<Void,Void,List<WeatherItem>>{
        @Override
        protected List<WeatherItem> doInBackground(Void... voids) {
            itemsIran.clear();
            for (int i = 0 ; i<cityNamesIranEnglishSolid.size();i++){
                WeatherFromWeb weatherFromWeb = backpackViewModel.getWeatherFromWeb(cityNamesIranEnglishSolid.get(i));
                int temperatureInt = (int) (((weatherFromWeb.getMain().getTemp()) - 273));
                String description = weatherFromWeb.getWeather().get(0).getDescription();
                itemsIran.add(new WeatherItem(cityNamesIranPersian.get(i),temperatureInt+"",description));
            }
            return itemsIran;
        }

        @Override
        protected void onPostExecute(List<WeatherItem> items) {
            super.onPostExecute(items);
            imageButtonWorldClickable(true);
            refreshButtonClickable(true);
            itemsIranOffline = items;
            initializeRecyclerView(items);
        }
    }

    private class getWeathersWorldFromWebAsync extends AsyncTask<Void,Void,List<WeatherItem>>{
        @Override
        protected List<WeatherItem> doInBackground(Void... voids) {
            itemsWorld.clear();
            for (int i = 0 ; i<cityNamesWorldEnglishSolid.size();i++){
                WeatherFromWeb weatherFromWeb = backpackViewModel.getWeatherFromWeb(cityNamesWorldEnglishSolid.get(i));
                int temperatureInt = (int) (((weatherFromWeb.getMain().getTemp()) - 273));
                String description = weatherFromWeb.getWeather().get(0).getDescription();
                itemsWorld.add(new WeatherItem(cityNamesWorldPersian.get(i),temperatureInt+"",description));
            }
            return itemsWorld;
        }
        @Override
        protected void onPostExecute(List<WeatherItem> items) {
            super.onPostExecute(items);
            imageButtonIranClickable(true);
            refreshButtonClickable(true);
            itemsWorldOffline = items;
            initializeRecyclerView(items);
        }
    }

    private Boolean getConnection() {
        isConnected = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            if (connectivityManager == null) {
                Toast.makeText(this, "به اینترنت دسترسی ندارید", Toast.LENGTH_LONG).show();
                imageButtonIranClickable = true;
                imageButtonWorldClickable(true);

            } else {
                info = connectivityManager.getActiveNetworkInfo();
            }
            if (info == null) {
                Toast.makeText(this, "به اینترنت دسترسی ندارید", Toast.LENGTH_LONG).show();
                imageButtonIranClickable = true;
                imageButtonWorldClickable(true);

            } else if (!(info.isConnected()) | !(info.isAvailable())) {
                Toast.makeText(this, "به اینترنت دسترسی ندارید", Toast.LENGTH_LONG).show();
                imageButtonIranClickable = true;
                imageButtonWorldClickable(true);
            }
            else {
                isConnected = true;}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isConnected;
    }

    private void throwWaitingSnackbar() {
        Snackbar snackbar = Snackbar.make(viewWeatherHomeCoordinatorLayout, "در حال دریافت اطلاعات... لطفا منتظر بمانبد.",
                Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        int snackbarTextId = com.google.android.material.R.id.snackbar_text;
        TextView textView = (TextView)snackbarView.findViewById(snackbarTextId);
        textView.setTextColor(getResources().getColor(R.color.textColorPrimary));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            textView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        }
        snackbar.show();
    }

    private void toolbarInitializing() {
        toolbar.inflateMenu(R.menu.menu_weather);
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
                    case R.id.refresh_weather:
                        refreshWeather();
                        break;
                }
                return true;
            }
        });
    }

    private void initializeItems() {
        initializeCityLists();
    }
    private void initializeCityLists() {
        cityNamesWorldEnglishSolid = cityNamesRepository.getCityNamesWorldEnglishSolid();
        cityNamesIranEnglishSolid = cityNamesRepository.getCityNamesIranEnglishSolid();
        cityNamesIranPersian = cityNamesRepository.getCityNamesIranPersian();
        cityNamesWorldPersian = cityNamesRepository.getCityNamesWorldPersian();
    }

    private void imageButtonWorldClickable (Boolean b){
        imageButtonWorldClickable = b;
    }

    private void imageButtonIranClickable (Boolean b){
        imageButtonIranClickable = b;
    }

    private void refreshButtonClickable (Boolean b){
        refreshButtonClickable = b;
    }

    private void worldButtonActivated (Boolean b){
        worldButtonActivated = b;
    }

    private void iranButtonActivated (Boolean b){
        iranButtonActivated = b;
    }

    private void imageButtonWorldBackgroundActivated(Boolean b){
        if (b){
            imageButtonWorld.setBackgroundDrawable(ContextCompat.getDrawable(this,
                    R.drawable.world_map_activated));
        }
        else if (!b){
            imageButtonWorld.setBackgroundDrawable(ContextCompat.getDrawable(this,
                    R.drawable.world_map_deactivated));
        }
    }

    private void imageButtonIranBackgroundActivated(Boolean b){
        if (b){
            imageButtonIran.setBackgroundDrawable(ContextCompat.getDrawable(this,
                    R.drawable.iran_map_activated));
        }
        else if (!b){
            imageButtonIran.setBackgroundDrawable(ContextCompat.getDrawable(this,
                    R.drawable.iran_map_deactivated));
        }
    }



    private void initializeRecyclerView(List<WeatherItem> items) {
        itemsCurrent = items;
        recyclerViewWeatherHome.setLayoutManager(new LinearLayoutManager(this));
        weatherAdapter = new WeatherAdapter(itemsCurrent, this);
        recyclerViewWeatherHome.setAdapter(weatherAdapter);
        recyclerViewWeatherHome.setHasFixedSize(true);

    }
}

