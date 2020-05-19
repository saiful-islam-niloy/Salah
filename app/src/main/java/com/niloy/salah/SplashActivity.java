package com.niloy.salah;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        hideActionBar();

        runSplashThread();

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
