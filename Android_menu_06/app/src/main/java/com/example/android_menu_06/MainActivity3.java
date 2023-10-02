package com.example.android_menu_06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

// xây dựng popup menu
public class MainActivity3 extends AppCompatActivity {
    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        button1=(Button)findViewById(R.id.clickme);
         button2=(Button)findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1clicked();
            }
        });
        Button Back=(Button) findViewById(R.id.button3);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void button1clicked(){
        //hàm này có nhiệm vụ tạo popup menu mỗi khi người dùng ấn nút

        // tạo anchor(neo) để neo popup menu lên button 2
        PopupMenu popupMenu=new PopupMenu(this,this.button2);
        // đoán cái ông inflate này sẽ lấy form items được định dạng bên menu Res
        popupMenu.inflate(R.menu.popup_menu);


        //thiết lập sk chọn item của popup menu
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Toast.makeText(MainActivity3.this, "you choose some items in popup menu", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        // show popup menu
        popupMenu.show();
    }
}