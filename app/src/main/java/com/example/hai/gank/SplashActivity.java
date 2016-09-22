package com.example.hai.gank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hai.gank.activity.GrilsActivity;
import com.example.hai.gank.bean.WlfareBean;
import com.example.hai.gank.request.Api;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.splash_imageView)
    ImageView mImageView;

    private Unbinder butterKnifeUnbinder;

    private ScaleAnimation scaleAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        butterKnifeUnbinder = ButterKnife.bind(this);

        initAnim();

        initView();

    }

    private void initAnim(){
        scaleAnim = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(3000);
        scaleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(SplashActivity.this,"splash has finish",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SplashActivity.this, GrilsActivity.class);
                startActivity(intent);
                finishActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void finishActivity(){
        this.finish();
    }

    private void initView(){
        Api.getWlfareApi().request(1,1)
                .subscribeOn(Schedulers.io())
                .map(new Func1<WlfareBean, String>() {
                    @Override
                    public String call(WlfareBean wlfareBean) {

                        if(!wlfareBean.isError()){
                            return wlfareBean.getGrils().get(0).getUrl();
                        }
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Glide.with(SplashActivity.this).load(R.drawable.welcome).animate(scaleAnim).into(mImageView);
                    }

                    @Override
                    public void onNext(String s) {
                        if(s == null){
                            Glide.with(SplashActivity.this).load(R.drawable.welcome).animate(scaleAnim).into(mImageView);
                        }
                        Glide.with(SplashActivity.this).load(s).animate(scaleAnim).into(mImageView);
                    }
                });

    }

    @Override
    protected void onDestroy() {
        butterKnifeUnbinder.unbind();
        super.onDestroy();
    }
}
