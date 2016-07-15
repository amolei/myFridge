package com.jiangjun.fridge.crawler;

import com.jiangjun.fridge.util.HttpClientUtil;

/**
 * Created by jiangjun on 2016/7/14.
 */
public class WeiboTest {

    static String weiborl = "https://api.weibo.com/2/users/show.json?";
    static String screen_name = "美食健康顾问";
    static String source = "2008711439";
    static String token = "2.00xPW34Bbb2wLCd1aecc5a13bMPoQE";

    public static void main(String[] args){
        System.out.println("aaa");
        String url = weiborl + "source=" + source + "&access_token=" + token + "&screen_name=" + screen_name;
        String result = HttpClientUtil.get(url);
        System.out.println(result);
    }
}
