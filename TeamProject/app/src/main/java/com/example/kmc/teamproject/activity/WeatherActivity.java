package com.example.kmc.teamproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.kmc.teamproject.R;

import com.example.kmc.teamproject.adapter.WeatherPagerAdapter;
import com.example.kmc.teamproject.fragment.WeatherFragment1;

import static android.widget.PopupMenu.*;

public class WeatherActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_fragment);

        ImageButton menubtn = (ImageButton) findViewById(R.id.barMenubtn);
        ImageButton searchbtn = (ImageButton) findViewById(R.id.searchBtn);
        final EditText searchCity = (EditText) findViewById(R.id.searchCity);

        menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(WeatherActivity.this, v);
                popup.getMenuInflater().inflate(R.menu.barmenu, popup.getMenu());
                popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.remoteController:
                                Intent intent = new Intent(WeatherActivity.this, RemoteControllerActivity.class);
                                startActivity(intent);
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check = searchCity.getText().toString();
                if (check.equals("")) {
                    Toast.makeText(WeatherActivity.this, "도시를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    WeatherFragment1.citychange = searchCity.getText().toString();
                    Intent intent = new Intent(WeatherActivity.this, WeatherActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });

        WeatherPagerAdapter mWeatherPagerAdapter = new WeatherPagerAdapter(
                getSupportFragmentManager()
        );

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mWeatherPagerAdapter);
    }
}