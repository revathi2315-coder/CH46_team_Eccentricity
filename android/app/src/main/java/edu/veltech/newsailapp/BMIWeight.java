package edu.veltech.newsailapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BMIWeight extends AppCompatActivity {
    int heartScore = 0;
    int height = 0;
    int bmiScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_weight);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Bundle bundle = getIntent().getExtras();
        try {
            heartScore = bundle.getInt("HEART_SCORE");
            height = bundle.getInt("HEIGHT");
        }
        catch(Exception e){}
        Button startButton = (Button)findViewById(R.id.Next);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When the Button is clicked after enetering the height and weight calculate the BMI
                Intent intent=new Intent(getApplicationContext(),CaloriesBurnt.class);
                EditText editText=findViewById(R.id.weight);
                String tempWeight=editText.getText().toString().trim();
                int playerWeight=0;
                if (tempWeight.length() > 0){
                    playerWeight=Integer.parseInt(tempWeight);
                    bmiScore = calculateBMI(height,playerWeight);
                    intent.putExtra("HEART_SCORE",(heartScore + bmiScore));
                    startActivity(intent);
                }
                else {
                    editText.requestFocus();
                    editText.setError("Please Enter your Weight in KG");
                    //Toast.makeText(BMIWeight.this,"Please Enter your Weight in KG", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
    public int calculateBMI(int height, int weight){
        double bmi = 0.0;
        int bmiResult = 0;
        double bmiheight = height / 100; // Convert the Centimeters into Meters for BMI Calculation
        bmi = weight / ( bmiheight * bmiheight);
        if ( bmi < 18.5){
            bmiResult = 1;
        }
        else if (bmi >= 18.5 && bmi <= 22.9){
            bmiResult = 0;
        }
        else if( bmi >= 22.9 && bmi <= 24.9){
            bmiResult = 1;
        }
        else if( bmi > 24.9){
            bmiResult = 2;
        }
    return bmiResult;
   }
}
