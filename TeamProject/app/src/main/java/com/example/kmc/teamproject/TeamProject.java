package com.example.kmc.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class TeamProject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_project);

        Intent intent = new Intent(TeamProject.this, WeatherActivity.class);
        startActivity(intent);
    }
}
