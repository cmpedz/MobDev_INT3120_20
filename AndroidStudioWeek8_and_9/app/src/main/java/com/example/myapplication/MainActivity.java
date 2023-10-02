package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private MyBroadCast myBroadCast=new MyBroadCast();
    // khởi tạo intent filter để liên kết nó với broadcast dùng để xử lý các dữ liệu của intent
    private IntentFilter intentFilter=new IntentFilter("com.example.myapplication");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1=(Button)findViewById(R.id.sharedpreferences);

        //đây là cách khởi tạo broadcast receiver trong code : chỉ dùng đến broadcast khi mở ứng dụng
        //liên kết intentfilter và broadcast
        registerReceiver(myBroadCast,intentFilter);
        Intent broadcastIntent=new Intent("com.example.myapplication");
        broadcastIntent.putExtra("announcement","welcome to my app.");
        sendBroadcast(broadcastIntent);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SharedPreferncesTest.class);
                startActivity(intent);
            }
        });

        Button button2=(Button)findViewById(R.id.sqlbutton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SQLite.class);
                startActivity(intent);
            }
        });
        Button button3=(Button) findViewById(R.id.InnerSavebutton);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,InnerSave.class);
                startActivity(intent);
            }
        });
        Button button4=(Button) findViewById(R.id.outerbutton);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,OuterSave.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onStop() {
        super.onStop();
        // bỏ liên kết với broadcast mỗi khi tắt ứng dụng

        unregisterReceiver(myBroadCast);
        Log.e("unregister","ok");
    }
}