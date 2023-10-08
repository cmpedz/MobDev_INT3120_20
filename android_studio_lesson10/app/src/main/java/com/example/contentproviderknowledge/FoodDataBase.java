package com.example.contentproviderknowledge;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;

public class FoodDataBase extends SQLiteOpenHelper {

    private static final String NAME_DATABASE="food_database";
    private static int DATA_BASE_VERSION=1;
    private static final String Name_Table="FoodsDATA";
    private static final String Col_id="ID";
    private static final String Col_food="FOOD";

    public FoodDataBase(Context context) {

        super(context, NAME_DATABASE, null, DATA_BASE_VERSION);

    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
          Log.e("table ","has created");
           String create_table="CREATE TABLE "+Name_Table+"( "+
                                Col_id+" INTEGER PRIMARY KEY, "+
                                Col_food+" VARCHAR(30) NOT NULL);";
           sqLiteDatabase.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Name_Table);
        onCreate(db);
    }

    public static String getNameTable(){
        return Name_Table;
    }
    public static String getCol_id(){
        return Col_id;
    }
    public static String getCol_food(){
        return Col_food;
    }


}
