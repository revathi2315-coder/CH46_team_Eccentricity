package edu.veltech.newsailapp;
import java.lang.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityRahasyaStart extends AppCompatActivity {

    int heartScore = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activty_rahasya_clickhere);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        try {
            Bundle bundle = getIntent().getExtras();
            heartScore = bundle.getInt("HEART_SCORE");
        }
        catch(Exception e){

        }
        SharedPreferences sharedPref = getSharedPreferences("UserDetails",MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        String convertHeartScore = String.valueOf(heartScore);
        editor.putString("heartScore",convertHeartScore);


        Button startButton = (Button) findViewById(R.id.clickhere);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if(heartScore <= 7) {
                     intent = new Intent(getApplicationContext(), GoldShield.class);
                     editor.putString("userInsuranceplan","PLAN-A");
                }
                else if(heartScore > 7 && heartScore <= 14) {
                     intent = new Intent(getApplicationContext(), SilverShield.class);
                    editor.putString("userInsuranceplan","PLAN-B");
                }
                else if(heartScore > 14 && heartScore <= 20) {
                     intent = new Intent(getApplicationContext(), BronzeShield.class);
                    editor.putString("userInsuranceplan","PLAN-C");
                }
                else if(heartScore > 20) {
                     intent = new Intent(getApplicationContext(), SandShield.class);
                     editor.putString("userInsuranceplan","PLAN-D");
                }
                editor.apply();
                startActivity(intent);
                finish();
            }
        });
    }
}

