package com.mike.demo.navigatorbuilder;

import android.view.View;
import android.view.ViewGroup;

//写框架时，先定义一个接口，即使最开始不知道需要写什么，可以后面进行补充
public interface INavigator {
    void createNavigator();
    void addViewToRoot(View view, ViewGroup viewGroup);
    public void applyLayoutParams();
}
