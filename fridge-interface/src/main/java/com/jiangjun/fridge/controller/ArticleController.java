package com.jiangjun.fridge.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiangjun.fridge.bean.ResBody;
import com.jiangjun.fridge.dto.FridgeArticle;
import com.jiangjun.fridge.service.FridgeArticleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangjun on 2016/7/17.
 */
@Controller
public class ArticleController {

    private static Logger logger = Logger.getLogger(ArticleController.class);

    @Autowired
    private FridgeArticleService fridgeArticleService;

    @RequestMapping(value = "queryArtileList")
    public void queryArtileList(HttpServletRequest request, HttpServletResponse response) {
        String jsonRequestStr = request.getParameter("jsonRequest");
        logger.info("queryArtileList>>>" + jsonRequestStr);
        JSONObject jsonRequest = JSONObject.parseObject(jsonRequestStr);
        int type = jsonRequest.getInteger("type");
        int count = jsonRequest.getInteger("count");
        int pageSize = jsonRequest.getInteger("pageSize");
//        count = (count - 1) * pageSize;
        List<FridgeArticle> list = fridgeArticleService.listByPage(count, pageSize, type);
        if(type == 1) {
            //九宫格文章
            List<String> picList = new ArrayList<String>();
            for (FridgeArticle f : list) {
                String[] images = f.getImages().split(",");
                for(String s:images){
                    picList.add(s);
                }
                f.setPicUrlList(picList);
            }
        }
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            item.put("items", JSON.toJSON(list));
            ResBody res = new ResBody();
            res.setResCode(1);
            res.setResMsg("success");
            item.put("res", res);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
