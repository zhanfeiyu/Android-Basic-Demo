package com.mike.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

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
        Navigator navigator = new Navigator.Builder(this,
                R.layout.my_navigation_header, root)
                .setText(R.id.tw_back, "回退")
                .setOnClickListener(R.id.tw_back, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }).create();


    }
}