package com.example.hai.gank.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.hai.gank.R;
import com.example.hai.gank.adapter.ViewPagerAdapter;
import com.example.hai.gank.base.BaseActivity;
import com.example.hai.gank.bean.WlfareBean;
import com.example.hai.gank.fragment.GrilFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Created by hai on 2016/9/4.
 */

public class ItemGrilActivity extends BaseActivity {

    @BindView(R.id.itemGril_viewPager)
    ViewPager mViewPager;

    @BindView(R.id.item_gril_toolbar)
    Toolbar toolbar;

    private List<WlfareBean.GrilBean> data;

    private int position;

    private ViewPagerAdapter pagerAdapter;


    @Override
    protected int getContentView() {
        return R.layout.activity_item_gril;
    }

    @Override
    protected void init() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setTitle("妹子");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finishActivity();
            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        position = bundle.getInt("position");
        data = bundle.getParcelableArrayList("data");

        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);
        pagerAdapter.setData(data);
        pagerAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(position);

    }

    private void finishActivity(){
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share_image){
            shareImage();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareImage(){
      //  int currentPosition = mViewPager.getCurrentItem();
        GrilFragment grilFragment = pagerAdapter.getCurrentGrilFragment();
        grilFragment.shareImage();
    }
}
