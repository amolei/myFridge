package com.jiangjun.fridge.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jiangjun.fridge.dao.IFoodInfoDao;
import com.jiangjun.fridge.dao.IFoodKindDao;
import com.jiangjun.fridge.dto.FoodInfoDto;
import com.jiangjun.fridge.dto.FoodKindDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by jiangjun on 16/6/14.
 */
@Controller
public class FridgeController {

    @Autowired
    private IFoodKindDao foodKindDao;

    @Autowired
    private IFoodInfoDao foodInfoDao;

    /**
     * 根据食物种类获取食物列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "queryFoodListByKindService",method = RequestMethod.POST)
    public void queryFoodListByKind(HttpServletRequest request,HttpServletResponse response){
        JSONObject jsonRequest = JSONObject.parseObject(request.getParameter("jsonRequest"));
        long id = jsonRequest.getLong("id");
        List<FoodInfoDto> list = foodInfoDao.listByKindId(id);
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            item.put("items",JSON.toJSON(list));
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取食物种类
     * @param request
     * @param response
     */
    @RequestMapping(value = "queryFoodKindsService",method= RequestMethod.POST)
    public void queryFoodKinds(HttpServletRequest request,HttpServletResponse response){
        System.out.println(request.getParameter("jsonRequest"));
        List<FoodKindDto> list = foodKindDao.list();
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            item.put("items",JSON.toJSON(list));
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
