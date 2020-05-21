package com.niloy.salah;


import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class SalahActivity extends AppCompatActivity {

    private DbHandler dbHandler;
    private RecyclerView recyclerView;
    private AdapterSalah adapterSalah;
    private ArrayList<ListSalah> salahList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salah);

        recyclerView = findViewById(R.id.salah);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        salahList = new ArrayList<ListSalah>();
        dbHandler = new DbHandler(this);

        hideActionBar();
        displayData();
    }



    private void displayData(){
        createDatabase();
        Cursor cursor = dbHandler.getSalahData();
        if(cursor.getCount() > 0){

            while (cursor.moveToNext()){
                ListSalah listSalah = new ListSalah(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                salahList.add(listSalah);
            }
            adapterSalah = new AdapterSalah(getApplicationContext(), salahList);
            recyclerView.setAdapter(adapterSalah);

        }else
            Toast.makeText(getApplicationContext(), "Sorry! No Data Found.\n Re-install the App.", Toast.LENGTH_LONG).show();

    }

    private void createDatabase() {
        try {
            dbHandler.createDataBase(1);
            dbHandler.customUpdateDB(1);
//            dbHandler.openDataBase();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

}
