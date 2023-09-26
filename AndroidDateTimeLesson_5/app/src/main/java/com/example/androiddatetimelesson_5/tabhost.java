package com.example.androiddatetimelesson_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

public class tabhost extends AppCompatActivity {

    /*  trước hết một tabselector bao gồm 3 thành phần :
     *   +tabhost : vùng chính chứa nội dung của tab và chứa các nút tab
     * => tabhost là một công cụ giúp cho người dùng có thể chuyển đổi sang các giao diện khác bằng các tab
     * ngay trong cùng một activity
     *   +tabwidgnet : Để định dạng cho các Tab button.
     *   +framelayout : vùng chứa nội dung của Tab
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);
        //các hàm của tabhost
        //setIndicator(CharSequence label): Phương thức này được sử dụng để thiết lập chuỗi nhãn lên  trên tab.
        // setIndicator(CharSequence label,Drawable icon): Phương thức này được sử dụng để thiết lập chuỗi nhãn và một icon lên trên tab.
        //addTab(TabSpec tabSpec): Phương thức này được sử dụng để thêm một tab mới cho một tab widget.
        //clearAllTabs():  Phương thức này được sử dụng xóa tất cả các tab trên TabHost
        /* setCurrentTab(int index): Phương thức này được sử dụng để thiết lập tab được chọn.
        Mặc định trong TabHost tab đầu tiên là tab hiện tại.*/
        //setOnTabChangedListener(OnTabChangeListenerl):  Phương thức này được sử dụng khi một tab thay đổi
        TabHost tabhost= findViewById(R.id.tabhost);
        tabhost.setup();
        // khởi tạo một tab con của tab host
        TabHost.TabSpec spec=tabhost.newTabSpec("main");

        // hàm set content giúp cho spec có thể xác định nội dung trong tabs đó
        spec.setContent(R.id.main);

       // khởi tạo một tabs con mới là spec cho tabhost
        spec.setIndicator("MAIN");
        tabhost.addTab(spec);

        TabHost.TabSpec spec2=tabhost.newTabSpec("clock");
        spec2.setIndicator("CLOCK");
        spec2.setContent(R.id.analogClock);
        tabhost.addTab(spec2);

        tabhost.setCurrentTab(0);

    }
}