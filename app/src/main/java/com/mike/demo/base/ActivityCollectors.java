package com.mike.demo.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;
/*
  用途：随时随地退出应用程序, 在应用的任何一个Activity里调用finishAll方法，都会退出整个应用并回收资源。
  这可以理解成又是一个 “数据源” 思路的案例
 */
public class ActivityCollectors {
    private static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        if (activity != null && !activities.contains(activity)) {
            activities.add(activity);
        }
    }

    public static void removeActivity(Activity activity) {
        if (activities.contains(activity)) {
            activities.remove(activity);
        }
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            activity.finish();
        }
        activities.clear();
    }
}
