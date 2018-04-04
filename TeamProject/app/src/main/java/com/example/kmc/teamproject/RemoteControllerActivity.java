package com.example.kmc.teamproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class RemoteControllerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_controller);

        //리모컨에서 긴급하게 전화해야 할 시 이미지 버튼을 클릭하면 다이얼 모드로 넘어감
        ImageButton HelpCall = (ImageButton) findViewById(R.id.ibHelpCall);
        HelpCall.setOnClickListener(new ImageButton.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01012345678"));
                startActivity(intent);
            }
        });

        //close button
        ImageButton close = (ImageButton) findViewById(R.id.ibClose);
        close.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RemoteControllerActivity.this, "닫힘", Toast.LENGTH_SHORT).show();
            }
        });

        //open button
        ImageButton open = (ImageButton) findViewById(R.id.ibOpen);
        open.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RemoteControllerActivity.this, "열림", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
