package com.example.sensorinandroidstudio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
/* -đầu tiên ta cần phải hiểu sensor trong android studio sẽ cung cấp một số cảm biến trong android để có
* thể phục cụ như cầu của người lập trình như : xác định vị trí của thiết bị, xacs định hướng đi dựa vào từ
* trường, xác định nhiệt độ của môi trường xung quanh,...
*
* */
// dưới đây sẽ là ví dụ cho sensor dùng để xasc định vị trí của thiết bị hiện tại

/* -Sensor sẽ gồm các thành phaanf chính như :
* + SensorManager
* + Sensor
* + SensorEvent
* + SensorEventListener
* */
public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor checkPos=null;
    private TextView CountStep;
    private int is_start=0;
    private long sum=0;
    private long save=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACTIVITY_RECOGNITION},
                    0);
        }



        CountStep=(TextView)findViewById(R.id.textView8);

        //khởi tạo sensor dùng để đếm bước đi
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);


    }
    @Override
    protected void onResume() {
        super.onResume();
        //đăng kí sự kiện lắng nghe những thay đổi của sensor đếm bước đi mỗi khi ta di chuyển
        checkPos=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(checkPos !=null) {
            sensorManager.registerListener(this, checkPos, sensorManager.SENSOR_DELAY_UI);
            Log.e("sensor","is register");
        }
        else {
            Toast.makeText(this,"this sensor doesnt exsist !",Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onPause() {
        super.onPause();
        // luôn luôn nhớ hủy đăng kí sensor với sensorEventListener để tránh lãng phí bộ nhớ
        Log.e("sensor","is unregister");
        sensorManager.unregisterListener(this);
        is_start=0;
        sum=0;

    }


    //thông báo về sự thay đổi dữ liệu của cảm biến
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
            // dựa vào sensor event để ta có thể lấy dữ liệu từ sensor hiện tại và hiện lên màn hình
        if(sensorEvent.sensor == checkPos ) {
            if(is_start == 0){
                Log.e("reset start","ok");
                save=(long)sensorEvent.values[0];
                is_start++;
            }
            else{
                sum=(long)sensorEvent.values[0]-save;
            }
            this.CountStep.setText(String.valueOf(sum));
        }
    }

    //thông báo sự thay đổi về độ chính xác của cảm biến
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}