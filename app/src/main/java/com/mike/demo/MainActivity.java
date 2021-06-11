package com.mike.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mike.demo.navigatorbuilder.DefaultNavigator;
import com.mike.demo.navigatorbuilder.Navigator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        ViewGroup root = findViewById(R.id.view_root);
        //使用Navigator: 需要知道布局资源的详细信息，id以及资源名
/*        Navigator navigator = new Navigator.Builder(this,
                R.layout.my_navigation_header, root)
                .setText(R.id.tw_back, "回退")
                .setOnClickListener(R.id.tw_back, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }).create();*/

        //如果有属性设置的接口没有提供，可以使用Navigator的findViewById API.
        //TextView textView = navigator.findViewById(R.id.tw_back);
        //textView.setVisibility(View.INVISIBLE);
        //textView.setTextSize((float) 12.0);

        DefaultNavigator defaultNavigator = new DefaultNavigator.Builder(this, root)
                .setLeftText("默认回退")
                .setLeftTextClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                })
                //.hideLeftText()
                .create();

    }
}