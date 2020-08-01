package edu.veltech.newsailapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class DiabetesHistory  extends AppCompatActivity {
    int heartScore = 0;
    int diabetesScore = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_diabetes);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Bundle bundle = getIntent().getExtras();
        try
        {
            heartScore = bundle.getInt("HEART_SCORE");
            //Log.v("Heart Score of Subject","old value"+heartScore);
        }
        catch(Exception e){
            Log.v("Exception",e.getMessage());
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_notdiabetic:
                if (checked) {
                    //Toast.makeText(MainActivity.this,"Male was selected", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),BloodPressureHistory.class);
                    diabetesScore = 0;
                    intent.putExtra("HEART_SCORE",(heartScore + diabetesScore));
                    startActivity(intent);
                    break;
                }
            case R.id.radio_amdiabetic:
                if (checked) {
                    //Toast.makeText(MainActivity.this, "Female was selected", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),BloodPressureHistory.class);
                    diabetesScore = 3;
                    intent.putExtra("HEART_SCORE",(heartScore + diabetesScore));
                    startActivity(intent);
                    break;
                }
        }
    }
}

