package com.haward.weblog.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.haward.weblog.R;
import com.haward.weblog.adapter.HomeRecyclerViewAdapter;
import com.haward.weblog.entity.HomeBlogInfo;
import com.haward.weblog.util.httpUtil;

import java.util.ArrayList;
import java.util.List;

public class homeFragment extends Fragment {
    private static final String TAG = "homeFragment";
    private RecyclerView recyclerView;
    private HomeRecyclerViewAdapter adapter;
    private List<HomeBlogInfo> blogList;
    // 主线程中处理子线程的消息
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Toast.makeText(getActivity(), "请求数据成功", Toast.LENGTH_LONG)
                            .show();
                    break;
                case 2:
                    Toast.makeText(getActivity(), "服务器错误，请稍后再试", Toast.LENGTH_LONG)
                            .show();
                    initData();
                    break;
                default:
                    break;
            }
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            blogList = (ArrayList<HomeBlogInfo>)msg.obj;
            adapter = new HomeRecyclerViewAdapter(blogList,getActivity());
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            recyclerView.setHasFixedSize(true);
            // 组件垂直往下
            recyclerView.setLayoutManager(layoutManager);
            //添加Android自带的分割线
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,null);
        recyclerView = view.findViewById(R.id.home_recyclerView);
        blogList = new ArrayList<>();
        // 在子线程中执行耗时的网络请求操作
        new Thread() {
            @Override
            public void run() {
                super.run();
                blogList = httpUtil.getHomeBlogs(getActivity());
                Message msg = new Message();
                if (!blogList.isEmpty()) {
                    msg.what = 1;
                } else {
                    msg.what = 2;
                }
                msg.obj = blogList;
                handler.sendMessage(msg);
            }
        }.start();
        return view;
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            HomeBlogInfo blogInfo = new HomeBlogInfo();
            blogInfo.setId(i);
            blogInfo.setTitle("apt-get安装" + i);
            blogInfo.setContent("1.数据比如有一条1.数据比如有一条1.数据比如有一条1.数据比如有一条1.数据比如有一条1.数据比如有一条1.数据比如有一条1.数据比如有一条");
            blogInfo.setTime("2018年12月11日");
            blogInfo.setReadNum(322);
            blogInfo.setDiscussNum(i);
            blogList.add(blogInfo);
        }
    }
}
