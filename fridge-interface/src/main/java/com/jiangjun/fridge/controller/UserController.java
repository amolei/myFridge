package com.jiangjun.fridge.controller;

import com.alibaba.fastjson.JSONObject;
import com.jiangjun.fridge.bean.ResBody;
import com.jiangjun.fridge.dao.IUserInfoDao;
import com.jiangjun.fridge.dto.UserInfoDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jiangjun on 2016/7/14.
 */
@Controller
public class UserController {

    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private IUserInfoDao userInfoDao;

    @RequestMapping(value = "login")
    public void login(HttpServletRequest request, HttpServletResponse response){
        int resCode = 1;
        JSONObject item = new JSONObject();
        String jsonRequestStr = request.getParameter("jsonRequest");
        logger.info("login>>>" + jsonRequestStr);
        JSONObject jsonRequest = JSONObject.parseObject(jsonRequestStr);
        if(jsonRequest.containsKey("user_name")
                && jsonRequest.getString("user_name") != null
                && !"".equals(jsonRequest.getString("user_name"))
                && jsonRequest.containsKey("password")
                && jsonRequest.getString("password") != null
                && !"".equals(jsonRequest.getString("password")))
        {
            String user_name = jsonRequest.getString("user_name");
            String password = jsonRequest.getString("password");
            UserInfoDto userInfoDto = userInfoDao.login(user_name,password);
            if(userInfoDto != null){
                resCode = 1;
                item.put("user_info",userInfoDto);
            }else {
                resCode = 0;
            }
        }else{
            resCode = 0;
        }
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            ResBody res = new ResBody();
            res.setResCode(resCode);
            res.setResMsg("success");
            item.put("res",res);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
