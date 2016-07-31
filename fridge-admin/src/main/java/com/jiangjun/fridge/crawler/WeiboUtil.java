package com.jiangjun.fridge.crawler;

import EDU.oswego.cs.dl.util.concurrent.FutureResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jiangjun.fridge.dto.CrawlerSource;
import com.jiangjun.fridge.dto.FridgeArticle;
import com.jiangjun.fridge.myThreadPool.FetchWeiboHandler;
import com.jiangjun.fridge.myThreadPool.FetchWeiboThreadPool;
import com.jiangjun.fridge.service.CrawlerSourceService;
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
    private static CrawlerSourceService crawlerSourceService;

    static String _FavortiesUrl = "https://api.weibo.com/2/favorites.json";
    static String access_token = "2.00xPW34Bbb2wLCd1aecc5a13bMPoQE";
    static String source = "2008711439";

    static {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring/spring.xml", "classpath:conf/spring/spring-mybatis.xml"});
        fridgeArticleService = (FridgeArticleService) context.getBean("fridgeArticleService");
        crawlerSourceService = (CrawlerSourceService) context.getBean("crawlerSourceService");
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

    public static boolean isInScreenNames(String screen_name, List<String> screen_names) {
        if (screen_names.contains(screen_name))
            return true;
        return false;
    }

    public static void favortiesHandle(String result,List<String> screen_names) {
        JSONObject obj = JSON.parseObject(result);
        JSONArray array = obj.getJSONArray("favorites");
        for (int i = 0; i < array.size(); i++) {
            JSONObject o = array.getJSONObject(i);
            JSONObject status = o.getJSONObject("status");
            JSONObject user = status.getJSONObject("user");
            if (user != null && isInScreenNames(user.getString("screen_name"),screen_names)) {
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
                    fridgeArticle.setUser_id(1L);
                    fridgeArticle.setPicUrlList(picUrlList);
                    fridgeArticle.setArticle_title(text);
                    fridgeArticle.setArticle_type(1);
                    fridgeArticle.setStatus("0");
                    fridgeArticle.setUpdate_time(new Date());
                    try {
                        FetchWeiboThreadPool.execute(new FutureResult().setter(new FetchWeiboHandler(fridgeArticle,fridgeArticleService)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        List<String> screen_names = new ArrayList<String>();
        List<CrawlerSource> crawlerSources = crawlerSourceService.listByType(0);
        for (CrawlerSource c : crawlerSources) {
            screen_names.add(c.getSource_name());
        }
        String result = WeiboUtil.getFavorties();
        favortiesHandle(result,screen_names);
    }
}
