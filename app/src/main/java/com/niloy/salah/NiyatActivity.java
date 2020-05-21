package com.niloy.salah;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.niloy.salah.SettingActivity.SHARED_PREFES;
import static com.niloy.salah.SettingActivity.ARABIC_FONT_SIZE;
import static com.niloy.salah.SettingActivity.BANGLA_FONT_SIZE;

public class NiyatActivity extends AppCompatActivity {
    private TextView arabic, bangla, bangla2;
    private FloatingActionButton fab;
    private String rakatId;
    private DbHandler dbHandler;
    private int arabicFontSize, banglaFontSize;
    private int fontSizeFactorArabic = 5;
    private int fontSizeFactorBangla = 4;


    @Override
    protected void onResume(){
        super.onResume();
        loadData();
        textResize();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niyat);
        Intent intent = getIntent();
        rakatId = intent.getStringExtra("Rakat_ID");

        arabic = findViewById(R.id.arabicNew);
        bangla = findViewById(R.id.banglaNew);
        bangla2 = findViewById(R.id.bangla2New);


        hideActionBar();
        loadData();
        displayData();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFES, MODE_PRIVATE);
        arabicFontSize = sharedPreferences.getInt(ARABIC_FONT_SIZE, 5 )+1;
        banglaFontSize = sharedPreferences.getInt(BANGLA_FONT_SIZE, 4 )+1;
    }

    private void displayData(){
        dbHandler = new DbHandler(this);
        Cursor cursor = dbHandler.getRakatNiyatData(rakatId);
        while (cursor.moveToNext()){
            arabic.setText(cursor.getString(4));
            bangla.setText(cursor.getString(5));
            bangla2.setText(cursor.getString(6));
        }
        textResize();
    }

    private void textResize() {
        arabic.setTextSize(arabicFontSize*fontSizeFactorArabic);
        bangla.setTextSize(banglaFontSize*fontSizeFactorBangla);
        bangla2.setTextSize(banglaFontSize*fontSizeFactorBangla);
    }

    private void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}
