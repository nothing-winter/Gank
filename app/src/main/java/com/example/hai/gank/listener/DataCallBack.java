package com.example.hai.gank.listener;

/**
 * Created by hai on 2016/9/3.
 */

public interface DataCallBack<T> {

    public void callBack(T object);

    public boolean isfreash();
}
