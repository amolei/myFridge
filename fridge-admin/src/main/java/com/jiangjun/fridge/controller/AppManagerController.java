package com.jiangjun.fridge.controller;

import com.alibaba.fastjson.JSONObject;
import com.jiangjun.fridge.dao.IAppInfoDao;
import com.jiangjun.fridge.dto.AppInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.enterprise.inject.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by jiangjun on 16/6/24.
 */
@Controller
public class AppManagerController {

    @Autowired
    private IAppInfoDao appInfoDao;

    @RequestMapping(value = "validateAppVersion")
    public void validateAppVersion(HttpServletRequest request, HttpServletResponse response) {
        String app_version = request.getParameter("app_version");
        int result = 0;
        if (appInfoDao.getAppByVersion(app_version) != null) {
            result = 1;
        }
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            item.put("flag", result);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "validateAppInfoName")
    public void validateAppInfoName(HttpServletRequest request, HttpServletResponse response) {
        String app_name = request.getParameter("app_name");
        int result = 0;
        if (appInfoDao.getAppByName(app_name) != null) {
            result = 1;
        }
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            item.put("flag", result);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "queryAppInfoList")
    public ModelAndView queryAppInfoList(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {
        modelAndView.setViewName("appManager");
        List<AppInfo> list = appInfoDao.list();
        modelAndView.addObject("appInfoList", list);
        return modelAndView;
    }

    @RequestMapping(value = "addAppInfo")
    public void addAppInfo(HttpServletRequest request, HttpServletResponse response) {

    }
}
