package com.example.kmc.teamproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kmc.teamproject.R;

public class raspip extends AppCompatActivity{

    Button connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.rasp_ip);
        connect = (Button) findViewById(R.id.button);

    }

    public void button_click(View v) {

        EditText editText = (EditText) findViewById(R.id.editText);
        String ip = editText.getText().toString();
        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
        intent1.putExtra("ip", ip);
        startActivity(intent1);
        finish();

    }
}
