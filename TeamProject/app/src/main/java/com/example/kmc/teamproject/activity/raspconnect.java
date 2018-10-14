package com.example.kmc.teamproject.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kmc.teamproject.R;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.PrintStream;
import java.util.Properties;

public class raspconnect extends Activity {
    String controlcmd1 = "java -Djava.library.path=/usr/lib/jni -cp /usr/share/java/RXTXcomm.jar:. serial";    // 라즈베리파이 내 아두이노 제어 자바 실행 명령어
    String controlcmd2 = "rm -f /var/lock/LCK*";    //허가 없이 라즈베리 파이를 사용하도록 열어둠

    Button openbtn, connect, closebtn;
    JSch localJsch;
    Session session;
    Channel channel1;
    Channel channel2;
    PrintStream out;
    String ip;

    @SuppressLint({"StaticFieldLeak", "ClickableViewAccessibility"})
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rasp_connect);

        Intent intent1 = getIntent();
        ip = intent1.getStringExtra("ip").toString();

        connect = (Button) findViewById(R.id.connect);
        openbtn = (Button) findViewById(R.id.open);
        closebtn = (Button) findViewById(R.id.close);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonclicked();
            }
        });

        /*버튼리스너*/

        openbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    System.out.println("2");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    System.out.println("java -Djava.library.path=/usr/lib/jni -cp /usr/share/java/RXTXcomm.jar:. serial");
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void buttonclicked() {
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        localJsch = new JSch();

                        session = localJsch.getSession("pi", ip, 22);
                        session.setPassword("dn69481008");

                        Properties localProperties = new Properties();
                        localProperties.put("StrictHostKeyChecking", "no");

                        session.setConfig(localProperties);
                        session.connect();

                        channel1 = session.openChannel("exec"); //터미널띄움
                        channel2 = session.openChannel("exec");   //channel 띄움  - >  터미널 띄운다

                        System.out.println("==> Connected to raspberry");
                        channel1 = session.openChannel("exec");
                        ((ChannelExec) channel1).setCommand(controlcmd2); // usb locking 해제 커맨드 설정
                        channel1.connect(); //커맨더실행

                        channel1 = session.openChannel("exec");
                        ((ChannelExec) channel1).setErrStream(System.err); // err 스트림 설정  라즈베리파이에서 발생한 에러를 안드로이드 스튜디오에서 확인 할 수 있다.
                        ((ChannelExec) channel2).setErrStream(System.err);
                        channel1.setInputStream(System.in);                // channel 1  에서 실행한 명령어의 결과를 안드로이드 스튜디오에서 확인 할 수 있다.

//                        ((ChannelExec) channel1).setCommand(controlcmd2);
                        ((ChannelExec) channel2).setCommand(controlcmd1);  //
//                        channel1.connect();                                // 커맨드 실행
                        channel2.connect();                                // 커맨드 실행

                        out = new PrintStream(channel2.getOutputStream()); // 버튼 제어를 위한 outpustream 지정

                    } catch (Exception localException1) {
                        localException1.printStackTrace();
                        return;
                    }
                    try {
                        Thread.sleep(100L);
                        return;
                    } catch (Exception localException2) {
                    }
                }
            }
        }.start();
    }
}
