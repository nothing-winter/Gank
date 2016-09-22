package com.example.hai.gank.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hai.gank.R;
import com.example.hai.gank.activity.ItemGrilActivity;
import com.example.hai.gank.bean.WlfareBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;

/**
 * Created by hai on 2016/9/4.
 */

public class EasyRecyclerAdapter extends RecyclerArrayAdapter<WlfareBean.GrilBean> implements RecyclerArrayAdapter.OnItemClickListener {


    public EasyRecyclerAdapter(Context context) {
        super(context);
        setOnItemClickListener(this);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.grils_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), ItemGrilActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        bundle.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) getAllData());
        intent.putExtras(bundle);
        getContext().startActivity(intent);
    }


    public class ViewHolder extends BaseViewHolder<WlfareBean.GrilBean>{
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.grils_item_imageView);
        }


        @Override
        public void setData(WlfareBean.GrilBean data) {
            Glide.with(getContext()).load(data.getUrl()).into(imageView);
        }
    }
}
