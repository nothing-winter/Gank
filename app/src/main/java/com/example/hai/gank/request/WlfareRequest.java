package com.example.hai.gank.request;

import com.example.hai.gank.bean.WlfareBean;



import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hai on 2016/9/1.
 */

public interface WlfareRequest {

    @GET("{count}/{page}")
    Observable<WlfareBean> request(@Path("count") int count, @Path("page") int page);
}
