package com.example.kmc.teamproject.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kmc.teamproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment2 extends Fragment {


    public WeatherFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather2, container, false);
    }

    public static WeatherFragment2 newInstance() {
        Bundle args = new Bundle();

        WeatherFragment2 fragment=new WeatherFragment2();
        fragment.setArguments(args);
        return fragment;
    }


}
