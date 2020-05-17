package com.niloy.salah;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NiyatActivity extends AppCompatActivity {
    private TextView arabic, bangla, bangla2;
    private String rakatId;
    private DbHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niyat);
        Intent intent = getIntent();
        rakatId = intent.getStringExtra("Rakat_ID");

        arabic = findViewById(R.id.arabicNew);
        bangla = findViewById(R.id.banglaNew);
        bangla2 = findViewById(R.id.bangla2New);

        displayData();
    }

    private void displayData(){
        dbHandler = new DbHandler(this);
        Cursor cursor = dbHandler.getRakatNiyatData(rakatId);
        System.out.println("Final: "+cursor.getCount());
        while (cursor.moveToNext()){
            arabic.setText(cursor.getString(4));
            bangla.setText(cursor.getString(5));
            bangla2.setText(cursor.getString(6));
        }

    }
}
