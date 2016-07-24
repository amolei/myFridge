package com.jiangjun.fridge.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiangjun.fridge.bean.ResBody;
import com.jiangjun.fridge.dto.FridgeArticle;
import com.jiangjun.fridge.dto.UserAction;
import com.jiangjun.fridge.dto.UserInfoDto;
import com.jiangjun.fridge.service.FridgeArticleService;
import com.jiangjun.fridge.service.UserActionService;
import com.jiangjun.fridge.service.UserInfoService;
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

    @Autowired
    private UserActionService userActionService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 根据文章Id获取评论列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "queryComments")
    public void queryComments(HttpServletRequest request, HttpServletResponse response){
        String jsonRequestStr = request.getParameter("jsonRequest");
        logger.info("queryComments>>>" + jsonRequestStr);
        JSONObject jsonRequest = JSONObject.parseObject(jsonRequestStr);
        long user_id = jsonRequest.getLong("user_id");
        long article_id = jsonRequest.getLong("article_id");
        List<UserAction> comments = userActionService.listByTypeAndArticleId(0,article_id);
        for(UserAction u:comments){
            UserInfoDto userInfoDto = userInfoService.queryByUserId(u.getUser_id());
            u.setUser_name(userInfoDto.getUser_name());
            u.setNick_name(userInfoDto.getNick_name());
            if(u.getComment_id() != null && u.getComment_id() > 0){
                //是回复
                UserAction sourceComment = userActionService.queryById(u.getComment_id());
                UserInfoDto sourceUserInfo = userInfoService.queryByUserId(sourceComment.getUser_id());
                sourceComment.setUser_name(sourceUserInfo.getUser_name());
                sourceComment.setNick_name(sourceUserInfo.getNick_name());
                u.setSourceComment(sourceComment);
            }
        }
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            item.put("items", JSON.toJSON(comments));
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

    @RequestMapping(value = "queryArtileList")
    public void queryArtileList(HttpServletRequest request, HttpServletResponse response) {
        String jsonRequestStr = request.getParameter("jsonRequest");
        logger.info("queryArtileList>>>" + jsonRequestStr);
        JSONObject jsonRequest = JSONObject.parseObject(jsonRequestStr);
        long user_id = jsonRequest.getLong("user_id");
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
                //获取评论列表
                List<UserAction> comments = userActionService.listByTypeAndArticleId(0,f.getArticle_id());
//                for(UserAction u:comments){
//                    UserInfoDto userInfoDto = userInfoService.queryByUserId(u.getUser_id());
//                    u.setUser_name(userInfoDto.getUser_name());
//                    u.setNick_name(userInfoDto.getNick_name());
//                }
//                f.setComments(comments);
                f.setCommentSize(comments.size());
                //点赞的次数
                f.setLikeSize(userActionService.listByTypeAndArticleId(1,f.getArticle_id()).size());
                //收藏的次数
                f.setStoreSize(userActionService.listByTypeAndArticleId(2,f.getArticle_id()).size());
                //获取是否点赞
                if(userActionService.listByParam(f.getArticle_id(),1,user_id).size() > 0)
                    f.setLike(true);
                //获取是否收藏
                if(userActionService.listByParam(f.getArticle_id(),2,user_id).size() > 0)
                    f.setStore(true);
            }
        }
        //获取评论列表
        //获取是否点赞
        //获取是否收藏
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
