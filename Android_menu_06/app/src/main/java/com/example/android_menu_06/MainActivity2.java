package com.example.android_menu_06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
//xây dựng context menu

public class MainActivity2 extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button=(Button)findViewById(R.id.contextbutton);
        Button backButton=(Button)findViewById(R.id.backbutton);

        //liên kết context menu với ông button trên để khi thao tác với button sẽ hiện ra context menu
        this.registerForContextMenu(button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    // tạo context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuinfo){
        super.onCreateContextMenu(menu,view,menuinfo);

        menu.setHeaderTitle("my context view");
        if(view==this.button) {
            // đoán cái ông inflate này sẽ lấy form items được định dạng bên menu Res
            getMenuInflater().inflate(R.menu.conext_menu, menu);
        }

    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Toast.makeText(MainActivity2.this,"context view was opened",Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }
}