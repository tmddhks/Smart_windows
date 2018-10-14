package com.example.kmc.teamproject.weather;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.kmc.teamproject.R;

public class CityPreference extends AppCompatActivity {
    SharedPreferences prefs;

    //    EditText editText;
    public CityPreference(Activity activity) {
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }
    // If the user has not chosen a city yet, return
    // Sydney as the default city

    public String getCity(String change) {
        return prefs.getString("city", change + ", kr");
    }

    void setCity(String city) {
        prefs.edit().putString("city", city).apply();
    }
}
