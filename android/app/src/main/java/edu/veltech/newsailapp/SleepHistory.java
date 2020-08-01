package edu.veltech.newsailapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class SleepHistory extends AppCompatActivity {
    int heartScore = 0;
    int sleepScore = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_sleep);
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
            case R.id.radio_sleepless5:
                if (checked) {
                    //Toast.makeText(MainActivity.this,"Male was selected", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),ActivityTransitionShaili.class);
                    sleepScore = 2;
                    intent.putExtra("HEART_SCORE",(heartScore + sleepScore));
                    startActivity(intent);
                    break;
                }
            case R.id.radio_sleep6to8:
                if (checked) {
                    //Toast.makeText(MainActivity.this, "Female was selected", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),ActivityTransitionShaili.class);
                    sleepScore = 0;
                    intent.putExtra("HEART_SCORE",(heartScore + sleepScore));
                    startActivity(intent);
                    break;
                }
            case R.id.radio_sleepmore8:
                if (checked) {
                    //Toast.makeText(MainActivity.this, "Female was selected", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),ActivityTransitionShaili.class);
                    sleepScore = 1;
                    intent.putExtra("HEART_SCORE",(heartScore + sleepScore));
                    startActivity(intent);
                    break;
                }
        }
    }
}

