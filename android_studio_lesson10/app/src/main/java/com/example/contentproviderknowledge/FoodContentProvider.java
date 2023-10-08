package com.example.contentproviderknowledge;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FoodContentProvider extends ContentProvider {
    private ContractInFoodContentProvider contract=new ContractInFoodContentProvider();
    private FoodDataBase db;

    @Override
    public boolean onCreate() {
        Log.e("content provider has ","created");
        db=new FoodDataBase(getContext());
        return true;
    }

    /* -trong hàm truy vấn query có các thuộc tính sau :
    *   -uri: xác định cơ sở dữ liệu cần truy vấn
        -projection: một hàng trong một bảng dữ liệu
        -selection: lệnh “where” được thực thi
        -selectionArgs: các tham số bổ sung cho thao tác truy vấn chọn
        -sortOrder: thứ tự cho các hàng được chọn
    */

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selections, @Nullable String[] selectionsArgs, @Nullable String sortOrder) {
         int UriType=ContractInFoodContentProvider.getsURIMatcher().match(uri);
         SQLiteDatabase readFunction=db.getReadableDatabase();
        Cursor cursor;
         switch (UriType){
             case ContractInFoodContentProvider.ALL_FOOD:
                  cursor=readFunction.query(db.getNameTable(),projection,selections,selectionsArgs,null,null,sortOrder);

                 if(cursor!=null){
                     Log.e("cursor is ","not null");
                     // thông báo về content resolver về việc truy vấn
                     cursor.setNotificationUri(getContext().getContentResolver(), uri);
                     cursor.moveToFirst();
                     return cursor;
                 }


                 break;
             case ContractInFoodContentProvider.ID_FOOD:
                     break;


         }


        throw new IllegalArgumentException("Unknown URI");

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {


            long id=0;
           //tham chiếu đến cơ sở dữ liệu cần được thêm dữ liệu
        //đây là hàm do sqlite cung cấp
           SQLiteDatabase writeFunction=db.getWritableDatabase();
           id = writeFunction.insert(db.getNameTable(), null, contentValues);
           writeFunction.close();
              if(id>0){
                  Toast.makeText(getContext(), "inserted : "+contentValues.get(db.getCol_food()), Toast.LENGTH_SHORT).show();
                  Uri _uri = ContentUris.withAppendedId(ContractInFoodContentProvider.CONTENT_URI, id);
                  // thông báo lại với content provider thông qua ông content resolver
                  getContext().getContentResolver().notifyChange(_uri, null);
                  return _uri;

            }
        // ném ra ngoại lệ neeus kiểu uri không phải là truy câập toàn bảng
        throw new IllegalArgumentException("Unknown URI INSERT: "+ uri);

    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int uriType = ContractInFoodContentProvider.getsURIMatcher().match(uri);
        SQLiteDatabase sqlDB = db.getWritableDatabase();
        int rowsDelete = 0;
        switch (uriType) {
            case ContractInFoodContentProvider.ALL_FOOD:
                rowsDelete = sqlDB.delete(db.getDatabaseName(),selection, selectionArgs);
                break;
            case ContractInFoodContentProvider.ID_FOOD:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDelete = sqlDB.delete(db.getDatabaseName(), db.getCol_id() + "=" + id, null);
                } else {
                    rowsDelete  = sqlDB.delete(db.getDatabaseName(), db.getCol_id()  + "=" + id + " and "+ selection, selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDelete;
    }


    //hàm update sẽ trả về số cột được thêm vào
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {

            int uriType = ContractInFoodContentProvider.getsURIMatcher().match(uri);
            SQLiteDatabase sqlDB = db.getWritableDatabase();
            int rowsUpdated = 0;
            switch (uriType) {
                case ContractInFoodContentProvider.ALL_FOOD:
                    rowsUpdated = sqlDB.update(db.getDatabaseName(),contentValues,
                            selection, selectionArgs);
                    break;
                case ContractInFoodContentProvider.ID_FOOD:
                    String id = uri.getLastPathSegment();
                    if (TextUtils.isEmpty(selection)) {
                        rowsUpdated = sqlDB.update(db.getDatabaseName(),
                                contentValues, db.getCol_id() + "=" + id, null);
                    } else {
                        rowsUpdated = sqlDB.update(db.getDatabaseName(),
                                contentValues, db.getCol_id()  + "=" + id + " and "
                                        + selection, selectionArgs);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown URI: " + uri);
            }
            getContext().getContentResolver().notifyChange(uri, null);
            return rowsUpdated;
    }
}
