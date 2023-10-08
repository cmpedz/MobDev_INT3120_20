package com.example.serviceknowledge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //cần dùng đến service connection để có thể thực thi việc kết nối đến service
    private boolean IsBound=false;
    private BoundService service=new BoundService();
    private Button startBoundService;
    private Button stopBoundService;
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("service ","connected");

                //ép ibinder về theo kết nối mybinder của mình
                BoundService.MyBinder binder=(BoundService.MyBinder)iBinder;

                //kết nối binder trên với service của mình
                service=binder.getBoundService();
                service.startMusic();
                IsBound=true;
        }

        //trường hợp mà onServiceDisconnected này diễn ra khi boundService của mình bị dừng đột ngột : như hệ thống cần bộ nhớ nên kill bound Service này

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("service ","disconnected");
            IsBound=false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText cin=(EditText) findViewById(R.id.textView);
        Button StartService=(Button) findViewById(R.id.button);
        StartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent service=new Intent(MainActivity.this,MyService.class);
                service.putExtra("data",cin.getText().toString());
                startService(service);
            }
        });

        startBoundService=(Button) findViewById(R.id.startboundservice);
        stopBoundService=(Button) findViewById(R.id.destroyboundservice);
        startBoundService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("start bound service","ok");
                Intent intent=new Intent(MainActivity.this,BoundService.class);
                //kết nối activity đến boundserice
                bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
            }
        });

        stopBoundService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(IsBound){
                    unbindService(serviceConnection);
                    IsBound=false;
                }
            }
        });
    }
}