package com.haward.weblog.entity;

import android.graphics.drawable.Drawable;

public class MeInfo {
    // 原创
    private int blogNum;
    // 等级
    private int blogLevel;
    // 排名
    private String blogRank;
    // 粉丝
    private int fansNum;
    // 喜欢
    private int attendNum;
    // 昵称
    private String nickName;
    // 头像url
    private String imgUrl;
    // 简介
    private String introduce;
    //评论
    private int discuss;
    //图像
    private Drawable drawable;
    // 积分
    private int blogScore;
    // 访问
    private int readNum;

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public int getBlogScore() {
        return blogScore;
    }

    public void setBlogScore(int blogScore) {
        this.blogScore = blogScore;
    }

    public int getBlogNum() {
        return blogNum;
    }

    public void setBlogNum(int blogNum) {
        this.blogNum = blogNum;
    }

    public int getBlogLevel() {
        return blogLevel;
    }

    public void setBlogLevel(int blogLevel) {
        this.blogLevel = blogLevel;
    }

    public String getBlogRank() {
        return blogRank;
    }

    public void setBlogRank(String blogRank) {
        this.blogRank = blogRank;
    }

    public int getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }

    public int getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(int attendNum) {
        this.attendNum = attendNum;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getDiscuss() {
        return discuss;
    }

    public void setDiscuss(int discuss) {
        this.discuss = discuss;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    @Override
    public String toString() {
        return "MeInfo{" +
                "blogNum=" + blogNum +
                ", blogLevel=" + blogLevel +
                ", blogRank='" + blogRank + '\'' +
                ", fansNum=" + fansNum +
                ", attendNum=" + attendNum +
              //  ", nickName='" + nickName + '\'' +
               // ", imgUrl='" + imgUrl + '\'' +
               // ", introduce='" + introduce + '\'' +
                ", discuss=" + discuss +
              //  ", drawable=" + drawable +
                ", blogScore=" + blogScore +
                ", readNum=" + readNum +
                '}';
    }
}
