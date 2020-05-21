package com.niloy.salah;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RakatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView textView;
    private AdapterRakat adapterRakat;
    private ArrayList<ListRakat> rakatList;
    private DbHandler dbHandler;
    private String salahId, salahName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rakat);

        Intent intent = getIntent();
        salahId = intent.getStringExtra("Salah_ID");
        salahName = intent.getStringExtra("Salah_Name");

        textView = findViewById(R.id.salah_name_header);
        textView.setText(salahName);

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
        else
            Toast.makeText(getApplicationContext(), "Sorry! No Data Found.\n Re-install the App.", Toast.LENGTH_LONG).show();
    }

    private void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

}
