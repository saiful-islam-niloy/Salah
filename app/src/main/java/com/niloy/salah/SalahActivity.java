package com.niloy.salah;


import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        System.out.println("Cursor: "+cursor.getCount());
        if(cursor.getCount() > 0){

            while (cursor.moveToNext()){
                ListSalah listSalah = new ListSalah(cursor.getString(0), cursor.getString(1));
                System.out.println(cursor.getString(0));
                salahList.add(listSalah);
            }
            adapterSalah = new AdapterSalah(getApplicationContext(), salahList);
            recyclerView.setAdapter(adapterSalah);

        }
    }

    private void createDatabase() {
        try {
            dbHandler.createDataBase();
            dbHandler.openDataBase();
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
