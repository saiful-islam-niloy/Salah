package com.niloy.salah;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SalahActivity extends AppCompatActivity {

    DbHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salah);

        dbHandler = new DbHandler(this);
        SQLiteDatabase database =  dbHandler.getWritableDatabase();

    }
}
