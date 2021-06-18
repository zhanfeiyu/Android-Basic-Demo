package com.mike.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mike.demo.navigatorbuilder.DefaultNavigator;
import com.mike.demo.navigatorbuilder.Navigator;
import com.mike.demo.patternusage.wrapperpattern.MyRecyclerView;
import com.mike.demo.patternusage.wrapperpattern.WrapRecyclerViewAdapter;
import com.mike.demo.ui.mainviewpager.DefaultBlankFragment;
import com.mike.demo.ui.mainviewpager.MainViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "MainActivity";
    ViewGroup root;
    ViewPager2 mainViewPager;
    List<Fragment> mainPageFragments = new ArrayList<>();

    LinearLayout ll_tab_main, ll_tab_second, ll_tab_third, ll_tab_fourth;
    ImageView iv_tab_main, iv_tab_second, iv_tab_third, iv_tab_fourth, iv_current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
        initViewPager();
        initTabIndicator();
    }

    private void initFragments() {
        mainPageFragments.add(DefaultBlankFragment.newInstance("Fragment1"));
        mainPageFragments.add(DefaultBlankFragment.newInstance("Fragment2"));
        mainPageFragments.add(DefaultBlankFragment.newInstance("Fragment3"));
        mainPageFragments.add(DefaultBlankFragment.newInstance("Fragment4"));
    }

    private void initViewPager() {
        mainViewPager = findViewById(R.id.main_view_pager);

        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), getLifecycle(), mainPageFragments);
        mainViewPager.setAdapter(mainViewPagerAdapter);

        mainViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeIndicatorTab(position);
            }
        });

    }

    private void initTabIndicator() {
        ll_tab_main = findViewById(R.id.view_tab_main);
        ll_tab_main.setOnClickListener(this);

        ll_tab_second = findViewById(R.id.view_tab_second);
        ll_tab_second.setOnClickListener(this);

        ll_tab_third = findViewById(R.id.view_tab_third);
        ll_tab_third.setOnClickListener(this);

        ll_tab_fourth = findViewById(R.id.view_tab_fourth);
        ll_tab_fourth.setOnClickListener(this);

        iv_tab_main = findViewById(R.id.iv_main_tab_main);
        iv_tab_second = findViewById(R.id.iv_main_tab_second);
        iv_tab_third = findViewById(R.id.iv_main_tab_third);
        iv_tab_fourth = findViewById(R.id.iv_main_tab_fourth);

        iv_tab_main.setSelected(true);
        iv_current = iv_tab_main;
    }

    @Override
    public void onClick(View v) {
        changeIndicatorTab(v.getId());
    }

    void changeIndicatorTab(int position) {
        iv_current.setSelected(false);
        switch (position) {
            case R.id.view_tab_main:
                mainViewPager.setCurrentItem(0);
            case 0:
                iv_tab_main.setSelected(true);
                iv_current = iv_tab_main;
                break;
            case R.id.view_tab_second:
                mainViewPager.setCurrentItem(1);
            case 1:
                iv_tab_second.setSelected(true);
                iv_current = iv_tab_second;
                break;
            case R.id.view_tab_third:
                mainViewPager.setCurrentItem(2);
            case 2:
                iv_tab_third.setSelected(true);
                iv_current = iv_tab_third;
                break;
            case R.id.view_tab_fourth:
                mainViewPager.setCurrentItem(3);
            case 3:
                iv_tab_fourth.setSelected(true);
                iv_current = iv_tab_fourth;
                break;
            default:
                break;
        }

    }
}