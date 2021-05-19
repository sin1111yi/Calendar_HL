package com.example.handlelife.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.handlelife.R;

public class BasenoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setNightMode();
    }

    public boolean isNightMode(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        return sharedPreferences.getBoolean("nightMode", false);
    }
    public void setNightMode(){
        setTheme(R.style.DayTheme);

    }
}