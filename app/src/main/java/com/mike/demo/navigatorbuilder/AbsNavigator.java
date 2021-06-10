package com.mike.demo.navigatorbuilder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

// 先写个基础的类, 通用操作
public class AbsNavigator implements INavigator{
    private Builder builder;
    private View navigationBar;

    protected AbsNavigator(Builder builder) { //不能让user去new，所以protected
        this.builder = builder;

        createNavigator();
        addViewToRoot(navigationBar, this.builder.viewGroup);
        applyLayoutParams();
    }

    public void applyLayoutParams() {
        Map<Integer, String> textMaps = this.builder.textMaps;
        for (Map.Entry<Integer, String> entry : textMaps.entrySet()) {
            TextView textView = findViewById(entry.getKey());
            textView.setText(entry.getValue());
        }

        Map<Integer, View.OnClickListener> listenerMaps = this.builder.listenerMaps;
        for (Map.Entry<Integer, View.OnClickListener> entry : listenerMaps.entrySet()) {
            View view = findViewById(entry.getKey());
            view.setOnClickListener(entry.getValue());
        }
    }

    public <T extends View> T findViewById(Integer resId) {
        return navigationBar.findViewById(resId);
    }

    public void addViewToRoot(View view, ViewGroup viewGroup) {
        viewGroup.addView(view, 0);
    }

    public void createNavigator() {
        navigationBar = LayoutInflater.from(this.builder.context)
                .inflate(this.builder.layoutId, this.builder.viewGroup, false);
    }

    public Builder getBuilder() {
        return builder;
    }

    //如果Builder不加泛型，调用setText等设参方法后，调用create()会报错：required Navigator, provided AbsNavigator.
    //因为setText返回的是AbsNavigator的builder
    public static abstract class Builder<B extends AbsNavigator.Builder> {
        private Context context;
        private int layoutId;
        private ViewGroup viewGroup;

        Map<Integer, String> textMaps;
        Map<Integer, View.OnClickListener> listenerMaps;

        public Builder(Context context, int layoutId, ViewGroup viewGroup) {
            this.context = context;
            this.layoutId = layoutId;
            this.viewGroup = viewGroup;

            textMaps = new HashMap<>();
            listenerMaps = new HashMap<>();
        }

        public abstract AbsNavigator create();

/*        public Builder setText(int id, String text) {
            textMaps.put(id, text);
            return this;
        }*/

        public B setText(int id, String text) {
            textMaps.put(id, text);
            return (B)this;
        }

        public B setOnClickListener(int id, View.OnClickListener listener) {
            listenerMaps.put(id, listener);
            return (B)this;
        }
    }
}
