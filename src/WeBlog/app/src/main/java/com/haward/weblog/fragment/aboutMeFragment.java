package com.haward.weblog.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.haward.weblog.R;
import com.haward.weblog.entity.MeInfo;
import com.haward.weblog.util.httpUtil;

public class aboutMeFragment extends Fragment {
    private static final String TAG = "aboutMeFragment";
    private MeInfo meInfo;
    private TextView tv_blogNum;
    private TextView tv_fansNum;
    private TextView tv_attendNum;
    private TextView tv_dicuss;
    private TextView tv_blogLevel;
    private TextView tv_blogRank;
    private TextView tv_readNum;
    private TextView tv_blogScore;

    // 主线程中处理子线程的消息
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Toast.makeText(getActivity(), "请求数据成功", Toast.LENGTH_LONG)
                            .show();
                    meInfo = (MeInfo)msg.obj;
                    initView(meInfo);
                    break;
                case 2:
                    Toast.makeText(getActivity(), "服务器错误，请稍后再试", Toast.LENGTH_LONG)
                            .show();
                    break;
                default:
                    break;
            }

        }
    };

    private void initView(MeInfo meInfo) {
        tv_blogNum.setText(meInfo.getBlogNum()+"");
        tv_fansNum.setText(meInfo.getFansNum()+"");
        tv_attendNum.setText(meInfo.getAttendNum()+"");
        tv_dicuss.setText(meInfo.getDiscuss()+"");
        tv_blogLevel.setText(meInfo.getBlogLevel()+"");
        tv_blogRank.setText(meInfo.getBlogRank());
        tv_readNum.setText(meInfo.getReadNum()+"");
        tv_blogScore.setText(meInfo.getBlogScore()+"");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aboutme,null);
        tv_blogNum = view.findViewById(R.id.tv_blogNum);
        tv_fansNum = view.findViewById(R.id.tv_fansNum);
        tv_attendNum = view.findViewById(R.id.tv_attendNum);
        tv_dicuss = view.findViewById(R.id.tv_dicuss);
        tv_blogLevel = view.findViewById(R.id.tv_blogLevel);
        tv_blogRank = view.findViewById(R.id.tv_blogRank);
        tv_readNum = view.findViewById(R.id.tv_readNum);
        tv_blogScore = view.findViewById(R.id.tv_blogScore);
        // 在子线程中执行耗时的网络请求操作
        new Thread() {
            @Override
            public void run() {
                super.run();
                meInfo = httpUtil.getAboutMeData(getActivity());
                Message msg = new Message();
                if (!(meInfo==null)) {
                    msg.what = 1;
                } else {
                    msg.what = 2;
                }
                msg.obj = meInfo;
                handler.sendMessage(msg);
            }
        }.start();
        return view;
    }

}
