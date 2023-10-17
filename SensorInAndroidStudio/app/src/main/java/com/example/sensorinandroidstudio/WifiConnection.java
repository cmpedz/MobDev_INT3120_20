package com.example.sensorinandroidstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class WifiConnection extends AppCompatActivity {

    //trogn phần này chúng ta sẽ làm việc và thao tác  trên wifi dựa vào các chức năng đã được android studio cung cấp


    private TextView currentWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_connection);
        currentWifi=(TextView) findViewById(R.id.currentwifi);
        Button button=(Button)findViewById(R.id.button);

        //dùng lớp quản lý wifi
        WifiManager wifiManager= (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);

        //lấy ra thông tin wifi đang kết nối hiện tại


        if(wifiManager.isWifiEnabled()){
            button.setText("Turn off Wifi");
            currentWifi.setText(wifiManager.getConnectionInfo().getBSSID().toString());
        }
        else{
            button.setText("Turn on Wifi");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wifiManager.isWifiEnabled()){
                    Log.e("wifi"," turn off");
                    button.setText("Turn off Wifi");
                }
                else{
                    Log.e("wifi"," turn on");
                    button.setText("Turn on Wifi");
                }
            }
        });


    }
}