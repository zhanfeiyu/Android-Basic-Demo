package com.mike.demo.ui.mainviewpager;

import android.content.Context;
import android.text.TextPaint;
import android.util.Log;
import android.widget.TextView;

import androidx.databinding.ObservableField;

import com.mike.demo.R;

public class MainViewModel {
    private String TAG = MainViewModel.class.getSimpleName();
    public static ObservableField<Integer> mainPageTabTextColor = new ObservableField<>();
    public static ObservableField<String> tabString = new ObservableField<>();

    Context mContext;

    public MainViewModel(Context context) {
        mContext = context;
    }

    public void setTextViewColor(TextView textView, int color) {
        int id = textView.getId();
        switch (id) {
            case R.id.tw_main_tab_main:
                mainPageTabTextColor.set(color);
                Log.d(TAG, "after setTextViewColor");
                break;
        }
    }

    public void setTextViewBoldStyle(TextView textView, boolean style) {
        TextPaint textPaint = textView.getPaint();
        textPaint.setFakeBoldText(style);
    }

    public void setText(String text) {
        this.tabString.set(text);
    }
}
