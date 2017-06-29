package com.example.android.norvicaconstruction.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.norvicaconstruction.MainActivity;
import com.example.android.norvicaconstruction.R;
import com.example.android.norvicaconstruction.util.NetworkUtils;

public class Splash_activity extends AppCompatActivity {

    private final long SPLASH_TIME_OUT = 1000;
    ProgressBar progressBar;
    /**
     * TAG string used for log.
     */
    private final String TAG = Splash_activity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                SharedPreferences pref = getSharedPreferences("Login", MODE_WORLD_READABLE);
                String mobile = pref.getString("mobile", "");
                String password = pref.getString("password", "");

                NetworkUtils internetCheck=new NetworkUtils();
                if (internetCheck.isNetworkAvailable(Splash_activity.this)) {

                    if (!mobile.equalsIgnoreCase("") && !password.equalsIgnoreCase("")) {
                        Intent i=new Intent(Splash_activity.this,MainActivity.class);
                        startActivity(i);
                    }
                    else {
                        Intent intent = new Intent(Splash_activity.this,Login_activity.class);
                        Splash_activity.this.startActivity(intent);
                    }

                }


                else {
                    Toast.makeText(Splash_activity.this, "Please Connect to Internet", Toast.LENGTH_SHORT).show();


                    // Display message in dialog box if you have not internet connection
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Splash_activity.this);
                    alertDialogBuilder.setTitle("No Internet Connection");
                    alertDialogBuilder.setMessage("You are offline please check your internet connection");
                    Toast.makeText(Splash_activity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                    alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            //Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
                            Intent intent = getIntent();
                            startActivity(intent);
                            finish();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }

            }
        }, SPLASH_TIME_OUT);

    }
}
