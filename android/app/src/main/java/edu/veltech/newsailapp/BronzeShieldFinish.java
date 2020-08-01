package edu.veltech.newsailapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class BronzeShieldFinish extends AppCompatActivity {
    public static final String DEFAULT = "N/A";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_bronze_result);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE);
        String useremail = sharedPreferences.getString("useremail",DEFAULT);
        String userDisplayName =  sharedPreferences.getString("userdisplayname",DEFAULT);
        String userGivenName = sharedPreferences.getString("usergivenname",DEFAULT);

        if (useremail.equals(DEFAULT) || userDisplayName.equals(DEFAULT) || userGivenName.equals(DEFAULT)){
            Toast.makeText(this,"loading details not found",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(BronzeShieldFinish.this,useremail + userDisplayName + userGivenName, Toast.LENGTH_LONG).show();
        }
        Button startButton = (Button) findViewById(R.id.start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When the Button is clicked after enetering the height and weight calculate the BMI
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
