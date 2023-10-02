package com.example.androidstudio7_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Phonebutton=(Button)findViewById(R.id.phonebutton);
        Button Searchbutotn=(Button)findViewById(R.id.searchbutton);
        Button Imagebutton=(Button)findViewById(R.id.imagebutton);
        Button sendbutton2=(Button)findViewById(R.id.sendbutton2);



        Phonebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sử dụng intent không tường minh để yêu cầu hệ thống gọi số nào đó
                Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:090-253-522"));
                startActivity(intent);
            }
        });


        Searchbutotn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_WEB_SEARCH);

                //thêm câu hỏi truy vấn cho ông intent tra web để mỗi khi ấn vào tự search dữ liệu đó trên gg
                intent.putExtra(SearchManager.QUERY,"how to code hello world ");
                startActivity(intent);
            }
        });

//

        // dùng intent để gửi tin nhắn cho ai đó
        sendbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_SENDTO,Uri.parse("sms:998 112"));
                intent.putExtra("sms body", "hello bro, are you free this afternoon ? ");
                startActivity(intent);
            }
        });

        //dùng intent để hiển thị ảnh
        Imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setType("image/pictures/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivity(intent);
            }
        });
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainActivity3.class);
                startActivity(intent);
            }
        });

    }
}