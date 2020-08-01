package edu.veltech.newsailapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class SmokingHistory extends AppCompatActivity {
    int heartScore = 0;
    int smokeScore = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_smoking);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        try {
            Bundle bundle = getIntent().getExtras();
            heartScore = bundle.getInt("HEART_SCORE");
        }
        catch(Exception e){}
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_smoking:
                if (checked) {
                    Intent intent=new Intent(getApplicationContext(),AlcoholHistory.class);
                    smokeScore = 2;
                    intent.putExtra("HEART_SCORE",(heartScore + smokeScore));
                    startActivity(intent);
                    break;
                }
            case R.id.radio_nosmoking:
                if (checked) {
                    Intent intent=new Intent(getApplicationContext(),AlcoholHistory.class);
                    smokeScore = 0;
                    intent.putExtra("HEART_SCORE",(heartScore + smokeScore));
                    startActivity(intent);
                    break;
                }
            case R.id.radio_passivesmoker:
                if (checked) {
                    Intent intent=new Intent(getApplicationContext(),AlcoholHistory.class);
                    smokeScore = 1;
                    intent.putExtra("HEART_SCORE",(heartScore + smokeScore));
                    startActivity(intent);
                    break;
                }
        }
    }
}
