package edu.veltech.newsailapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class ActivityVartha extends AppCompatActivity {
    int SPLASH_DISPLAY_LENGTH = 8000; //splash screen will be shown for 2 seconds
    int heartScore = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_vartha);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Bundle bundle = getIntent().getExtras();
        heartScore = bundle.getInt("HEART_SCORE");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(getApplicationContext(), ActivityVarthaStart.class);
                mainIntent.putExtra("HEART_SCORE",heartScore);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}