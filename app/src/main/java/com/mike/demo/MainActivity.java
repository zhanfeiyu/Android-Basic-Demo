package com.mike.demo;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mike.demo.base.BaseActivity;
import com.mike.demo.databinding.ActivityMainBinding;
import com.mike.demo.ui.mainviewpager.DefaultBlankFragment;
import com.mike.demo.ui.mainviewpager.familyfragment.FamilyFragment;
import com.mike.demo.ui.mainviewpager.StudyFragment;
import com.mike.demo.ui.mainviewpager.MainViewModel;
import com.mike.demo.ui.mainviewpager.MainViewPagerAdapter;
import com.mike.demo.ui.mainviewpager.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private String TAG = "MainActivity";
    ViewGroup root;
    List<Fragment> mainPageFragments = new ArrayList<>();

    int originTextColor;
    float originalTextSize;
    TextView tw_current;

    ActivityMainBinding mainBinding = null;

    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBindings();
        initFragments();
        initViewPager();
        initTabIndicator();
    }

    private void initDataBindings() {
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(this);
        mainBinding.setVm(mainViewModel);

    }

    private void initFragments() {
        Log.d(TAG, "enter initFragments");
        mainPageFragments.add(DefaultBlankFragment.newInstance("Fragment1"));
        mainPageFragments.add(FamilyFragment.newInstance("abc", "def"));
        mainPageFragments.add(StudyFragment.newInstance("adb", "ddd"));
        mainPageFragments.add(MineFragment.newInstance("ddd", "ddd"));
        Log.d(TAG, "leave initFragments");
    }

    private void initViewPager() {
        Log.d(TAG, "enter initViewPager");

        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), getLifecycle(), mainPageFragments);

        mainBinding.mainViewPager.setAdapter(mainViewPagerAdapter);
        mainBinding.mainViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeIndicatorTab(position);
            }
        });
        mainBinding.mainViewPager.setUserInputEnabled(false);
        Log.d(TAG, "leave initViewPager");
    }

    private void initTabIndicator() {
        Log.d(TAG, "enter initTabIndicator");

        mainBinding.viewTabMain.setOnClickListener(this);
        mainBinding.viewTabSecond.setOnClickListener(this);
        mainBinding.viewTabThird.setOnClickListener(this);
        mainBinding.viewTabFourth.setOnClickListener(this);

        mainViewModel.setTextViewColor(mainBinding.twMainTabMain, R.color.red);

        originTextColor = mainBinding.twMainTabMain.getCurrentTextColor();
        originalTextSize = mainBinding.twMainTabFourth.getTextSize();

        setTextViewBoldStyle(mainBinding.twMainTabMain, true);
        tw_current = mainBinding.twMainTabMain;
        Log.d(TAG, "leave initTabIndicator");
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick");
        changeIndicatorTab(v.getId());
    }

/*
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
*/

    void changeIndicatorTab(int position) {
        setTextViewBoldStyle(tw_current, false);
        switch (position) {
            case R.id.view_tab_main:
                mainBinding.mainViewPager.setCurrentItem(0);
            case 0:
                setTextViewBoldStyle(mainBinding.twMainTabMain, true);
                tw_current = mainBinding.twMainTabMain;
                break;
            case R.id.view_tab_second:
                mainBinding.mainViewPager.setCurrentItem(1);
            case 1:
                setTextViewBoldStyle(mainBinding.twMainTabSecond, true);
                tw_current = mainBinding.twMainTabSecond;
                break;
            case R.id.view_tab_third:
                mainBinding.mainViewPager.setCurrentItem(2);
            case 2:
                setTextViewBoldStyle(mainBinding.twMainTabThird, true);
                tw_current = mainBinding.twMainTabThird;
                break;
            case R.id.view_tab_fourth:
                mainBinding.mainViewPager.setCurrentItem(3);
            case 3:
                setTextViewBoldStyle(mainBinding.twMainTabFourth, true);
                tw_current = mainBinding.twMainTabFourth;
                break;
            default:
                break;
        }
    }

    public void setTextViewBoldStyle(TextView textView, boolean bold) {
        if (bold) {
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setTextSize(18);
            textView.setTextColor(Color.RED);
        } else {
            textView.setTypeface(Typeface.DEFAULT);
            textView.setTextSize(15);
            textView.setTextColor(originTextColor);
        }
    }
}