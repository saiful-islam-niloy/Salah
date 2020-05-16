package com.niloy.salah;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
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

        displayData();



    }
    private void displayData(){
        dbHandler = new DbHandler(this);
        try {
            dbHandler.createDataBase();
            dbHandler.openDataBase();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        SQLiteDatabase database =  dbHandler.getReadableDatabase();
        Cursor cursor = dbHandler.getData();
        if(cursor.getCount() > 0){

            while (cursor.moveToNext()){
                ListSalah listSalah = new ListSalah(cursor.getString(0), cursor.getString(1));
                System.out.println(cursor.getString(1));
                salahList.add(listSalah);
            }
            adapterSalah = new AdapterSalah(getApplicationContext(), salahList);
            recyclerView.setAdapter(adapterSalah);

        }
    }
}
