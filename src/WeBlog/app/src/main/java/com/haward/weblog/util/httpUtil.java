package com.haward.weblog.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.haward.weblog.R;
import com.haward.weblog.entity.BlogInfo;
import com.haward.weblog.entity.HomeBlogInfo;
import com.haward.weblog.entity.MeInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class httpUtil {
    private static final String TAG = "httpUtil";

    public static List<BlogInfo> getMyBlogs(Context context) {
        List<BlogInfo> list = new ArrayList<>();
        String url_blogs = context.getString(R.string.url_blogs);
        Log.i(TAG, "getBlogs: baseUrl:" + url_blogs);
        try {
            Document doc = Jsoup.connect(url_blogs).header("User-Agent",
                        "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0").get();
            Log.i(TAG, "getBlogs: " + doc.getElementsByTag("div"));

            Elements elements = doc.select("div.article-list").select("div.article-item-box");
            Log.i(TAG, "getBlogs: " + elements.size());
            for (int i = 0; i < elements.size(); i++) {
                BlogInfo blogInfo = new BlogInfo();
                blogInfo.setId(i);

                Log.i(TAG, "getBlogs: " + i + ".........");
                Log.i(TAG, "getBlogs: " + elements.get(i).toString());

                String url = elements.get(i).select("h4").select("a").attr("href");
                Log.i(TAG, "getBlogs: url :" + url);
                blogInfo.setUrl(url);

                String title = elements.get(i).select("h4").select("a").text().replace("原","");
                Log.i(TAG, "getBlogs: title :" + title);
                blogInfo.setTitle(title);

                String content = elements.get(i).select("p.content").select("a").text();
                Log.i(TAG, "getBlogs: content :" + content);
                blogInfo.setContent(content);

                String time = elements.get(i).select("div.info-box").select("p")
                        .select("span.date").text().split(" ")[0];
                Log.i(TAG, "getBlogs: time :" + time);
                blogInfo.setTime(time);

                String readNum = elements.get(i).select("div.info-box").select("p")
                        .select("span.read-num").select("span.num").get(0).text().toString();
                Log.i(TAG, "getBlogs: readNum :" + readNum);
                blogInfo.setReadNum(Integer.valueOf(readNum));

                String discussNum = elements.get(i).select("div.info-box").select("p")
                        .select("span.read-num").select("span.num").get(1).text().toString();
                Log.i(TAG, "getBlogs: discussNum :" + discussNum);
                blogInfo.setDiscussNum(Integer.valueOf(discussNum));

                Log.i(TAG, "getBlogs: " + blogInfo.toString());

                list.add(blogInfo);
            }
        } catch (Exception e) {
            Log.i(TAG, "getBlogs: " + e.getMessage());
        } finally {
            return list;
        }
    }

    public static List<HomeBlogInfo> getHomeBlogs(Context context) {
        List<HomeBlogInfo> list = new ArrayList<>();
        String url_blogs = context.getString(R.string.url_home);
        Log.i(TAG, "getBlogs: baseUrl:" + url_blogs);
        try {
            Document doc = Jsoup.connect(url_blogs).header("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0").get();
            Elements elements = doc.select("ul.feedlist_mod").select("div.list_con");
            Log.i(TAG, "getBlogs: " + elements.size());
            for (int i = 0; i < elements.size(); i++) {
                HomeBlogInfo blogInfo = new HomeBlogInfo();
                blogInfo.setId(i);

                Log.i(TAG, "getBlogs: " + i + ".........");
                Log.i(TAG, "getBlogs: " + elements.get(i).toString());

                String url = elements.get(i).select("div.title").select("h2")
                        .select("a").attr("href");
                Log.i(TAG, "getBlogs: url :" + url);
                blogInfo.setUrl(url);

                String title = elements.get(i).select("div.title").select("h2")
                        .select("a").text();
                Log.i(TAG, "getBlogs: title :" + title);
                blogInfo.setTitle(title);

                String content = elements.get(i).select("div.summary").text();
                Log.i(TAG, "getBlogs: content :" + content);
                blogInfo.setContent(content);

                String time = elements.get(i).select("dl.list_userbar").select("dd.time").text();
                Log.i(TAG, "getBlogs: time :" + time);
                blogInfo.setTime(time);

                String readNum = elements.get(i).select("dl.list_userbar").select("dd.read_num")
                        .select("a").select("span.num").text();
                Log.i(TAG, "getBlogs: readNum :" + readNum);
                blogInfo.setReadNum(Integer.valueOf(readNum));

                String discussNum = elements.get(i).select("dl.list_userbar").select("dd.common_num")
                        .select("a").select("span.num").text();
                Log.i(TAG, "getBlogs: discussNum :" + discussNum);
                if(discussNum.isEmpty()) {
                    blogInfo.setDiscussNum(0);
                }else {
                    blogInfo.setDiscussNum(Integer.valueOf(discussNum));
                }

                String author = elements.get(i).select("dl.list_userbar").select("dt")
                        .select("a").select("img").attr("title");
                Log.i(TAG, "getBlogs: author :" + author);
                blogInfo.setAuthor(author);

                String imgUrl = elements.get(i).select("dl.list_userbar").select("dt")
                        .select("a").select("img").attr("src");
                Log.i(TAG, "getBlogs: imgUrl :" + imgUrl);
                blogInfo.setImgUrl(imgUrl);

                Log.i(TAG, "getBlogs: " + blogInfo.toString());

                list.add(blogInfo);
            }
        } catch (Exception e) {
            Log.i(TAG, "getBlogs: " + e.getMessage());
        } finally {
            return list;
        }
    }

    public static MeInfo getAboutMeData(Context context) {
        String url_me = context.getString(R.string.url_me);
        Log.i(TAG, "getMeInfo: baseUrl:" + url_me);
        MeInfo meInfo = null;
        try {
            meInfo = new MeInfo();
            Document doc = Jsoup.connect(url_me).header("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0").get();

            Elements elements = doc.select("div.aside-box").select("div.data-info").select("dl.text-center")
                    .select("dd").select("span");
            // 原创
            int n = Integer.valueOf(elements.get(0).text());
            meInfo.setBlogNum(n);
            //粉丝
            int fansNum = Integer.valueOf(elements.get(1).text());
            Log.i(TAG, "getAboutMeData: fansNum: " + fansNum);
            meInfo.setFansNum(fansNum);
            //喜欢
            int attendNum = Integer.valueOf(elements.get(2).text());
            Log.i(TAG, "getAboutMeData: attendNum: " + attendNum);
            meInfo.setAttendNum(attendNum);
            //评论
            int dicuss = Integer.valueOf(elements.get(3).text());
            Log.i(TAG, "getAboutMeData: dicuss: " + dicuss);
            meInfo.setDiscuss(dicuss);

            elements = doc.select("div.aside-box").select("div.grade-box");
            // 等级
            String str = elements.select("dl").select("dd").select("a").attr("title");
            int blogLevel = Integer.valueOf(str.split("级")[0]);
            Log.i(TAG, "getAboutMeData: blogLevel:" + blogLevel);
            meInfo.setBlogLevel(blogLevel);
            // 访问
            str = elements.select("dl").select("dd").get(1).attr("title");
            Log.i(TAG, "getAboutMeData: str:" + str);
            int readNum = Integer.valueOf(str);
            Log.i(TAG, "getAboutMeData: readNum:" + readNum);
            meInfo.setReadNum(readNum);
            // 积分
            str = elements.select("dl").select("dd").get(2).attr("title");
            //Log.i(TAG, "getAboutMeData: str:" + str);
            int score = Integer.valueOf(str);
            Log.i(TAG, "getAboutMeData: scroe:" + score);
            meInfo.setBlogScore(score);
            // 排名
            str = elements.select("dl").select("dd").get(3).text();
            Log.i(TAG, "getAboutMeData: str:" + str);
            meInfo.setBlogRank(str);

            //Log.i(TAG, "getMeInfo: " + meInfo.toString());
        } catch (Exception e) {
            Log.i(TAG, "getMeInfo: " + e.getMessage());
        }
        return meInfo;
    }

    public static Drawable loadImageFromNetwork(String urladdr) {
        Drawable drawable = null;
        try{
            drawable = Drawable.createFromStream(new URL(urladdr).openStream(), "image.jpg");
        }catch(IOException e){
            Log.i(TAG,e.getMessage());
        }
        if(drawable == null){
            Log.i(TAG,"null drawable");
        }else{
            Log.i(TAG,"not null drawable");
        }
        return drawable;
    }
}
