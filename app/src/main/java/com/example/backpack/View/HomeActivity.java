package com.example.backpack.View;

import androidx.appcompat.app.AppCompatActivity;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageButton;

import com.example.backpack.R;
import com.example.backpack.View.Backpack.BackpackListActivity;
import com.example.backpack.View.Weather.WeatherHomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.imageButtonBackpack)
    ImageButton imageButtonBP;
    private ActivityOptions options;
    private Slide exitTransitionSlide;
    private Fade exitTransitionFade;
    private Slide enterTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
        initializeTransitions();

      }

    private void initializeTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            exitTransitionSlide = new Slide();
            exitTransitionSlide.setSlideEdge(Gravity.LEFT);
            exitTransitionSlide.setDuration(500);
            exitTransitionSlide.setInterpolator(new AnticipateOvershootInterpolator());
            getWindow().setExitTransition(exitTransitionSlide);
            exitTransitionFade = new Fade();
            exitTransitionFade.setDuration(500);
            exitTransitionFade.setInterpolator(new AnticipateOvershootInterpolator());

            enterTransition = new Slide();
            enterTransition.setSlideEdge(Gravity.LEFT);
            enterTransition.setDuration(500);
            getWindow().setEnterTransition(enterTransition);
            getWindow().setAllowEnterTransitionOverlap(false);
            enterTransition.setInterpolator(new AnticipateOvershootInterpolator());

        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
           }

    @OnClick(R.id.imageButtonBackpack)
    public void onBackpack(View view){


        Intent intent = new Intent(HomeActivity.this, BackpackListActivity.class);
        intent.putExtra("HomeActivityIntent", 1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(exitTransitionSlide);

            options = ActivityOptions.makeSceneTransitionAnimation(this);

                    startActivity(intent,options.toBundle());

                }else {
            startActivity(intent);

                }

    }

    @OnClick(R.id.imageButtonWeather)
    public void onWeathet(View view){

        Intent intent = new Intent(HomeActivity.this, WeatherHomeActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(exitTransitionFade);

            options = ActivityOptions.makeSceneTransitionAnimation(this);

            startActivity(intent,options.toBundle());

        }else {
            startActivity(intent);

        }
    }

    }

