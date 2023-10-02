package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SharedPreferncesTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prefernces_test);

        //khởi tạo một ô nhớ trong sharedPreferences với tên save1
        SharedPreferences sharedPreferncesTest=getSharedPreferences("save1",MODE_PRIVATE);
        //khởi tạo hàm edit cho save1
        SharedPreferences.Editor editor=sharedPreferncesTest.edit();


        //hàm put với các tham số lần lượt là tên key, giấ trị key
        editor.putString("name","cmpedz");

        //Luôn commit sau khi đã thêm thành công data
        editor.commit();

        TextView textView=(TextView) findViewById(R.id.textView2);

        // hàm get với tham số lần lượt là : tên key, giá trị mặc định khi trả về
        textView.setText(sharedPreferncesTest.getString("name",""));

        Button button=(Button) findViewById(R.id.quitbuttonshared);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}