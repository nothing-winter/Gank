package com.example.hai.gank.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by hai on 2016/9/6.
 */

public class MyApplication extends Application {

    public static Context context;

    public MyApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
