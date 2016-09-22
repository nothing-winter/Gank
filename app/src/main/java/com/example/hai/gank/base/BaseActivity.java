package com.example.hai.gank.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.example.hai.gank.tools.ActivityStack;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hai on 2016/9/1.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mButterKnifeBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentView());

        mButterKnifeBinder = ButterKnife.bind(this);

        init();

        ActivityStack.add(this);
    }

    protected abstract int getContentView();

    protected abstract void init();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            ActivityStack.pop();
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    @Override
    protected void onDestroy() {
        mButterKnifeBinder.unbind();

        super.onDestroy();
    }
}
