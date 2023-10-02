package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SQLite extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        textView=(TextView) findViewById(R.id.textView);
        Student student1=new Student(1,"nguyen van B");
        Student student2=new Student(2,"nguyen van A");
        Student student3=new Student(3,"nguyen van C");
        SQLiteHandler mySQLManagement=new SQLiteHandler(this);
        mySQLManagement.addStudent(student1);
        mySQLManagement.addStudent(student2);
        mySQLManagement.addStudent(student3);
//        mySQLManagement.deleteStudent(student3.getId());

        textView.setText(mySQLManagement.getStudent(3)+".");

        Button button=(Button) findViewById(R.id.quitbuttonsql);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}