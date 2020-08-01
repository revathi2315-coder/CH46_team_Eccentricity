package edu.veltech.newsailapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
//import android.os.Handler;

public class GenderSail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_gender);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        String myGender;
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_male:
                if (checked) {
                    //Toast.makeText(MainActivity.this,"Male was selected", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),CurrentAge.class);
                    myGender = "Male";
                    intent.putExtra("myGender",myGender);
                    startActivity(intent);
                    break;
                }
            case R.id.radio_female:
                if (checked) {
                    //Toast.makeText(MainActivity.this, "Female was selected", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),CurrentAge.class);
                    myGender = "Female";
                    intent.putExtra("myGender",myGender);
                    startActivity(intent);
                    break;
                }
        }
    }

}
