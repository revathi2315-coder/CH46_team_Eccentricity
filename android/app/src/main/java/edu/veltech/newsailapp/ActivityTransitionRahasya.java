package edu.veltech.newsailapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityTransitionRahasya extends AppCompatActivity {
    int SPLASH_DISPLAY_LENGTH = 5000; //splash screen will be shown for 2 seconds
    int heartScore = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_transition_rahasya);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        try {
            Bundle bundle = getIntent().getExtras();
            heartScore = bundle.getInt("HEART_SCORE");
        }
        catch(Exception e){}
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(getApplicationContext(), ActivityRahasya.class);
                mainIntent.putExtra("HEART_SCORE",heartScore);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

}

