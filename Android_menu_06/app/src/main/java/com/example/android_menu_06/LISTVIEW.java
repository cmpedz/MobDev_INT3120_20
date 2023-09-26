package com.example.android_menu_06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LISTVIEW extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        //tạo dữ liệu cho một danh sách
        String mobileArray []= {"Android","IPhone","WindowsMobile",
                "Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
        //tạo Apdapter cho listview
        // cú pháp new ArrayAdapter(context, kiểu form danh sách, dữ liệu truyền vào )
        ArrayAdapter<String> mylist=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mobileArray);

        // khởi tạo một list view bên layout gắn id r đem sang bên activity này để kiểm soát
        ListView mylistview=(ListView) findViewById(R.id.idmylistview);

        //gắn adapter cho listview
        mylistview.setAdapter(mylist);
        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(LISTVIEW.this,mobileArray[i]+" selected",Toast.LENGTH_SHORT).show();

            }
        });
    }
}