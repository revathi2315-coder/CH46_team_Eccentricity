package edu.veltech.newsailapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityShailiStart extends AppCompatActivity {

    int heartScore = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_shaili_start);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Bundle bundle = getIntent().getExtras();
        heartScore = bundle.getInt("HEART_SCORE");
        Button startButton = (Button) findViewById(R.id.shailistart);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When the Button is clicked after enetering the height and weight calculate the BMI
                Intent intent = new Intent(getApplicationContext(), SmokingHistory.class);
                intent.putExtra("HEART_SCORE",heartScore);
                startActivity(intent);
                finish();
            }
        });
    }
}

