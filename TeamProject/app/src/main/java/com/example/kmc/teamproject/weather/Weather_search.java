package com.example.kmc.teamproject.weather;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kmc.teamproject.R;

public class Weather_search extends AppCompatActivity {

    SharedPreferences prefs;


        EditText editText;


    public Weather_search(Activity activity) {
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);

    }

    // If the user has not chosen a city yet, return
    // Sydney as the default city


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_search);
        //지역날씨 검색 버튼 이벤트
//        Button btn = (Button) findViewById(R.id.weather_search);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////                LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_weather1, null);
////                editText = (EditText) linearLayout.findViewById(R.id.search);
//            }
//        });
    }


    public String getCity() {

        return prefs.getString("city", "seoul");

    }

    void setCity(String city) {
        prefs.edit().putString("city", city).apply();
    }
}
