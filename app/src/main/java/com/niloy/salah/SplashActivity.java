package com.niloy.salah;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class SplashActivity extends AppCompatActivity {
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        resizeMinarIfNeeded();

        hideActionBar();

        runSplashThread();

    }

    private void resizeMinarIfNeeded(){
        relativeLayout = findViewById(R.id.minar);
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk <= Build.VERSION_CODES.M) {
            relativeLayout.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.background2));
        }

    }

    private void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    private void runSplashThread(){
        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                    Intent intent = new Intent(getApplicationContext(),SalahActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }

}
