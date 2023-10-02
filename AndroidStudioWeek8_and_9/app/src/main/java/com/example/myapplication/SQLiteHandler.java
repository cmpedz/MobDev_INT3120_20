package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


//tạo một sqlite handler để ta có thể tự xây dựng nên một cơ sở dữ liệu lưu trữ thông tin sinh viên dựa trên sqlite có sẵn của android studio
public class SQLiteHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "schoolManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";
    private static final String Column_ID = "id";
    private static final String Column_NAME = "name";

    public SQLiteHandler(Context context) {
        //khởi tạo một database với tên schoolmanager trong sqlite
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //tạo hàm khởi tạo cho database
    @Override
    public void onCreate(SQLiteDatabase db) {
        //khởi tạo một bảng mang tên students trong database trên
        // ta viết đoạn create_students_table như việc tạo ra một table trong database
        String create_table = "CREATE TABLE "+TABLE_NAME+"("+
                                        Column_ID+" INTEGER PRIMARY KEY,"+Column_NAME+" VARCHAR(30) );";

        // chèn đoạn code trên vào database sqlite
        db.execSQL(create_table);
    }


    //tạo hàm update cho database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // xóa bảng cũ
            String drop_table="DROP TABLE"+TABLE_NAME+";";
            db.execSQL(drop_table);

            //tạo một bảng mới đc update
            onCreate(db);

    }

    //tạo hàm insert dữ liệu cho data base
    public void addStudent(Student s){
        /* SQLiteDatabase có chứa các phương thức tạo, xóa, thực thi các lệnh SQL, nó sẽ được sử dụng để
        insert các giá trị từ object Student vào các trường trong bảng students*/
      SQLiteDatabase db=this.getWritableDatabase();

        /*ContentValues được sử dụng để lưu các giá trị tương ứng với các trường trong bảng*/
        ContentValues values = new ContentValues();
        values.put(Column_ID,s.getId());
        values.put(Column_NAME,s.getName());

        db.insert(TABLE_NAME,null,values);
        db.close();



    }

    //tạo hàm trả dữ liệu của student từ database về cho ứng dụng
    public Student getStudent(int studentId) {
        SQLiteDatabase db = this.getReadableDatabase();

        //Ta sẽ dùng Cursor để lưu giá trị trả về
        Cursor cursor = db.query(TABLE_NAME, null, Column_ID + " = ?", new String[] { String.valueOf(studentId) },null, null, null);
        if(cursor != null)
            //nếu cursor khác null thì cursor cần moveToFirst() để có thể trỏ vào hàng dữ liệu đầu tiên của database
            cursor.moveToFirst();
        Student student = new Student(cursor.getInt(0), cursor.getString(1));
        return student;
    }
    public void deleteStudent(int studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, Column_ID + " = ?", new String[] { String.valueOf(studentId) });
        db.close();
    }

}
