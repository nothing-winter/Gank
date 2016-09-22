package com.example.hai.gank.tools;

import com.example.hai.gank.base.MyApplication;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hai on 2016/9/1.
 */

public class OkHttpRetrofit {

    private static String baseUrl = "http://gank.io/api/data/福利/";
    public  static File baseFile = MyApplication.getContext().getCacheDir();
    public  static final File cacheFile = new File(baseFile,"gankCache");

    private static Retrofit mRetrofit;

    public static Retrofit getRetrofit(){
        if(mRetrofit == null){
            synchronized (OkHttpRetrofit.class){
                mRetrofit = new Retrofit.Builder().baseUrl(baseUrl).client(getOkHttpClient())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
            }
        }
        return mRetrofit;
    }

    private static OkHttpClient getOkHttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(new Cache(cacheFile,1024*1024*10)).build();
        return okHttpClient;
    }
}
