package com.jiangjun.fridge.controller;

import com.jiangjun.fridge.dao.IUserInfoDao;
import com.jiangjun.fridge.dto.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by jiangjun on 16/6/29.
 */
@Controller
public class UserManagerController {

    @Autowired
    private IUserInfoDao userInfoDao;


    @RequestMapping(value = "queryUserInfoList")
    public ModelAndView queryUserList(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response){
        List<UserInfoDto> list = userInfoDao.list();
        modelAndView.setViewName("userInfoManager");
        modelAndView.addObject("userInfoList", list);
        return modelAndView;
    }
}
