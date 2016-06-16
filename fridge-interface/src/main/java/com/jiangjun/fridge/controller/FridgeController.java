package com.jiangjun.fridge.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jiangjun.fridge.bean.ResBody;
import com.jiangjun.fridge.dao.*;
import com.jiangjun.fridge.dto.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jiangjun on 16/6/14.
 */
@Controller
public class FridgeController {
    private static Logger logger = Logger.getLogger(FridgeController.class);

    @Autowired
    private IFoodKindDao foodKindDao;

    @Autowired
    private IFoodInfoDao foodInfoDao;

    @Autowired
    private IUserForFoodDao userForFoodDao;

    @Autowired
    private IShopListDao shopListDao;

    @Autowired
    private IShopListForFoodDao shopListForFoodDao;

    /**
     * 从冰箱中删除食物
     * @param request
     * @param response
     */
    @RequestMapping(value = "delFoodFromFridgeService")
    public void delFoodFromFridgeService(HttpServletRequest request,HttpServletResponse response){
        String jsonRequestStr = request.getParameter("jsonRequest");
        logger.info("jsonRequest>>>" + jsonRequestStr);
        JSONObject jsonRequest = JSONObject.parseObject(jsonRequestStr);
        long user_id = jsonRequest.getLong("user_id");
        JSONArray items = jsonRequest.getJSONArray("items");
        for(int i=0;i<items.size();i++){
            long food_id = items.getJSONObject(i).getLong("food_id");
            userForFoodDao.delFood(user_id,food_id);
        }
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            ResBody res = new ResBody();
            res.setResCode(1);
            res.setResMsg("success");
            item.put("res",res);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 设置购物单中的食物是否购买标志
     * @param request
     * @param response
     */
    @RequestMapping(value = "setFoodStatusByShopListService")
    public void setFoodStatusByShopListService(HttpServletRequest request,HttpServletResponse response){
        String jsonRequestStr = request.getParameter("jsonRequest");
        logger.info("jsonRequest>>>" + jsonRequestStr);
        JSONObject jsonRequest = JSONObject.parseObject(jsonRequestStr);
        long shop_list_id = jsonRequest.getLong("shop_list_id");
        long food_id = jsonRequest.getLong("food_id");
        int status = jsonRequest.getInteger("status");
        ShopListForFoodDto shopListForFoodDto = shopListForFoodDao.queryByShopListAndFoodId(shop_list_id, food_id);
        shopListForFoodDto.setStatus(status);
        shopListForFoodDao.updateStatus(shopListForFoodDto);
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            ResBody res = new ResBody();
            res.setResCode(1);
            res.setResMsg("success");
            item.put("res",res);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 添加食物到购物单
     */
    @RequestMapping(value = "addFoodShopListService")
    public void addFoodShopListService(HttpServletRequest request,HttpServletResponse response){
        String jsonRequestStr = request.getParameter("jsonRequest");
        logger.info("jsonRequest>>>" + jsonRequestStr);
        JSONObject jsonRequest = JSONObject.parseObject(jsonRequestStr);
        long shop_list_id = jsonRequest.getLong("shop_list_id");
        long food_id = jsonRequest.getLong("food_id");
        ShopListForFoodDto shopListForFoodDto = new ShopListForFoodDto();
        shopListForFoodDto.setStatus(0);
        shopListForFoodDto.setShop_list_id(shop_list_id);
        shopListForFoodDto.setFood_id(food_id);
        shopListForFoodDao.addShopListForFood(shopListForFoodDto);
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            ResBody res = new ResBody();
            res.setResCode(1);
            res.setResMsg("success");
            item.put("res",res);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *  添加购物单
     * @param request
     * @param response
     */
    @RequestMapping(value = "addShopListService")
    public void addShopListService(HttpServletRequest request,HttpServletResponse response){
        String jsonRequestStr = request.getParameter("jsonRequest");
        logger.info("jsonRequest>>>" + jsonRequestStr);
        JSONObject jsonRequest = JSONObject.parseObject(jsonRequestStr);
        long user_id = jsonRequest.getLong("user_id");
        String shop_list_name = jsonRequest.getString("shop_list_name");
        ShopListDto shopListDto = new ShopListDto();
        shopListDto.setUser_id(user_id);
        shopListDto.setShop_info_name(shop_list_name);
        shopListDto.setShop_info_date(new Date());
        shopListDao.addShopList(shopListDto);
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            ResBody res = new ResBody();
            res.setResCode(1);
            res.setResMsg("success");
            item.put("res",res);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 添加食物到冰箱
     * @param request
     * @param response
     */
    @RequestMapping(value = "addFoodFridgeService")
    public void addFoodFridgeService(HttpServletRequest request,HttpServletResponse response){
        String jsonRequestStr = request.getParameter("jsonRequest");
        logger.info("jsonRequest>>>" + jsonRequestStr);
        JSONObject jsonRequest = JSONObject.parseObject(jsonRequestStr);
        long user_id = jsonRequest.getLong("user_id");
        JSONObject food_info = jsonRequest.getJSONObject("food_info");
        UserForFoodDto userForFoodDto = new UserForFoodDto();
        userForFoodDto.setUser_id(user_id);
        userForFoodDto.setFood_id(food_info.getLong("food_id"));
        userForFoodDto.setNum(food_info.getInteger("num"));
        userForFoodDto.setWeight(food_info.getString("weight"));
        userForFoodDto.setPast_time(new Date(food_info.getLong("past_time")));
        userForFoodDto.setCreate_time(new Date());
        userForFoodDto.setStatus(1);
        userForFoodDao.addUserForFood(userForFoodDto);
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            ResBody res = new ResBody();
            res.setResCode(1);
            res.setResMsg("success");
            item.put("res",res);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据购物单ID查询购物单详细
     * @param request
     * @param response
     */
    @RequestMapping(value = "queryShopInfoService")
    public void queryShopInfoService(HttpServletRequest request,HttpServletResponse response){
        String jsonRequestStr = request.getParameter("jsonRequest");
        logger.info("jsonRequest>>>" + jsonRequestStr);
        JSONObject jsonRequest = JSONObject.parseObject(jsonRequestStr);
        long shop_list_id = jsonRequest.getLong("shop_list_id");
        List<ShopListForFoodDto> shopListForFoodDtos = shopListForFoodDao.listByShopListId(shop_list_id);
        for(ShopListForFoodDto s:shopListForFoodDtos){
            s.setFood_info(foodInfoDao.getFoodInfoById(s.getFood_id()));
        }
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            item.put("items", JSON.toJSON(shopListForFoodDtos));
            ResBody res = new ResBody();
            res.setResCode(1);
            res.setResMsg("success");
            item.put("res",res);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 获取历史购物单列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "queryShopListService")
    public void queryShopListService(HttpServletRequest request,HttpServletResponse response){
        String jsonRequestStr = request.getParameter("jsonRequest");
        logger.info("jsonRequest>>>" + jsonRequestStr);
        JSONObject jsonRequest = JSONObject.parseObject(jsonRequestStr);
        long user_id = jsonRequest.getLong("user_id");
        List<ShopListDto> shopListDtos = shopListDao.listByUserId(user_id);
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            item.put("items", JSON.toJSON(shopListDtos));
            ResBody res = new ResBody();
            res.setResCode(1);
            res.setResMsg("success");
            item.put("res",res);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取我的食物列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "queryFoodListService")
    public void queryFoodListService(HttpServletRequest request,HttpServletResponse response){
        String jsonRequestStr = request.getParameter("jsonRequest");
        logger.info("jsonRequest>>>" + jsonRequestStr);
        JSONObject jsonRequest = JSONObject.parseObject(jsonRequestStr);
        long user_id = jsonRequest.getLong("user_id");
        List<UserForFoodDto> userForFoodDtos = userForFoodDao.listByUserId(user_id);
        for(UserForFoodDto u:userForFoodDtos){
            long food_id = u.getFood_id();
            FoodInfoDto foodInfoDto = foodInfoDao.getFoodInfoById(food_id);
            u.setFood_info(foodInfoDto);
        }
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            item.put("items", JSON.toJSON(userForFoodDtos));
            ResBody res = new ResBody();
            res.setResCode(1);
            res.setResMsg("success");
            item.put("res",res);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据食物种类获取食物列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "queryFoodListByKindService")
    public void queryFoodListByKind(HttpServletRequest request,HttpServletResponse response){
        String jsonRequestStr = request.getParameter("jsonRequest");
        logger.info("jsonRequest>>>" + jsonRequestStr);
        JSONObject jsonRequest = JSONObject.parseObject(jsonRequestStr);
        long id = jsonRequest.getLong("food_kind_id");
        List<FoodInfoDto> list = foodInfoDao.listByKindId(id);
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            item.put("items", JSON.toJSON(list));
            ResBody res = new ResBody();
            res.setResCode(1);
            res.setResMsg("success");
            item.put("res",res);
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
    @RequestMapping(value = "queryFoodKindsService")
    public void queryFoodKinds(HttpServletRequest request,HttpServletResponse response){
        logger.info("jsonRequest>>>" + request.getParameter("jsonRequest"));
        List<FoodKindDto> list = foodKindDao.list();
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            item.put("items",JSON.toJSON(list));
            ResBody res = new ResBody();
            res.setResCode(1);
            res.setResMsg("success");
            item.put("res",res);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
