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
import android.net.Uri;
import android.os.AsyncTask;
import android.content.SharedPreferences;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import androidx.appcompat.app.AppCompatActivity;

public class SandShieldFinish extends AppCompatActivity {

    public static final String DEFAULT = "N/A";
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    public String   username;
    public  String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_sand_result);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Button startButton = (Button) findViewById(R.id.start);
        username = getApplicationContext().getResources().getString(R.string.Username);
        password = getApplicationContext().getResources().getString(R.string.Password);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When the Button is clicked
                String serverResponse = UpdateHeartScoretoServer();
                //Toast.makeText(SandShieldFinish.this, serverResponse+"Is not null",Toast.LENGTH_LONG ).show();
                Intent intent = new Intent(getApplicationContext(), ActivityPlanD.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private String UpdateHeartScoretoServer(){
        HttpURLConnection conn = null;
        URL url = null;
        try {
               url = new URL("http://54.145.155.144/loginpost.php");
        }
        catch (MalformedURLException e) {
            //Toast.makeText()
            return "from first url catch";
        }
        try
        {
            // Setup HttpURLConnection class to send and receive data from php and mysql
            conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setRequestMethod("POST");

            // setDoInput and setDoOutput method depict handling of both send and receive
            conn.setDoInput(true);
            conn.setDoOutput(true);
            SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE);
            String useremail = sharedPreferences.getString("useremail",DEFAULT);
            String userInsuranceplan =  sharedPreferences.getString("userInsuranceplan",DEFAULT);
            String userGivenName = sharedPreferences.getString("usergivenname",DEFAULT);
            String userAgegroup = sharedPreferences.getString("useragegroup",DEFAULT);
            String heartScore = sharedPreferences.getString("heartScore",DEFAULT);
            // Append parameters to URL
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("username", username)
                    .appendQueryParameter("password", password)
                    .appendQueryParameter("useremail", useremail)
                    .appendQueryParameter("usergivenname", userGivenName)
                    .appendQueryParameter("useragegroup", userAgegroup)
                    .appendQueryParameter("userinsuranceplan", userInsuranceplan)
                    .appendQueryParameter("heartscore", heartScore);
            String query = builder.build().getEncodedQuery();

            // Open connection for sending data
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            assert query != null;
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            conn.connect();

        }
        catch (IOException e)
        {
            return e.getMessage();
        }

        try {

            int response_code = conn.getResponseCode();

            // Check if successful connection made
            if (response_code == HttpURLConnection.HTTP_OK) {

                // Read data sent from server
                InputStream input = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                // Pass data to onPostExecute method
                return(result.toString());

            }else{

                return("unsuccessful");
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "exception";
        } finally {
            conn.disconnect();
        }


    }

}
