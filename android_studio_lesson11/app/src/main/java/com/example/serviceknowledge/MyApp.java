package com.example.serviceknowledge;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class MyApp extends Application {
    //xác định id kênh chứa thông báo
    public static final String Notification_channel_id="notification_channel";
    @Override
    public void onCreate() {
        super.onCreate();
        creataChannelNotification();
    }

    private void creataChannelNotification() {
        //taạo một kênh với các tham số truyền vào là : id kênh, tên kênh,thứ tự ưu tiên cho thông báo
        // có thể xem trogn phần cài đặt thông báo của ứng dụng và sẽ xuất hiện tên kênh : "my Notification channel"
        NotificationChannel channel=new NotificationChannel(Notification_channel_id,
                "my Notification channel",
                NotificationManager.IMPORTANCE_DEFAULT);

        //tạo một quản lý cho các thông báo => cài đặt kênh thông báo vào hệ thống
        NotificationManager manager=getSystemService(NotificationManager.class);
        if(manager!=null){
            //tạo kênh chứa thoong báo
            manager.createNotificationChannel(channel);
        }

    }
}
