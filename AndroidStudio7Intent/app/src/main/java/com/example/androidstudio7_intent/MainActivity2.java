package com.example.androidstudio7_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private Button button;
    private TextView message;
    private String fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button=(Button) findViewById(R.id.backbutton);
        message=(TextView) findViewById(R.id.textView2);

        //lấy intent đã kết nối hai cái mainactivity 2,3 với nhau
        Intent intent=this.getIntent();
        this.message.setText(intent.getStringExtra("message"));
        fullname=intent.getStringExtra("fullname");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    @Override
    public void finish(){
            Intent data=new Intent();
            data.putExtra("feedback","OK hello, "+fullname+ " ,how are you?");

            //trả về result code và intent data
            setResult(RESULT_OK,data);
            super.finish();
    }
}