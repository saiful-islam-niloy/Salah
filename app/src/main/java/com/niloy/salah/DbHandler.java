package com.niloy.salah;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class DbHandler extends SQLiteOpenHelper {
    private Context context;

    private static final String DATABASE_NAME = "Salah.db";

    private static final String TABLE_NAME = "Salah";
    private static final String KEY_SALAH_ID = "_id";
    private static final String KEY_SALAH_NAME = "name";
    private static final String CREATE_SALAH_TABLE =
            "CREATE TABLE "+TABLE_NAME+ "("+
                    KEY_SALAH_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    KEY_SALAH_NAME+" VARCHAR(255) );";

    private static final int VERSION_NUMBER = 1;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;

    public DbHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(CREATE_SALAH_TABLE);
            Toast.makeText(context, "Database Created. :)", Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(context, ""+e, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL(DROP_TABLE);
            onCreate(db);
            Toast.makeText(context, "Database Upgraded. :)", Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(context, ""+e, Toast.LENGTH_LONG).show();
        }
    }

    public long insertData(String name){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SALAH_NAME, name);

        long rowId = database.insert(TABLE_NAME, null, contentValues);
        return rowId;
    }

    public Cursor getData(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME+";", null);
        return cursor;
    }
}
