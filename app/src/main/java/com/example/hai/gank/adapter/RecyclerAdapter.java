package com.example.hai.gank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hai.gank.R;
import com.example.hai.gank.bean.WlfareBean;
import com.example.hai.gank.listener.LoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hai on 2016/9/2.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private List<WlfareBean.GrilBean> data;

    private LoadMoreListener<WlfareBean.GrilBean> loadMoreListener;

    private Context context;

    public RecyclerAdapter(Context context){
        this.context = context;
        data = new ArrayList<WlfareBean.GrilBean>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grils_item,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,viewHolder.position+":",Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(data.get(position).getUrl()).into(holder.imageView);
        holder.position=position;
        if(++position == getItemCount()){
            loadMore();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<WlfareBean.GrilBean> data){
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void setLoadMoreListener(LoadMoreListener<WlfareBean.GrilBean> loadMoreListener){
        this.loadMoreListener = loadMoreListener;
    }

    public void loadMore(){
        if(loadMoreListener ==null )
            return;
        loadMoreListener.loadMore();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;

        public int position;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.grils_item_imageView);
        }
    }
}
