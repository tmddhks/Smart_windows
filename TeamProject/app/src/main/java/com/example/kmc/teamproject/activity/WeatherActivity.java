package com.example.kmc.teamproject.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.kmc.teamproject.R;
import com.example.kmc.teamproject.adapter.WeatherPagerAdapter;

public class WeatherActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_fragment);

        WeatherPagerAdapter mWeatherPagerAdapter = new WeatherPagerAdapter(
                getSupportFragmentManager()
        );

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mWeatherPagerAdapter);

        TabLayout mTab = (TabLayout) findViewById(R.id.tab);
        mTab.setupWithViewPager(mViewPager);
    }
}
