package edu.veltech.newsailapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;



public class ActivityLoginWithGoogle extends AppCompatActivity implements View.OnClickListener {

    GoogleSignInClient mGoogleSignInClient;
    SignInButton sign_in_button;


    private static final int RC_SIGN_IN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.axtivity_login_with_google);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);



// Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // Check for existing Google Sign In account, if the user is already signed in
        //       the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
                //Toast.makeText(ActivityLoginWithGoogle.this, account.getDisplayName(), Toast.LENGTH_LONG).show();
                sign_in_button = findViewById(R.id.sign_in_button);
                sign_in_button.setSize(SignInButton.SIZE_STANDARD);
                sign_in_button.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {

            //  updateUI();
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task task = GoogleSignIn.getSignedInAccountFromIntent(data);
            //Toast.makeText(ActivityLoginWithGoogle.this,"in activity result", Toast.LENGTH_LONG).show();
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task completedTask) {
        try
        {
            GoogleSignInAccount account = (GoogleSignInAccount) completedTask.getResult(ApiException.class);
            captureUserDetails(account);

        }
        catch (ApiException e)
        {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //Log.w("Warning", "signInResult:failed code=" + e.getStatusCode());
            //Toast.makeText(ActivityLoginWithGoogle.this,"Google Authentication Service Unavailable. Please Try Again later" + e.getStatusCode(), Toast.LENGTH_LONG).show();
            Toast.makeText(ActivityLoginWithGoogle.this,"Google Authentication Service Unavailable. Please Try Again later" + e.getStatusCode(), Toast.LENGTH_LONG).show();
        } catch (Throwable throwable) {

            Toast.makeText(ActivityLoginWithGoogle.this,"inside throwable"+ throwable.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void captureUserDetails(GoogleSignInAccount account){
        try
        {
            SharedPreferences sharedPref = getSharedPreferences("UserDetails",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("useremail",account.getEmail());
            editor.putString("usergivenname",account.getGivenName());
            editor.putString("userdisplayname",account.getDisplayName());
            editor.apply();
            Toast.makeText(ActivityLoginWithGoogle.this,"Your are Logged in as "+account.getGivenName() + "With Email address "+account.getEmail(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ActivityLoginWithGoogle.this, MainMap.class);
            startActivity(intent);
        }
        catch(Exception e)
        {
            Toast.makeText(ActivityLoginWithGoogle.this,"inside update ui catch"+e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}

