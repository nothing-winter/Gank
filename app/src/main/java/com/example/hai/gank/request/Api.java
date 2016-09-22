package com.example.hai.gank.request;

import com.example.hai.gank.tools.OkHttpRetrofit;

/**
 * Created by hai on 2016/9/2.
 */

public class Api {

    public static WlfareRequest getWlfareApi(){
       return OkHttpRetrofit.getRetrofit().create(WlfareRequest.class);
    }
}
