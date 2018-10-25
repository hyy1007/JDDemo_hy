package com.example.jddemo_hy.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jddemo_hy.R;

public class MainActivity extends AppCompatActivity {

    private int time=3;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                if (time>0){
                    time--;
                    handler.sendEmptyMessageDelayed(0,1000);
                }else {
                    Intent intent=new Intent(MainActivity.this,ShowActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler.sendEmptyMessageDelayed(0,1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
