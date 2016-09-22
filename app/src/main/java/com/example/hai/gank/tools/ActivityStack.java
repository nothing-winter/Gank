package com.example.hai.gank.tools;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by hai on 2016/9/1.
 */

public class ActivityStack {

    private static Stack<Activity> mActivityStack = new Stack<Activity>();

    public static void add(Activity activity){
        mActivityStack.add(activity);
    }

    public static void pop(){
        mActivityStack.pop();
    }

    public static void exit(){
        for(Activity activity : mActivityStack){
            activity.finish();
        }
        System.exit(0);
    }


}
