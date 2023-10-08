package com.example.contentproviderknowledge;

import android.content.UriMatcher;
import android.net.Uri;

public class ContractInFoodContentProvider {
    private static final String AUTHORITY = "com.database.fooddatabase";
    //content path : tên bảng data base cần truy cập
    private static final String CONTENT_PATH =  "Foods";
    static final String URL = "content://" + AUTHORITY + "/" + CONTENT_PATH;
    public static final int ALL_FOOD=1;
    public static final int ID_FOOD=2;

    //tạo đường dẫn Uri để liên kết content provider với database
    static final Uri CONTENT_URI = Uri.parse(URL);


    //mục đích của việc sinh ra UriMatcher :
    //như ta đã biết thì Uri sẽ xác định kiểu dữ liệu từ cơ sở dữ liệu cho content provider
    /*- bản thân uri sẽ có 2 kiểu trả về :
      +trả về một bảng của cơ sở dữ liệu đó
      + trả về một hàng của bảng dữ liệu từ cơ sở dữ liệu đó
    => nhờ có UriMatcher ta có thể định nghĩa được kiểu trả về của Uri
    * */

    private static final UriMatcher sURIMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);
    static {
        // lâấy ra data của toàn bảng
        sURIMatcher.addURI(AUTHORITY, CONTENT_PATH, ALL_FOOD);
        // lấy ra data từ một hàng của bảng
        sURIMatcher.addURI(AUTHORITY,  CONTENT_PATH + "/#", ID_FOOD);
    }
    public static UriMatcher getsURIMatcher(){
        return sURIMatcher;
    }
    public static Uri getContentUri(){
        return CONTENT_URI;
    }
    public static String getContentPath(){
        return CONTENT_PATH;
    }

}
