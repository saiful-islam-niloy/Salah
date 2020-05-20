package com.niloy.salah;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RakatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterRakat adapterRakat;
    private ArrayList<ListRakat> rakatList;
    private DbHandler dbHandler;
    private String salahId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rakat);

        Intent intent = getIntent();
        salahId = intent.getStringExtra("Salah_ID");

        recyclerView = findViewById(R.id.rakat);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rakatList = new ArrayList<ListRakat>();

        hideActionBar();
        displayData();
    }

    private void displayData(){
        dbHandler = new DbHandler(this);
        Cursor cursor = dbHandler.getRakatData(salahId);
        if(cursor.getCount() > 0){

            while (cursor.moveToNext()){

                ListRakat listRakat = new ListRakat(cursor.getString(0), cursor.getString(6), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                rakatList.add(listRakat);
            }

            adapterRakat = new AdapterRakat(getApplicationContext(), rakatList);
            recyclerView.setAdapter(adapterRakat);

        }
    }

    private void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}
