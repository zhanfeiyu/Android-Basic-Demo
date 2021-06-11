package com.mike.demo.navigatorbuilder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mike.demo.R;

//有时我们不需要user去关注id以及我们的头部的样式，大部分的情况下是类似的，所以提供一个默认的
public class DefaultNavigator extends AbsNavigator<DefaultNavigator.Builder> {
    protected DefaultNavigator(Builder builder) {
        super(builder);
    }

    @Override
    public void applyLayoutParams() {
        super.applyLayoutParams();
        View view = findViewById(R.id.tw_navigation_bar);
        //调用getBuilder，需要指定泛型，否则会提示找不到visibility属性，
        view.setVisibility(getBuilder().visibility);
    }

    public static class Builder extends AbsNavigator.Builder {
        //DefaultNavigator拥有的特殊的
        int visibility = View.VISIBLE;

        public Builder(Context context, ViewGroup viewGroup) {
            super(context, R.layout.ui_navigation_bar_default, viewGroup);
        }

        @Override
        public DefaultNavigator create() {
            return new DefaultNavigator(this);
        }

        public Builder setLeftText(String text) {
            setText(R.id.tw_navigation_bar, text);
            return this;
        }

        public Builder setLeftTextClickListener(View.OnClickListener clickListener) {
            setOnClickListener(R.id.tw_navigation_bar, clickListener);
            return this;
        }

        public Builder hideLeftText() {
            this.visibility = View.INVISIBLE;
            return this;
        }
    }
}
