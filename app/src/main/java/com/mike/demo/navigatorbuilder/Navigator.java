package com.mike.demo.navigatorbuilder;

import android.content.Context;
import android.view.ViewGroup;

//给User用的,
public class Navigator extends AbsNavigator {
    protected Navigator(Builder builder) {
        super(builder);
    }

    public static class Builder extends AbsNavigator.Builder<Navigator.Builder> {

        public Builder(Context context, int layoutId, ViewGroup viewGroup) {
            super(context, layoutId, viewGroup);
        }

        @Override
        public Navigator create() {
            return new Navigator(this);
        }
    }

}
