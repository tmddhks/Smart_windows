package com.example.kmc.teamproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.kmc.teamproject.R;

public class LoadingActivity extends AppCompatActivity {
    private ImageView imWindow;
    private Animation aniLoding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        initView();
        startLoading();
    }

    private void initView() {
        imWindow = (ImageView) findViewById(R.id.img_loding);
        aniLoding = AnimationUtils.loadAnimation(this, R.anim.loading);
        imWindow.setAnimation(aniLoding);
    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), WeatherActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }
}
