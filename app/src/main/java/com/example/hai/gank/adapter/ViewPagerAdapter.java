package com.example.hai.gank.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.hai.gank.bean.WlfareBean;
import com.example.hai.gank.fragment.GrilFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hai on 2016/9/4.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<WlfareBean.GrilBean> data;

    private GrilFragment currentGrilFragment;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        data = new ArrayList<WlfareBean.GrilBean>();
    }

    @Override
    public Fragment getItem(int position) {
        GrilFragment grilFragment = new GrilFragment();
        Bundle bundle = new Bundle();
        bundle.putString("URL",data.get(position).getUrl());
        grilFragment.setArguments(bundle);
        return grilFragment;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public void setData(List<WlfareBean.GrilBean> data) {
        this.data = data;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        currentGrilFragment = (GrilFragment) object;
    }
    public GrilFragment getCurrentGrilFragment(){
        return currentGrilFragment;
    }
}
