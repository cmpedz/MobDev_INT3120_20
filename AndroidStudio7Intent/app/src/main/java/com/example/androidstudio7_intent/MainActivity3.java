package com.example.androidstudio7_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    private EditText name;
    private TextView feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button button=(Button) findViewById(R.id.nextbutton);
        name=(EditText) findViewById(R.id.editText);
        feedback=(TextView) findViewById(R.id.feedback);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity3.this,MainActivity2.class);
                intent.putExtra("fullname",name.getText().toString());
                intent.putExtra("message","hello, please say hello to me");
                int requestcode=1;
                startActivityForResult(intent,requestcode);
            }
        });
        Button button2=(Button) findViewById(R.id.backbutton2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    public void onActivityResult(int requestcode,int resultcode,Intent data) {
        super.onActivityResult(requestcode, resultcode, data);
        if (requestcode == 1 && resultcode == RESULT_OK) {

           this.feedback.setText(data.getStringExtra("feedback").toString());
        }
        else{
            this.feedback.setText("?");
        }
    }
}