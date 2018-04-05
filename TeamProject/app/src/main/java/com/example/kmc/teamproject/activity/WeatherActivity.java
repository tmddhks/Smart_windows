package com.example.kmc.teamproject.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.remoteController:
                Intent intent = new Intent(WeatherActivity.this, RemoteControllerActivity.class);
                startActivity(intent);
                break;

            case R.id.helpCall:
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:01022850814"));
                startActivity(intent1);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
