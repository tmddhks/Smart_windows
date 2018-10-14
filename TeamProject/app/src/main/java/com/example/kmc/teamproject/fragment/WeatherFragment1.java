package com.example.kmc.teamproject.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kmc.teamproject.R;
import com.example.kmc.teamproject.weather.CityPreference;
import com.example.kmc.teamproject.weather.RemoteFetch;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment1 extends Fragment {

    static public String citychange = "ansan";
    Typeface weatherFont;
    TextView cityField;
    TextView condition;
    TextView weatherIcon;
    TextView updatedField;
    String a = "";
    TextView pressure;
    TextView humidity;
    TextView currentTemperatureField;
    TextView wind_speed;
    TextView temp_min;
    TextView temp_max;
    Handler handler;

    public WeatherFragment1() {
        // Required empty public constructor
        handler = new Handler();
    }

    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather1, container, false);
        humidity = (TextView) rootView.findViewById(R.id.humidity);
        pressure = (TextView) rootView.findViewById(R.id.pressure);
        currentTemperatureField = (TextView) rootView.findViewById(R.id.current_temperature_field);
        wind_speed = (TextView) rootView.findViewById(R.id.wind);
        temp_min = (TextView) rootView.findViewById(R.id.temp_min);
        temp_max = (TextView) rootView.findViewById(R.id.temp_max);
        cityField = (TextView) rootView.findViewById(R.id.city_field);
        updatedField = (TextView) rootView.findViewById(R.id.updated_field);
        condition = (TextView) rootView.findViewById(R.id.condition);
        weatherIcon = (TextView) rootView.findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weather.ttf");
        updateWeatherData(new CityPreference(getActivity()).getCity(citychange));
    }

    private void updateWeatherData(final String city) {
        new Thread() {
            public void run() {
                final JSONObject json = RemoteFetch.getJSON(getActivity(), city);
                if (json == null) {
                    handler.post(new Runnable() {
                        public void run() {
                            Toast.makeText(getActivity(),
                                    getActivity().getString(R.string.place_not_found),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        public void run() {
                            renderWeather(json);
                        }
                    });
                }
            }
        }.start();
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void renderWeather(JSONObject json) {
        try {
            cityField.setText(json.getString("name").toUpperCase(Locale.KOREA) +
                    ", " +
                    json.getJSONObject("sys").getString("country"));

            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");
            JSONObject wind = json.getJSONObject("wind");
            a = details.getString("description").toUpperCase(Locale.KOREA);
            if (a.equals("SCATTERED CLOUDS")) a = "흐림";
            if (a.equals("CLEAR SKY")) a = "맑음";
            if (a.equals("FEW CLOUDS")) a = "조금 흐림";
            if (a.equals("BROKEN CLOUDS")) a = "매우 흐림";
            if (a.equals("SHOWER RAIN")) a = "비";
            if (a.equals("RAIN")) a = "비";
            if (a.equals("THUNDERSTORM")) a = "천둥, 번개";
            if (a.equals("SNOW")) a = "눈";
            if (a.equals("MIST")) a = "안개";
            condition.setText(a);

            //바람
            wind_speed.setText(wind.getString("speed") + "m/s");
            //습도
            humidity.setText(main.getString("humidity") + "%");
            //기압
            pressure.setText(main.getString("pressure") + "hPa");
            //최고온도
            temp_max.setText(String.format("%.1f", main.getDouble("temp_max")) + "°c");
            //최저온도
            temp_min.setText(String.format("%.1f", main.getDouble("temp_min")) + "°c");
            //온도
            currentTemperatureField.setText(String.format("%.1f", main.getDouble("temp")) + "°c");

            DateFormat df = DateFormat.getDateTimeInstance();
            String updatedOn = df.format(new Date(json.getLong("dt") * 1000));
            updatedField.setText("Last update: " + updatedOn);

            setWeatherIcon(details.getInt("id"),
                    json.getJSONObject("sys").getLong("sunrise") * 1000,
                    json.getJSONObject("sys").getLong("sunset") * 1000);

        } catch (Exception e) {
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }
    }

    private void setWeatherIcon(int actualId, long sunrise, long sunset) {
        int id = actualId / 100;
        String icon = "";
        if (actualId == 800) {
            long currentTime = new Date().getTime();
            if (currentTime >= sunrise && currentTime < sunset) {
                icon = getActivity().getString(R.string.weather_sunny);
            } else {
                icon = getActivity().getString(R.string.weather_clear_night);
            }
        } else {
            switch (id) {
                case 2:
                    icon = getActivity().getString(R.string.weather_thunder);
                    break;
                case 3:
                    icon = getActivity().getString(R.string.weather_drizzle);
                    break;
                case 7:
                    icon = getActivity().getString(R.string.weather_foggy);
                    break;
                case 8:
                    icon = getActivity().getString(R.string.weather_cloudy);
                    break;
                case 6:
                    icon = getActivity().getString(R.string.weather_snowy);
                    break;
                case 5:
                    icon = getActivity().getString(R.string.weather_rainy);
                    break;
            }
        }
        weatherIcon.setText(icon);
    }

    public void changeCity(String city) {
        updateWeatherData(city);
    }

    public static WeatherFragment1 newInstance() {
        Bundle args = new Bundle();

        WeatherFragment1 fragment = new WeatherFragment1();
        fragment.setArguments(args);
        return fragment;
    }

}
