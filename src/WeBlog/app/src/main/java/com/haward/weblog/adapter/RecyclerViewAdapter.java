package com.haward.weblog.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haward.weblog.R;
import com.haward.weblog.WebActivity;
import com.haward.weblog.entity.BlogInfo;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private List<BlogInfo> blogs;
    private Context context;

    public RecyclerViewAdapter(List<BlogInfo> blogs, Context context) {
        this.blogs = blogs;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(context).inflate(R.layout.blog_item,viewGroup,false);
       CustomViewHolder customViewHolder = new CustomViewHolder(view);
       return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        final int index = i;
        customViewHolder.blog_title.setText(blogs.get(i).getTitle());
        customViewHolder.blog_content.setText(blogs.get(i).getContent());
        customViewHolder.blog_time.setText(blogs.get(i).getTime());
        customViewHolder.blog_readNum.setText(blogs.get(i).getReadNum()+"");
        if (blogs.get(i).getDiscussNum()!=0) {
            customViewHolder.blog_discussNum.setText("评论 " + blogs.get(i).getDiscussNum());
        } else {
            customViewHolder.blog_discussNum.setText("评论");
        }
        customViewHolder.blog_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: " + blogs.get(index).getUrl());
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("url", blogs.get(index).getUrl());
                context.startActivity(intent);
            }
        });
    }

    //自定义类
    static class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView blog_title;
        TextView blog_content;
        TextView blog_time;
        TextView blog_readNum;
        TextView blog_discussNum;
        LinearLayout blog_item;

        public CustomViewHolder(final View itemView) {
            super(itemView);
            blog_item = itemView.findViewById(R.id.blog_item);
            blog_title = itemView.findViewById(R.id.blog_title);
            blog_content = itemView.findViewById(R.id.blog_content);
            blog_time = itemView.findViewById(R.id.blog_time);
            blog_readNum = itemView.findViewById(R.id.blog_readNum);
            blog_discussNum = itemView.findViewById(R.id.blog_discussNum);
        }
    }

    @Override
    public int getItemCount() {
        return blogs.size();
    }
}
