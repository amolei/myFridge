package com.jiangjun.fridge.controller;

import EDU.oswego.cs.dl.util.concurrent.FutureResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jiangjun.fridge.dao.IFridgeArticleDao;
import com.jiangjun.fridge.dto.FridgeArticle;
import com.jiangjun.fridge.myThreadPool.FetchWeiboHandler;
import com.jiangjun.fridge.myThreadPool.FetchWeiboThreadPool;
import com.jiangjun.fridge.service.FridgeArticleService;
import com.jiangjun.fridge.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jiangjun on 2016/7/17.
 */
@Controller
public class ArticleManagerController {

    static String[] screen_names = new String[]{"美食健康顾问"};
    static String _FavortiesUrl = "https://api.weibo.com/2/favorites.json";
    static String access_token = "2.00xPW34Bbb2wLCd1aecc5a13bMPoQE";
    static String source = "2008711439";

    @Autowired
    private FridgeArticleService fridgeArticleService;

    @RequestMapping(value = "offLineArticle")
    public String offLine(HttpServletRequest request, HttpServletResponse response){
        long article_id = Long.parseLong(request.getParameter("article_id"));
        int type = Integer.parseInt(request.getParameter("type"));
        FridgeArticle fridgeArticle = fridgeArticleService.getById(article_id);
        fridgeArticle.setStatus("0");
        fridgeArticleService.update(fridgeArticle);
        return "redirect:/queryArticleList.do?type=" + type;
    }

    @RequestMapping(value = "upLineArticle")
    public String upLine(HttpServletRequest request, HttpServletResponse response){
        long article_id = Long.parseLong(request.getParameter("article_id"));
        int type = Integer.parseInt(request.getParameter("type"));
        FridgeArticle fridgeArticle = fridgeArticleService.getById(article_id);
        fridgeArticle.setStatus("1");
        fridgeArticleService.update(fridgeArticle);
        return "redirect:/queryArticleList.do?type=" + type;
    }

    @RequestMapping(value = "queryArticleList")
    public ModelAndView queryArticleList(ModelAndView modelAndView,HttpServletRequest request, HttpServletResponse response){
        int type = Integer.parseInt(request.getParameter("type"));
        List<FridgeArticle> list = fridgeArticleService.listByType(type);
        modelAndView.addObject("fridgeArticleList",list);
        modelAndView.setViewName("ninePicManager");
        return modelAndView;
    }

    @RequestMapping(value = "getArticleFromWeibo")
    public void getArticleFromWeibo(HttpServletRequest request, HttpServletResponse response){
        favortiesHandle(getFavorties());
    }

    public String getFavorties() {
        int count = 10;
        String result = "";
        String url = _FavortiesUrl + "?access_token=" + access_token + "&source=" + source + "&count=" + count;
        return HttpClientUtil.get(url);
    }

    public boolean isInScreenNames(String screen_name){
        for(int i=0;i<screen_names.length;i++){
            if(screen_name.equals(screen_names[i]))
                return true;
        }
        return false;
    }

    public void favortiesHandle(String result) {
        JSONObject obj = JSON.parseObject(result);
        JSONArray array = obj.getJSONArray("favorites");
        for (int i = 0; i < array.size(); i++) {
            JSONObject o = array.getJSONObject(i);
            JSONObject status = o.getJSONObject("status");
            JSONObject user = status.getJSONObject("user");
            if (user != null && user.getString("screen_name")!= null && isInScreenNames(user.getString("screen_name"))) {
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
                        String imgUrl = pic.getString("thumbnail_pic");
                        imgUrl = imgUrl.replaceAll("thumbnail","mw690");//替换成大图
                        picUrlList.add(imgUrl);
                    }
                    fridgeArticle.setPicUrlList(picUrlList);
                    fridgeArticle.setArticle_title(text);
                    fridgeArticle.setArticle_type(1);
                    fridgeArticle.setStatus("0");
                    fridgeArticle.setSource(user.getString("screen_name"));
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
}
