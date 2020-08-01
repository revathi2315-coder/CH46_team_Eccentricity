package edu.veltech.newsailapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BMIHeight extends AppCompatActivity {
    public int heartScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_height);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Bundle bundle = getIntent().getExtras();
        heartScore = bundle.getInt("HEART_SCORE");
        Button startButton = (Button)findViewById(R.id.Next);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When the Button is clicked after enetering the height and weight calculate the BMI
                Intent intent=new Intent(getApplicationContext(),BMIWeight.class);
                intent.putExtra("HEART_SCORE",heartScore);
                EditText editText=findViewById(R.id.height);
                String tempHeight=editText.getText().toString().trim();
                int playerHeight=0;
                if (tempHeight.length() > 0){
                    playerHeight=Integer.parseInt(tempHeight);
                    intent.putExtra("HEIGHT",playerHeight);
                    startActivity(intent);
                }
                else{
                    editText.requestFocus();
                    editText.setError("Please Enter your Height in CM");
                    //Toast.makeText(BMIHeight.this,"Please Enter your Height in CM", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}

