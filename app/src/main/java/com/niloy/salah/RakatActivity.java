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
    private ArrayList<String> priorityList;
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
        priorityList  = new ArrayList<String>();

        displayData();
    }

    private void displayData(){
        dbHandler = new DbHandler(this);
        Cursor cursor = dbHandler.getRakatData(salahId);
        if(cursor.getCount() > 0){

            while (cursor.moveToNext()){

                ListRakat listRakat = new ListRakat(cursor.getString(0), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
                if(cursor.getString(2) == "1")
                    priorityList.add("ফরজ");
                else if(cursor.getString(2) == "2")
                    priorityList.add("ওয়াজিব");
                else if(cursor.getString(2) == "3")
                    priorityList.add("সুন্নত");
                else if(cursor.getString(2) == "4")
                    priorityList.add("নফল");
                System.out.println("Priority Count: "+priorityList.size());
                rakatList.add(listRakat);
            }

            adapterRakat = new AdapterRakat(getApplicationContext(), rakatList,null);
            recyclerView.setAdapter(adapterRakat);

        }
    }
}
