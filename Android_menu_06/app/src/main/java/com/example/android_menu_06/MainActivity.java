package com.example.android_menu_06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;
// xây dựng option menu
public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button) findViewById(R.id.button_move_to_context_layout);
        Button button2=(Button)findViewById(R.id.popupbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainActivity3.class);
                startActivity(intent);
            }
        });
    }

    //khởi tạo option menu lên màn hình
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Gọi hàm getMenuInflater() để tạo một MenuInflater
        //Trong hàm inflate() tham số thứ nhất là menu cần lấy ra để tạo, tham số thứ 2 là OptionMenu.
        // đoán cái ông inflate này sẽ lấy form items được định dạng bên menu Res
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    //thiết lập các sk khi bấm nút chọn item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       int id=item.getItemId();
       if(id==R.id.listview){
           Intent intent=new Intent(this,LISTVIEW.class);
           startActivity(intent);
       }
        if(id==R.id.spinnerview){
            Intent intent=new Intent(this,SpinnerVIew.class);
            startActivity(intent);
        }
        if(id==R.id.homework){
            Intent intent=new Intent(this,HomeWork4.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }



}