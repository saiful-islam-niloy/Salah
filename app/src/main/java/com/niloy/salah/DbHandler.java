package com.niloy.salah;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.content.Context.MODE_PRIVATE;
import static com.niloy.salah.SettingActivity.SHARED_PREFES;

public class DbHandler extends SQLiteOpenHelper {
    private Context context;
    private SQLiteDatabase myDataBase;

    private static String DATABASE_PATH = "";
    private static final String DATABASE_NAME = "Salah.db";

    private static final String TABLE_NAME_SALAH = "salah";
    private static final String KEY_SALAH_ID = "_id";
    private static final String KEY_SALAH_NAME = "name";
    private static final String CREATE_SALAH_TABLE =
            "CREATE TABLE "+ TABLE_NAME_SALAH + "("+
                    KEY_SALAH_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    KEY_SALAH_NAME+" VARCHAR(20) );";

    private static final String TABLE_NAME_PRIORITY = "priority";
    private static final String KEY_PRIORITY_ID = "_id";
    private static final String KEY_PRIORITY_NAME = "name";
    private static final String CREATE_PRIORITY_TABLE =
            "CREATE TABLE "+ TABLE_NAME_PRIORITY + "("+
                    KEY_PRIORITY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    KEY_PRIORITY_NAME+" VARCHAR(20) );";

    private static final String TABLE_NAME_RAKAT = "rakat";
    private static final String KEY_RAKAT_ID = "_id";
    private static final String KEY_RAKAT_SALAH_ID = "salah_id";
    private static final String KEY_RAKAT_PRIORITY_ID = "priority_id";
    private static final String KEY_RAKAT_QUANTITY = "rakat";
    private static final String KEY_RAKAT_NIYAT_ARABIC = "niyat_arabic";
    private static final String KEY_RAKAT_NIYAT_BANGLA_PRONOUNCIATION = "niyat_bangla_pronounciation";
    private static final String KEY_RAKAT_NIYAT_BANGLA = "niyat_bangla";
    private static final String CREATE_RAKAT_TABLE =
            "CREATE TABLE "+ TABLE_NAME_RAKAT + "("+
                    KEY_RAKAT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    KEY_RAKAT_SALAH_ID+" INTEGER NOT NULL, "+
                    KEY_RAKAT_PRIORITY_ID+" INTEGER NOT NULL, "+
                    KEY_RAKAT_QUANTITY+" VARCHAR(1),"+
                    KEY_RAKAT_NIYAT_ARABIC+" VARCHAR(150),"+
                    KEY_RAKAT_NIYAT_BANGLA_PRONOUNCIATION+" VARCHAR(150),"+
                    KEY_RAKAT_NIYAT_BANGLA+" VARCHAR(150));";

    private static final int VERSION_NUMBER = 1;
    private static final String FETCH_RAKAT_DATA = "SELECT id, salah_id, rakat, niyat_arabic, niyat_bangla_pronounciation,niyat_bangla, " +
            "(SELECT name FROM priority WHERE id = priority_id) as test FROM rakat";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME_SALAH;
    private static final String DROP_TABLE2 = "DROP TABLE IF EXISTS "+ TABLE_NAME_PRIORITY;
    private static final String DROP_TABLE3 = "DROP TABLE IF EXISTS "+ TABLE_NAME_RAKAT;

    private static final String OLD_VERSION = "DATAVERSION";

    public DbHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
        DATABASE_PATH = context.getDatabasePath(DATABASE_NAME).toString();
    }



    public void createDataBase(int version) throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            Toast.makeText(context, "DB Exits", Toast.LENGTH_LONG).show();
        } else {
            this.getWritableDatabase();
            try {
                copyDataBase();
            }
            catch (IOException e) {
                throw new Error("Error copying database");
            }
            saveDatabaseVersion(version);
        }

    }

    public boolean checkDataBase() {
        File databasePath = context.getDatabasePath(DATABASE_PATH);
        return databasePath.exists();
    }

    private void copyDataBase() throws IOException {
        InputStream inputStream = context.getAssets().open(DATABASE_NAME);

        if(inputStream != null) {

            int mFileLength = inputStream.available();

            SQLiteDatabase database = this.getReadableDatabase();
            String filePath = database.getPath();
            database.close();

            // Save the downloaded file
            OutputStream output = new FileOutputStream(filePath);

            byte data[] = new byte[1024];
            long total = 0;
            int count;
            while ((count = inputStream.read(data)) != -1) {
                total += count;
                if(mFileLength != -1) {
                    // Publish the progress
//                    publishProgress((int) (total * 100 / mFileLength));
                }
                output.write(data, 0, count);
            }
//            return true;
        }
    }

    public void openDataBase() throws SQLException {
        String myPath = DATABASE_PATH;
        myDataBase = SQLiteDatabase
                .openDatabase(
                        myPath, null,
                        SQLiteDatabase.OPEN_READONLY);
    }


    public  void customUpdateDB(int version){
        if(loadDataBaseVersion()< version){
            this.context.deleteDatabase(DATABASE_NAME);
            saveDatabaseVersion(version);
            try {
                this.copyDataBase();
                Toast.makeText(context, "DB Updated", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveDatabaseVersion(int version){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(OLD_VERSION, version);
        editor.apply();
    }

    private int loadDataBaseVersion(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFES, MODE_PRIVATE);
        int old = sharedPreferences.getInt(OLD_VERSION, 1 );
        return 1;
    }
    @Override
    public synchronized void close() {
        // close the database.
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(CREATE_SALAH_TABLE);
            db.execSQL(CREATE_PRIORITY_TABLE);
            db.execSQL(CREATE_RAKAT_TABLE);
            Toast.makeText(context, "Database Created. :)", Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(context, ""+e, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public Cursor getSalahData(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+ TABLE_NAME_SALAH +";", null);
        return cursor;
    }


    public Cursor getRakatData(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(FETCH_RAKAT_DATA+" WHERE "+KEY_RAKAT_SALAH_ID+"="+ Integer.parseInt(id), null);
        return cursor;
    }

    public Cursor getRakatNiyatData(String rakatId) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+ TABLE_NAME_RAKAT +" WHERE "+"id"+"="+ Integer.parseInt(rakatId)+";", null);
        return cursor;
    }
}
