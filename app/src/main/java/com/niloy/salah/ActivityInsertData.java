package com.niloy.salah;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityInsertData extends AppCompatActivity {
    private EditText id, name;
    private Button submit;
    private DbHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        submit = findViewById(R.id.submit);
        dbHandler = new DbHandler(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String names = name.getText().toString();
                if(v.getId() == R.id.submit){
                    long rowId = dbHandler.insertData(names);
                    if(rowId>0)
                        Toast.makeText(getApplicationContext(), "Row Inserted. :)", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(), "Failed. :)", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
