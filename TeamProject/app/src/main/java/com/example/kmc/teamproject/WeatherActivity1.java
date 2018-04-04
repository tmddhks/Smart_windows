package com.example.kmc.teamproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class WeatherActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        //상단바의 이미지 버튼을 누를 시 리모컨 페이지로 넘어갈 수 있도록 하는 버튼 클릭 이벤트
//        ImageButton remoteController = (ImageButton) findViewById(R.id.ibRemoteController);
//        remoteController.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(WeatherActivity1.this, RemoteControllerActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}