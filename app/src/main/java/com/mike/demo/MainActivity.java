package com.mike.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mike.demo.databinding.ActivityMainBinding;
import com.mike.demo.databinding.LayoutForMainViewPagerIndicatorBinding;
import com.mike.demo.navigatorbuilder.DefaultNavigator;
import com.mike.demo.navigatorbuilder.Navigator;
import com.mike.demo.patternusage.wrapperpattern.MyRecyclerView;
import com.mike.demo.patternusage.wrapperpattern.WrapRecyclerViewAdapter;
import com.mike.demo.ui.mainviewpager.DefaultBlankFragment;
import com.mike.demo.ui.mainviewpager.MainViewModel;
import com.mike.demo.ui.mainviewpager.MainViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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
        setContentView(R.layout.activity_main);
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
        mainPageFragments.add(DefaultBlankFragment.newInstance("Fragment1"));
        mainPageFragments.add(DefaultBlankFragment.newInstance("Fragment2"));
        mainPageFragments.add(DefaultBlankFragment.newInstance("Fragment3"));
        mainPageFragments.add(DefaultBlankFragment.newInstance("Fragment4"));
    }

    private void initViewPager() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), getLifecycle(), mainPageFragments);

        mainBinding.mainViewPager.setAdapter(mainViewPagerAdapter);
        mainBinding.mainViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeIndicatorTab(position);
            }
        });

    }

    private void initTabIndicator() {
        mainBinding.viewTabMain.setOnClickListener(this);
        mainBinding.viewTabSecond.setOnClickListener(this);
        mainBinding.viewTabThird.setOnClickListener(this);
        mainBinding.viewTabFourth.setOnClickListener(this);

        mainViewModel.setTextViewColor(mainBinding.twMainTabMain, R.color.red);

        originTextColor = mainBinding.twMainTabMain.getCurrentTextColor();
        originalTextSize = mainBinding.twMainTabFourth.getTextSize();

        setTextViewBoldStyle(mainBinding.twMainTabMain, true);
        tw_current = mainBinding.twMainTabMain;
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