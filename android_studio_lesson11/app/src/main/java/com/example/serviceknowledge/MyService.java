package com.example.serviceknowledge;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    @Override
    public void onCreate(){


    }
    @Override
    public void onDestroy(){
        super.onDestroy();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        Log.e("service status :","onStartCommand");
        String s=intent.getStringExtra("data");
        //khởi tạo notification bằng NotificationCompat.Builder(context, id của kênh notificaiton) dùng các hàm set để tạo thông báo thích hợp
        Notification announcement=new NotificationCompat.Builder(this,MyApp.Notification_channel_id)
                .setContentTitle("Service Says :").
                setContentText(s).
                setSmallIcon(R.drawable.ic_launcher_foreground).build();

        //sau khi tạo ra thông báo thì sẽ cho service chạy notification trên foreground
        startForeground(100,announcement);
        // thêm stopself để sau khi service hoàn thành nhiệm vụ thông báo thì ông sẽ tự tắt luôn
        //nếu không có thì ông cứ chạy cho đến khi nào bộ nhớ thiếu thì tắt hoặc ta sẽ dùng một client khác để tắt
        stopSelf();
        return START_NOT_STICKY;
    }
}
