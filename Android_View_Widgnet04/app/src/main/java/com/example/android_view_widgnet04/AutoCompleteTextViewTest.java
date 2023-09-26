package com.example.android_view_widgnet04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
public class AutoCompleteTextViewTest extends AppCompatActivity implements TextWatcher {
    String[] items = {"Android","IPhone","WindowsMobile", "Blackberry","WebOS","Ubuntu","Windows7", "Max OS X"};


@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_text_view);
        AutoCompleteTextView Myautocompletion =(AutoCompleteTextView)  findViewById(R.id.autoCompleteTextView);
          ArrayAdapter<String> myadapter=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,items);
          Myautocompletion.setAdapter(myadapter);

          //thêm chức năng gợi ý text cho AutoCompleteTextView
        Myautocompletion.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}