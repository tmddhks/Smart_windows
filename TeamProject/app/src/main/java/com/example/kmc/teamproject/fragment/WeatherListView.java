package com.example.kmc.teamproject.fragment;


import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kmc.teamproject.R;
import com.example.kmc.teamproject.server.forecastTask;
import com.example.kmc.teamproject.server.windowTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class WeatherListView extends Fragment {
    Typeface weatherFont;

    ListView weather_list;
    ArrayList<String> weather_info;
    ArrayAdapter<String> adapter;

    public WeatherListView() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_weatherlistview, null);

        weather_list = (ListView) rootView.findViewById(R.id.weather_list);
        weather_info = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, weather_info);
        weather_list.setAdapter(adapter);

        String resulttext1;

        try {
            resulttext1 = new forecastTask().execute().get();
            JSONArray ja = new JSONArray("resulttext1");

            for (int i = 0; i < ja.length(); i++) {
                JSONObject forecast = ja.getJSONObject(i);

                String info = "시간 : " + forecast.getString("time") + "\n" + "날씨 : " + forecast.getString("weather") + "\n" + "온도 : " + forecast.getString("temp") + "\n" + "습도 : " + forecast.getString("hudmity");
                weather_info.add(info);
                adapter.notifyDataSetChanged();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    public static WeatherListView newInstance() {
        Bundle args = new Bundle();

        WeatherListView fragment = new WeatherListView();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedlnstanceState) {
        weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weather.ttf");
        super.onCreate(savedlnstanceState);

    }
}