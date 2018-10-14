package com.example.kmc.teamproject.fragment;


import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kmc.teamproject.R;
import com.example.kmc.teamproject.server.forecastTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.concurrent.ExecutionException;


public class WeatherFragment3 extends Fragment {

    Typeface weatherFont;
    TextView Temperature;
    TextView Humidity;
    TextView Weather;
    TextView Time;


    public WeatherFragment3() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_weather3, container, false);

        Temperature = (TextView) rootView.findViewById(R.id.temperature);
        Humidity = (TextView) rootView.findViewById(R.id.humidity);
        Weather = (TextView) rootView.findViewById(R.id.condition);
        Time = (TextView) rootView.findViewById(R.id.updated_field);

//        Drawable alpha = ((ImageView) rootView.findViewById(R.id.weather)).getDrawable();
//        alpha.setAlpha(100);

        String resulttext1;
        String humidity = "";
        String temperature = "";
        String weather = "";
        String time = "";

        try {
            resulttext1 = new forecastTask().execute().get();
            JSONArray ja1 = new JSONArray("resulttext1");
            for (int i = 0; i < ja1.length(); i++) {
                JSONObject forecast = ja1.getJSONObject(i);

                humidity = "습도" + "\n" + forecast.getString("hudmity") + "%";
                temperature = "온도" + "\n" + forecast.getString("temp") + "℃";
                weather = "" + "\n" + forecast.getString("weather");
                time = "" + "Last update: " + forecast.getString("time");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Humidity.setText(humidity);
        Temperature.setText(temperature);
        Weather.setText(weather);
        Time.setText(time);

        return rootView;
    }

    public static WeatherFragment3 newInstance() {
        Bundle args = new Bundle();

        WeatherFragment3 fragment = new WeatherFragment3();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedlnstanceState) {
        weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weather.ttf");
        super.onCreate(savedlnstanceState);

    }
}

