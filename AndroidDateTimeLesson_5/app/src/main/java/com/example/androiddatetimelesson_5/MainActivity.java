package com.example.androiddatetimelesson_5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    // tạo một calendar từ java và cập nhập thời gian hiện tại
    private Calendar mycalendar=Calendar.getInstance();

    //tạo chức năng d cho DatePickerDialog khi nhấn thay đổi ngày trên giao diện của nó
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
            // thay đổi ngày, tháng, năm cho mycalendar khi ta chọn trên DatePickerDialog
            mycalendar.set(y,m,d);
        }

    };

    //tạo chức năng t chọn time cho TimePickerDialog
    TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int h, int m) {
            // cũng như DateDialog thì ông dưới cũng hoạt động tương tự như trên
            mycalendar.set(Calendar.HOUR_OF_DAY,h);
            mycalendar.set(Calendar.MINUTE,m);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Datebutton=(Button)findViewById(R.id.datebutton);
        Button Timebutton=(Button) findViewById(R.id.timebutton);
        Datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mỗi khi ấn nút lật tức khởi tạo ông DatePickerDialog lên màn hình MainActivity
                new DatePickerDialog(MainActivity.this,d,mycalendar.get(Calendar.YEAR),mycalendar.get(Calendar.MONTH),
                        mycalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Timebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(MainActivity.this,t,mycalendar.get(Calendar.HOUR_OF_DAY),mycalendar.get(Calendar.MINUTE),false).show();
            }
        });

    }
}