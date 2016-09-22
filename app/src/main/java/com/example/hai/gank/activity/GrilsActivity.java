package com.example.hai.gank.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.example.hai.gank.R;
import com.example.hai.gank.adapter.EasyRecyclerAdapter;
import com.example.hai.gank.base.BaseActivity;
import com.example.hai.gank.bean.WlfareBean;
import com.example.hai.gank.request.Api;
import com.example.hai.gank.tools.NetUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hai on 2016/9/2.
 */

public class GrilsActivity extends BaseActivity implements RecyclerArrayAdapter.OnLoadMoreListener, android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG=GrilsActivity.class.getSimpleName();

 //   @BindView(R.id.activity_grils_recyclerView)
 //   RecyclerView mRecyclerView;

    @BindView(R.id.activity_grils_easyRecycler)
    EasyRecyclerView mEasyRecyclerView;

    @BindView(R.id.activity_grils_viewStub)
    ViewStub viewStub;


    private static final int COUNT = 10;
    private int page = 1;
    private int loageMoreCount = 5;

    private boolean isViewStubShow = false;

//    private RecyclerAdapter adapter;

    private EasyRecyclerAdapter easyAdapter;



    @Override
    protected int getContentView() {
        return R.layout.activity_grils;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void init() {
//
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        adapter = new RecyclerAdapter(this);
//        adapter.setLoadMoreListener(this);
//        mRecyclerView.setAdapter(adapter);
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });
//
//        initData();


        mEasyRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        easyAdapter = new EasyRecyclerAdapter(this);
        mEasyRecyclerView.setAdapter(easyAdapter);
        easyAdapter.setMore(R.layout.load_more,this);
        mEasyRecyclerView.setRefreshListener(this);
        initData();

    }

    private void initData(){
        getData(COUNT,page);
        page = 2;
    }

    private void getData(int count,int page){
        Api.getWlfareApi().request(count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WlfareBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e(TAG,"getData was wrong");
                        String text = "出错了";
                        if(!isConnectedNet()){
                            text = "请检查网络";
                        }
                        initViewStub(text);
                    }

                    @Override
                    public void onNext(WlfareBean wlfareBean) {
                       // adapter.setData(wlfareBean.getGrils());
                        easyAdapter.addAll(wlfareBean.getGrils());
                    }
                });

    }

    private void initViewStub(String text){
        if(isViewStubShow)
           return;
        View view =viewStub.inflate();
        TextView textView = (TextView) view.findViewById(R.id.error_textView);
        textView.setText(text);
        isViewStubShow = true;

    }

    private boolean isConnectedNet(){
        return NetUtil.isConnected(this);
    }



    @Override
    public void onLoadMore() {
        page++;
        getData(loageMoreCount,page);
    }


    @Override
    public void onRefresh() {
        getData(10,1);
    }
}
