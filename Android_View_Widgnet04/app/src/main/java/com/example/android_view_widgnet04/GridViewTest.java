package com.example.android_view_widgnet04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class GridViewTest extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private     String[] items = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7",
            "Max OS X"};
    private TextView selections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_test);
        selections= (TextView)findViewById(R.id.textView);
        GridView MyGridView=(GridView) findViewById(R.id.mygridview);
        ArrayAdapter<String> myadapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        MyGridView.setAdapter(myadapter);
        MyGridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        this.selections.setText(items[i]);
    }
}