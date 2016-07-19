package com.jiangjun.fridge.crawler;

import EDU.oswego.cs.dl.util.concurrent.FutureResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jiangjun.fridge.dto.FridgeArticle;
import com.jiangjun.fridge.myThreadPool.FetchWeiboHandler;
import com.jiangjun.fridge.myThreadPool.FetchWeiboThreadPool;
import com.jiangjun.fridge.service.FridgeArticleService;
import com.jiangjun.fridge.util.HttpClientUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jiangjun on 2016/7/17.
 */
public class WeiboUtil {

    private static FridgeArticleService fridgeArticleService;

    static String _FavortiesUrl = "https://api.weibo.com/2/favorites.json";
    static String access_token = "2.00xPW34Bbb2wLCd1aecc5a13bMPoQE";
    static String source = "2008711439";

    static {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring/spring.xml", "classpath:conf/spring/spring-mybatis.xml"});
        fridgeArticleService = (FridgeArticleService) context.getBean("fridgeArticleService");
    }

    /**
     * 获取我的最新收藏的微博列表
     *
     * @return
     */
    public static String getFavorties() {
        int count = 10;
        String result = "";
        String url = _FavortiesUrl + "?access_token=" + access_token + "&source=" + source + "&count=" + count;
        return HttpClientUtil.get(url);
    }

    public static void favortiesHandle(String result) {
        JSONObject obj = JSON.parseObject(result);
        JSONArray array = obj.getJSONArray("favorites");
        for (int i = 0; i < array.size(); i++) {
            JSONObject o = array.getJSONObject(i);
            JSONObject status = o.getJSONObject("status");
            JSONObject user = status.getJSONObject("user");
            if (user != null && user.getString("screen_name").equals("美食健康顾问")) {
                FridgeArticle fridgeArticle = new FridgeArticle();
                JSONArray pic_urls = status.getJSONArray("pic_urls");
                if (pic_urls != null && pic_urls.size() == 9) {
                    String text = status.getString("text");
                    //判断文章是否已经存在
                    if(fridgeArticleService.listByTitleAndType(text,1).size() > 0){
                        continue;
                    }
                    List<String> picUrlList = new ArrayList<String>();
                    for (int j = 0; j < pic_urls.size(); j++) {
                        JSONObject pic = pic_urls.getJSONObject(j);
                        picUrlList.add(pic.getString("thumbnail_pic"));
                    }
                    fridgeArticle.setPicUrlList(picUrlList);
                    fridgeArticle.setArticle_title(text);
                    fridgeArticle.setArticle_type(1);
                    fridgeArticle.setStatus("0");
                    fridgeArticle.setUpdate_time(new Date());
//                    try {
//                        FetchWeiboThreadPool.execute(new FutureResult().setter(new FetchWeiboHandler(fridgeArticle)));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String result = WeiboUtil.getFavorties();
        favortiesHandle(result);
    }
}