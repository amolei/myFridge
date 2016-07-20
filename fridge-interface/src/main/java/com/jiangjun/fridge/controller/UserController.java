package com.jiangjun.fridge.controller;

import com.alibaba.fastjson.JSONObject;
import com.jiangjun.fridge.bean.ResBody;
import com.jiangjun.fridge.dao.IUserInfoDao;
import com.jiangjun.fridge.dto.UserAction;
import com.jiangjun.fridge.dto.UserForPoint;
import com.jiangjun.fridge.dto.UserInfoDto;
import com.jiangjun.fridge.service.UserActionService;
import com.jiangjun.fridge.service.UserForPointService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by jiangjun on 2016/7/14.
 */
@Controller
public class UserController {

    private static Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private IUserInfoDao userInfoDao;
    @Autowired
    private UserActionService userActionService;
    @Autowired
    private UserForPointService userForPointService;

    @RequestMapping(value = "addAction")
    public void addAction(HttpServletRequest request, HttpServletResponse response) {
        String jsonRequestStr = request.getParameter("jsonRequest");
        logger.info("addAction>>>" + jsonRequestStr);
        JSONObject jsonRequest = JSONObject.parseObject(jsonRequestStr);
        long user_id = jsonRequest.getLong("user_id");
        long article_id = jsonRequest.getLong("article_id");
        int type = jsonRequest.getInteger("type");
        UserAction userAction = new UserAction();
        userAction.setUser_id(user_id);
        userAction.setArticle_id(article_id);
        userAction.setAction_type(type);
        userAction.setUpdate_time(new Date());
        if (type == 0) {
            userAction.setContent(jsonRequest.getString("content"));
        }
        userActionService.add(userAction);
        //添加积分
        int point = type == 0 ? 2 : 1;
        UserForPoint userForPoint = new UserForPoint();
        userForPoint.setUser_id(user_id);
        userForPoint.setPoint(point);
        userForPoint.setAction_id(userAction.getAction_id());
        userForPointService.add(userForPoint);
        //更新总积分
        UserInfoDto userInfoDto = userInfoDao.queryByUserId(user_id);
        userInfoDto.setPoint(userInfoDto.getPoint() + point);
        userInfoDao.update(userInfoDto);
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
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

    @RequestMapping(value = "login")
    public void login(HttpServletRequest request, HttpServletResponse response) {
        int resCode = 1;
        JSONObject item = new JSONObject();
        String jsonRequestStr = request.getParameter("jsonRequest");
        logger.info("login>>>" + jsonRequestStr);
        JSONObject jsonRequest = JSONObject.parseObject(jsonRequestStr);
        if (jsonRequest.containsKey("user_name")
                && jsonRequest.getString("user_name") != null
                && !"".equals(jsonRequest.getString("user_name"))
                && jsonRequest.containsKey("password")
                && jsonRequest.getString("password") != null
                && !"".equals(jsonRequest.getString("password"))) {
            String user_name = jsonRequest.getString("user_name");
            String password = jsonRequest.getString("password");
            UserInfoDto userInfoDto = userInfoDao.login(user_name, password);
            if (userInfoDto != null) {
                resCode = 1;
                item.put("user_info", userInfoDto);
            } else {
                resCode = 0;
            }
        } else {
            resCode = 0;
        }
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            ResBody res = new ResBody();
            res.setResCode(resCode);
            res.setResMsg("success");
            item.put("res", res);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
