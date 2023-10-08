package com.example.contentproviderknowledge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ContentValues[] foodlist=new ContentValues[3];
    private int so_phan_tu=0;
    private FoodDataBase db;
    private TextView FoodName;
    private EditText foodcin;
    private TextView id_food;
    private EditText id_cin;
    private TextView result;
    private EditText foodCout;
    private Button insert;
    private Button get;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new FoodDataBase(this);
        FoodName=(TextView) findViewById(R.id.FoodName);
        foodcin=(EditText) findViewById(R.id.FoodNameCin);
        id_food=(TextView)findViewById(R.id.id) ;
        id_cin=(EditText) findViewById(R.id.idCin);
        result=(TextView) findViewById(R.id.result);
        foodCout=(EditText) findViewById(R.id.FoodNameCout);
        insert=(Button) findViewById(R.id.addbutton);
        get=(Button) findViewById(R.id.getbutton);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!foodcin.getText().toString().isEmpty() && !id_cin.getText().toString().isEmpty()){
                    ContentValues values =new ContentValues();
                    values.put(db.getCol_food(),foodcin.getText().toString());
                    values.put(db.getCol_id(),id_cin.getText().toString());
                    getContentResolver().insert(ContractInFoodContentProvider.getContentUri(),values);


                    //khi ta muốn content resolver chèn một dãy dữ liệu
//                    getContentResolver().bulkInsert(ContractInFoodContentProvider.getContentUri(),foodlist);
//                    so_phan_tu=0;

                }
            }
        });

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!foodCout.getText().toString().isEmpty()){
                    String projections[]={db.getCol_food()};
                    String selecitons="id ="+foodCout.getText().toString();
                    Cursor cursor=getContentResolver().query(ContractInFoodContentProvider.getContentUri(),
                            projections,selecitons,null,null);
                    if(cursor!=null) {
                        cursor.moveToFirst();
                        try {
                            result.setText(cursor.getString(0));
                        }catch (Exception e){
                            result.setText("not found");
                        }

                    }

                }
            }
        });
        Button plus=(Button) findViewById(R.id.plusbutton);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!foodCout.getText().toString().isEmpty() && !id_cin.getText().toString().isEmpty()){
                    ContentValues values =new ContentValues();
                    values.put(db.getCol_food(),foodcin.getText().toString());
                    values.put(db.getCol_id(),id_cin.getText().toString());
                    foodlist[so_phan_tu]=values;
                    so_phan_tu++;

                }
            }
        });
    }
}