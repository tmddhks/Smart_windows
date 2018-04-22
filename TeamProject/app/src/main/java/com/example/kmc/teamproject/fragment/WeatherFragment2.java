package com.example.kmc.teamproject.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.kmc.teamproject.R;
import com.example.kmc.teamproject.server.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class WeatherFragment2 extends Fragment {

    TextView sensor;
//    Button update;

    public WeatherFragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_weather2, container, false);
        sensor = (TextView) rootView.findViewById(R.id.tvSensor);
//        센서값 업데이트
//        update = (Button) rootView.findViewById(R.id.update);

        String resulttext;
        String result = "";
        try {
            resulttext = new Task().execute().get();
            result = "";
            JSONArray ja = new JSONArray(resulttext);
            for (int i = 0; i < ja.length(); i++) {
                JSONObject window = ja.getJSONObject(i);
                result += "Temparature: " + window.getString("temparature") + " Moisture: " + window.getString("moisture") +
                        " Finedust: " + window.getString("finedust") + "\n";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        sensor.setText(result);

// 센서값 업데이트 이벤트
//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String resultText;
//                String result = "";
//                try {
//                    resultText = new Task().execute().get();
//                    JSONArray ja = new JSONArray(resultText);
//                    for (int i = 0; i < ja.length(); i++) {
//                        JSONObject window = ja.getJSONObject(i);
//                        result += "Temparature: " + window.getString("temparature") + " Moisture: " + window.getString("moisture") +
//                                " Finedust: " + window.getInt("finedust") + "\n";
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//                sensor.setText(result);
//            }
//        });
        // Inflate the layout for this fragment
        return rootView;
    }

    public static WeatherFragment2 newInstance() {
        Bundle args = new Bundle();

        WeatherFragment2 fragment = new WeatherFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedlnstanceState) {
        super.onCreate(savedlnstanceState);
    }
}

