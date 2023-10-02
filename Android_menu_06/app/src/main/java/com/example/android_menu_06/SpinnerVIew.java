package com.example.android_menu_06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerVIew extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String[] items = {"Android","IPhone","WindowsMobile","Blackberry","WebOS",
            "Ubuntu","Windows7","Max OS X","CMPEPHONE1","CMPEPHONE4","CMPEPHONE3","CMPEPHONE2"};
    private TextView SelectedItem;
    private boolean is_spinner_clicked=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_view);
        this.SelectedItem=(TextView)findViewById(R.id.selecteditem);
        //
        Spinner myspinner=(Spinner)findViewById(R.id.spinner);



        ArrayAdapter<String> myapdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,this.items);

        //thiết lập thanh trượt lên xuống cho một danh sách
        //cú pháp setDropDownViewResource(form thanh kéo)
        myapdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        myspinner.setAdapter(myapdapter);

        // thiết lập các thao tác khi chọn mỗi thành phần của một spinner
        myspinner.setOnItemSelectedListener(this);

    }


    @Override
    // thiết lập hàm chọn items của spinner
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        this.SelectedItem.setText(this.items[i]);

    }

    @Override
    // trạng thái khi không chọn cái j
    public void onNothingSelected(AdapterView<?> adapterView) {

        this.SelectedItem.setText("");
        this.is_spinner_clicked=false;
    }
}