package com.example.kmc.teamproject.activity;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.kmc.teamproject.R;

public class RemoteControllerActivity extends AppCompatActivity {

    ImageButton helpcall;
    ImageButton btnopen;
    ImageButton btnclose;
    ImageView imageview = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_controller);
        imageview = (ImageView) findViewById(R.id.imageView);
        helpcall = (ImageButton) findViewById(R.id.helpcall);
        btnopen = (ImageButton) findViewById(R.id.open);
        btnclose = (ImageButton) findViewById(R.id.close);

        helpcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"));
                startActivity(intent);
            }
        });
        btnopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageview.setImageResource(R.drawable.open);
            }
        });
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageview.setImageResource(R.drawable.close);
            }
        });
    }
}