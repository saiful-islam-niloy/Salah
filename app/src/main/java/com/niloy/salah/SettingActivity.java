package com.niloy.salah;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends Activity {
    private SeekBar arabic, bangla;
    private TextView textViewArabic, textViewBangla;
    private Button save;


    private int arabicFontSize, banglaFontSize;
    public static final String SHARED_PREFES = "sharedPrefs";
    public static final String ARABIC_FONT_SIZE = "arabicFontSize";
    public static final String BANGLA_FONT_SIZE = "banglaFontSize";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        textViewArabic = findViewById(R.id.text);
        textViewBangla = findViewById(R.id.text2);
        save = findViewById(R.id.save);

        loadData();

        arabic = findViewById(R.id.arabic_font);
        arabic.setMax(10);
        arabic.setProgress(arabicFontSize);
        textViewArabic.setText("Arabic Font Size: "+arabicFontSize);
        arabic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewArabic.setText("Arabic Font Size: "+Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        bangla = findViewById(R.id.bangla_font);
        bangla.setMax(10);
        bangla.setProgress(banglaFontSize);
        textViewBangla.setText("Bangla Font Size: "+banglaFontSize);
        bangla.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewBangla.setText("Bangla Font Size: "+Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                Toast.makeText(getApplicationContext(), "Saved.", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(ARABIC_FONT_SIZE, arabic.getProgress());
        editor.putInt(BANGLA_FONT_SIZE, bangla.getProgress());

        editor.apply();

    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFES, MODE_PRIVATE);
        arabicFontSize = sharedPreferences.getInt(ARABIC_FONT_SIZE, 3 );
        banglaFontSize = sharedPreferences.getInt(BANGLA_FONT_SIZE, 3 );
    }

}
