package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InnerSave extends AppCompatActivity {
    private String filename = "test.txt";
    private String filepath = "HelloCheems";
    File myInternalFile;
    Button btnSave, btnDisplay;
    EditText myInputText;
    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_save);


        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        //dùng contextwrapper.getDir để có thể gọi thư mục từ filepath(nếu không có sẽ tự động tạo ra)
        File directory = contextWrapper.getDir(filepath, Context.MODE_PRIVATE);
        //sau đó gắn file .txt cần truy cập trong th mục vào myInternalFile
        myInternalFile = new File(directory, filename);
        myInputText=(EditText) findViewById(R.id.myInputText);
        responseText=(TextView)findViewById(R.id.responseText);

        btnDisplay=(Button) findViewById(R.id.btnDisplay);
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myData = "";
                try {
                    //Đọc file
                    FileInputStream fis = new FileInputStream(myInternalFile);
                    DataInputStream in = new DataInputStream(fis);
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String strLine;
                    //Đọc từng dòng
                    while ((strLine = br.readLine()) != null) {
                        myData = myData + strLine.toString();
                    }
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                myInputText.setText(myData);
                responseText.setText("Lấy dữ liệu từ bộ nhớ trong");
            }
        });



      btnSave=(Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //Mở file
                    FileOutputStream fos = new FileOutputStream(myInternalFile);
                    //Ghi dữ liệu vào file
                    fos.write(myInputText.getText().toString().getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                myInputText.setText("");
                responseText.setText("Đã được lưu vào bộ nhớ trong");
            }
        });
        Button quit=(Button) findViewById(R.id.innersavequitbutton);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}