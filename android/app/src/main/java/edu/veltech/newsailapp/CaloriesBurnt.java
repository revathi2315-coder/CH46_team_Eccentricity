package edu.veltech.newsailapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class CaloriesBurnt extends AppCompatActivity {
    int heartScore = 0;
    int caloriesScore = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_caloriesburnt);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Bundle bundle = getIntent().getExtras();
        try {
            heartScore = bundle.getInt("HEART_SCORE");
        }
        catch(Exception e){}
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_sedentary:
                if (checked) {
                    //Toast.makeText(MainActivity.this,"Male was selected", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),SleepHistory.class);
                    caloriesScore = 3;
                    intent.putExtra("HEART_SCORE",(heartScore + caloriesScore));
                    startActivity(intent);
                    break;
                }
            case R.id.radio_moderate:
                if (checked) {
                    //Toast.makeText(MainActivity.this, "Female was selected", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),SleepHistory.class);
                    caloriesScore = 1;
                    intent.putExtra("HEART_SCORE",(heartScore + caloriesScore));
                    startActivity(intent);
                    finish();
                }
            case R.id.radio_lightlyactive:
                if (checked) {
                    //Toast.makeText(MainActivity.this, "Female was selected", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),SleepHistory.class);
                    caloriesScore = 0;
                    intent.putExtra("HEART_SCORE",(heartScore + caloriesScore));
                    startActivity(intent);
                    finish();
                }
        }
    }
}
