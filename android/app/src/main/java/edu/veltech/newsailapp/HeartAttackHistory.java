package edu.veltech.newsailapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;

public class HeartAttackHistory extends AppCompatActivity {
    int heartScore = 0;
    int heartAttackScore = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_heartattack);
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
            case R.id.radio_both:
                if (checked) {
                    Intent intent=new Intent(getApplicationContext(),DiabetesHistory.class);
                    heartAttackScore = 3;
                    intent.putExtra("HEART_SCORE",( heartScore + heartAttackScore));
                    startActivity(intent);
                    break;
                   }
            case R.id.radio_other:
                if (checked) {

                    Intent intent=new Intent(getApplicationContext(),DiabetesHistory.class);
                    heartAttackScore = 2;
                    intent.putExtra("HEART_SCORE",( heartScore + heartAttackScore));
                    startActivity(intent);
                    break;
                }
            case R.id.radio_none:
                if (checked) {
                    Intent intent=new Intent(getApplicationContext(),DiabetesHistory.class);
                    heartAttackScore = 0;
                    intent.putExtra("HEART_SCORE",( heartScore + heartAttackScore));
                    startActivity(intent);
                    break;
                }
        }
    }
}
