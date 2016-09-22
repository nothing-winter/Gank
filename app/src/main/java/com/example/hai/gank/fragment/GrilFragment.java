package com.example.hai.gank.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hai.gank.R;
import com.example.hai.gank.tools.FileUtil;

import java.io.File;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by hai on 2016/9/4.
 */

public class GrilFragment extends Fragment {

    private Context context;

    private String url;

    private ImageView imageView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        url = getArguments().getString("URL");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gril,container,false);
        imageView = (ImageView) view.findViewById(R.id.fragment_gril_imageView);

        Glide.with(context).load(url).into(imageView);
        return view;
    }

    public void shareImage(){
        if(imageView == null)
            return;
        Drawable drawable = imageView.getDrawable();
        if(drawable == null)
            return;
        Bitmap bitmap = drawableToBitmap(drawable);
        Observable.just(FileUtil.saveImage(bitmap,"share.jpg"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if(aBoolean){
                            Uri imageUri = Uri.fromFile(new File(FileUtil.CAMERA_Path + "/share.jpg"));
                            Intent shareIntent = new Intent();
                            shareIntent.setAction(Intent.ACTION_SEND);
                            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                            shareIntent.setType("image/*");
                            startActivity(Intent.createChooser(shareIntent, "分享gank到"));
                        }else {
                            Toast.makeText(context,"大爷！分享出错了!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public android.graphics.Bitmap drawableToBitmap(Drawable drawable) {
        android.graphics.Bitmap bitmap = android.graphics.Bitmap.createBitmap(

                drawable.getIntrinsicWidth(),

                drawable.getIntrinsicHeight(),

                drawable.getOpacity() != PixelFormat.OPAQUE ? android.graphics.Bitmap.Config.ARGB_8888

                        : android.graphics.Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);

        //canvas.setBitmap(bitmap);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        drawable.draw(canvas);

        return bitmap;
    }
}
