package com.example.kmc.teamproject.weather;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

public class CityPreference extends AppCompatActivity {
    SharedPreferences prefs;
//    EditText editText;
    public CityPreference(Activity activity) {
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }
    // If the user has not chosen a city yet, return
    // Sydney as the default city
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.weather_search);
//
//    }

    public String getCity() {
        return prefs.getString("city","seoul");
    }
    void setCity(String city) {
        prefs.edit().putString("city", city).apply();
    }
}
