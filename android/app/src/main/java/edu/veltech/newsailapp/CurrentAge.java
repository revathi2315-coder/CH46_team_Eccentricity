package edu.veltech.newsailapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class CurrentAge extends AppCompatActivity {
    int heartScore = 0;
    String subjectGender = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_age);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Bundle bundle = getIntent().getExtras();
        try
        {
            subjectGender = bundle.getString("myGender").trim();
            Log.v("Subject Male/Female",subjectGender);
        }
        catch(Exception e){
            Log.v("Exception",e.getMessage());
        }
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        SharedPreferences sharedPref = getSharedPreferences("UserDetails",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_0_25:
                if (checked) {
                    //Toast.makeText(MainActivity.this,"Male was selected", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),ActivityVartha.class);
                    if(subjectGender.equals("Male")) {
                        heartScore = 3;
                    }
                    else if(subjectGender.equals("Female")) {
                        heartScore = 0;
                    }
                    editor.putString("useragegroup","0-25");
                    editor.apply();
                    intent.putExtra("HEART_SCORE",heartScore);
                    startActivity(intent);
                    break;
                }
            case R.id.radio_25_45:
                if (checked) {
                    //Toast.makeText(MainActivity.this, "Female was selected", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),ActivityVartha.class);
                    if(subjectGender.equals("Male")) {
                        heartScore = 3;
                    }
                    else if(subjectGender.equals("Female")) {
                        heartScore = 1;
                    }
                    editor.putString("useragegroup","25-45");
                    editor.apply();
                    intent.putExtra("HEART_SCORE",heartScore);
                    startActivity(intent);
                    break;
                }
            case R.id.radio_45_100:
                if (checked) {
                    //Toast.makeText(MainActivity.this, "Female was selected", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),ActivityVartha.class);
                    if(subjectGender.equals("Male")) {
                        heartScore = 3;
                    }
                    else if(subjectGender.equals("Female")) {
                        heartScore = 2;
                    }
                    editor.putString("useragegroup","45-100");
                    editor.apply();
                    intent.putExtra("HEART_SCORE",heartScore);
                    startActivity(intent);
                    break;
                }
        }
    }

}

