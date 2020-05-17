package com.niloy.salah;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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

        displayData();
    }



    private void displayData(){
        try {
            dbHandler.createDataBase();
//            dbHandler.openDataBase();
        }
        catch (Exception e) {
            e.printStackTrace();
        }



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

}
