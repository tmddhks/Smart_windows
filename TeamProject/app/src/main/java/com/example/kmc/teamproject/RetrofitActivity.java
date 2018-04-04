package com.example.kmc.teamproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends Activity {
    private Retrofit retrofit;
    private TextView textView;

    private final String BASE_URL = "http://4dfa4f7d.ngrok.io/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        init();

        // GitHub API 인터페이스 생성
        GitHub gitHub = retrofit.create(GitHub.class);
        // 인터페이스에 구현한 메소드인 contributors에 param 값을
        // 넘기는 요청 만듬
        Call<List<Window>> call = gitHub.windows("smart");

        // 앞서만든 요청을 수행
        call.enqueue(new Callback<List<Window>>() {
            @Override
            // 성공시
            public void onResponse(Call<List<Window>> call, Response<List<Window>> response) {
                List<Window> windows = response.body();
                // 받아온 리스트를 순회하면서
                for (Window window : windows) {
                    // 텍스트 뷰에 window 정보를 붙임
                    textView.append(window.window);
                }
            }

            @Override
            // 실패시
            public void onFailure(Call<List<Window>> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this, "정보받아오기 실패", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    public void init() {
        textView = (TextView) findViewById(R.id.textView);
        // GSON 컨버터를 사용하는 REST 어댑터 생성
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}