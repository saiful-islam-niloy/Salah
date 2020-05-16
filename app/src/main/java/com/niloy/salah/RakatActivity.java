package com.niloy.salah;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

        displayData();
    }

    private void displayData(){
        dbHandler = new DbHandler(this);

        SQLiteDatabase database =  dbHandler.getReadableDatabase();
        Cursor cursor = dbHandler.getRakatData(salahId);
        System.out.println("Rakat COunt: "+cursor.getCount());
        if(cursor.getCount() > 0){

            while (cursor.moveToNext()){
                ListRakat listRakat = new ListRakat(cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
                System.out.println(cursor.getString(0));
                rakatList.add(listRakat);
            }

            adapterRakat = new AdapterRakat(getApplicationContext(), rakatList,null);
            recyclerView.setAdapter(adapterRakat);

        }
    }
}
