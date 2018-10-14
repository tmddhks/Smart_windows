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
import com.example.kmc.teamproject.server.windowTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.concurrent.ExecutionException;


public class WeatherFragment2 extends Fragment {

    Typeface weatherFont;
    TextView Temperature;
    TextView Humidity;
    TextView Finedust;


    public WeatherFragment2() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_weather2, container, false);
        Temperature = (TextView) rootView.findViewById(R.id.temperature);
        Humidity = (TextView) rootView.findViewById(R.id.humidity);
        Finedust = (TextView) rootView.findViewById(R.id.finedust);
//        Drawable alpha = ((ImageView) rootView.findViewById(R.id.weather)).getDrawable();
//        alpha.setAlpha(100);

        String resulttext;
        String humidity = "";
        String temperature = "";
        String finedust = "";

        try {
            resulttext = new windowTask().execute().get();
            JSONArray ja = new JSONArray("resulttext");
            for (int i = 0; i < ja.length(); i++) {
                JSONObject window = ja.getJSONObject(i);

                humidity = "습도" + "\n" + window.getString("humidity") + "%";
                temperature = "온도" + "\n" + window.getString("temperature") + "℃";
                finedust = "미세먼지" + "\n" + window.getString("finedust") + "PM";

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
        Finedust.setText(finedust);

        return rootView;
    }

    public static WeatherFragment2 newInstance() {
        Bundle args = new Bundle();

        WeatherFragment2 fragment = new WeatherFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedlnstanceState) {
        weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weather.ttf");
        super.onCreate(savedlnstanceState);
    }
}

