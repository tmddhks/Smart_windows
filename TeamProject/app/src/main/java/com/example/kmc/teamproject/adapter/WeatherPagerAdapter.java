package com.example.kmc.teamproject.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kmc.teamproject.fragment.WeatherFragment1;
import com.example.kmc.teamproject.fragment.WeatherFragment2;

public class WeatherPagerAdapter extends FragmentPagerAdapter {

    public WeatherPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return WeatherFragment1.newInstance();
            case 1:
                return WeatherFragment2.newInstance();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "기상청 날씨";
            case 1:
                return "센서 감지 날씨";
            default:
                return null;
        }
    }

    private static int PAGE_NUMBER = 2;

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }
}
