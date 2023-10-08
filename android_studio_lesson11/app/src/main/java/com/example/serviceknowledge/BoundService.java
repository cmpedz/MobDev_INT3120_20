package com.example.serviceknowledge;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class BoundService extends Service {

    //khi tạo ra một service mới thì luôn khai báo nó trong android manifest để android biết được
    private MyBinder binder=new MyBinder();
    private MediaPlayer music;

    //khai báo một kết nối mybinder để có thể kết nối giữa service với clients
    public class MyBinder extends Binder{
        public BoundService getBoundService(){
            return BoundService.this;
        }
    }
    @Override
    public void onCreate(){
        Log.e("bound service ","on create");
        super.onCreate();
    }
    @Override
    public void onDestroy(){
        Log.e("bound service ","on destroy");

        super.onDestroy();
        if(music!=null){
            //giải phóng music
            music.release();
            music=null;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("bound service ","on bind");
        return binder;
    }
    @Override
    public boolean onUnbind(Intent intent){
        Log.e("bound service ","un bind");
        return super.onUnbind(intent);
    }
    public void startMusic(){
        if(music==null){
            Log.e("start music : ","ok");
            music=MediaPlayer.create(getApplicationContext(),R.raw.sansjoke);
            music.start();
        }
    }

}
