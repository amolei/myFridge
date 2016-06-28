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
import java.util.*;

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

    @Autowired
    private IUserInfoDao userInfoDao;

    /**
     * 从历史购物单中查询食品
     * @param request
     * @param response
     */
    @RequestMapping(value = "queryHistoryFromShopList")
    public void queryHistoryFromShopList(HttpServletRequest request,HttpServletResponse response){
        String jsonRequestStr = request.getParameter("jsonRequest");
        logger.info("setFoodStatusByShopListService>>>" + jsonRequestStr);
        JSONObject jsonRequest = JSONObject.parseObject(jsonRequestStr);
        long user_id = jsonRequest.getLong("user_id");
        //根据user_id查询出shoplist
        List<ShopListDto> list = shopListDao.listByUserId(user_id);
        Set<Long> food_ids = new HashSet<Long>();
        //根据shoplist id查询出食物Id列表
        for(ShopListDto s:list){
            long shop_list_id = s.getShop_list_id();
            List<ShopListForFoodDto> shopListForFoodDtos = shopListForFoodDao.listByShopListId(shop_list_id);
            for(ShopListForFoodDto sL:shopListForFoodDtos){
                food_ids.add(sL.getFood_id());
            }
        }
        //根据食物ID列表查询食物信息列表
        List<FoodInfoDto> foodInfoDtos = new ArrayList<FoodInfoDto>();
        Iterator it = food_ids.iterator();
        while(it.hasNext()){
            Long food_id = (Long) it.next();
            FoodInfoDto foodInfoDto = foodInfoDao.getFoodInfoById(food_id);
            if(foodInfoDto != null) {
                FoodKindDto foodKindDto = foodKindDao.queryById(foodInfoDto.getKind_id());
                foodInfoDto.setKind_name(foodKindDto.getFood_kind_name());
                foodInfoDtos.add(foodInfoDto);
            }
        }
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            ResBody res = new ResBody();
            res.setResCode(1);
            res.setResMsg("success");
            item.put("res", res);
            item.put("items",foodInfoDtos);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 从冰箱中删除食物
     * @param request
     * @param response
     */
    @RequestMapping(value = "delFoodFromFridgeService")
    public void delFoodFromFridgeService(HttpServletRequest request,HttpServletResponse response){
        String jsonRequestStr = request.getParameter("jsonRequest");
        logger.info("delFoodFromFridgeService>>>" + jsonRequestStr);
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
        logger.info("setFoodStatusByShopListService>>>" + jsonRequestStr);
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
        logger.info("addFoodShopListService>>>" + jsonRequestStr);
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
        logger.info("addShopListService>>>" + jsonRequestStr);
        JSONObject jsonRequest = JSONObject.parseObject(jsonRequestStr);
        long user_id = jsonRequest.getLong("user_id");
        String user_name = userInfoDao.queryByUserId(user_id).getUser_name();
        String shop_list_name = user_name + "'ShopList>" + new Date().getTime();
        ShopListDto shopListDto = new ShopListDto();
        shopListDto.setUser_id(user_id);
        shopListDto.setShop_info_name(shop_list_name);
        shopListDto.setShop_info_date(new Date());
        shopListDao.addShopList(shopListDto);
        Long shop_list_id = shopListDto.getShop_list_id();
        if(shop_list_id != null) {
            JSONArray items = jsonRequest.getJSONArray("items");
            for (int i = 0; i < items.size(); i++) {
                ShopListForFoodDto shopListForFoodDto = new ShopListForFoodDto();
                JSONObject item = items.getJSONObject(i);
                long food_id;
                if(item.getInteger("is_tmp") != null && item.getInteger("is_tmp") == 1){
                    if(item.containsKey("food_id") && item.getLong("food_id") != null){
                        food_id = item.getLong("food_id");
                    }else {
                        /**
                         * 新加临时食品
                         * 1.添加到临时食品库
                         * 2.购物单绑定临时食品
                         */
                        FoodInfoDto tmp = new FoodInfoDto();
                        tmp.setFood_name(item.getString("food_name"));
                        tmp.setFood_des(item.getString("food_name"));
                        tmp.setHot(0);
                        tmp.setKind_id(item.getLong("kind_id"));
                        tmp.setIs_tmp(1);
                        foodInfoDao.addFoodInfo(tmp);
                        food_id = tmp.getFood_id();
                    }
                }else {
                    food_id = item.getLong("food_id");
                }
                shopListForFoodDto.setFood_id(food_id);
                shopListForFoodDto.setStatus(0);
                shopListForFoodDto.setShop_list_id(shop_list_id);
                shopListForFoodDao.addShopListForFood(shopListForFoodDto);
            }
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
     * 添加食物到冰箱
     * @param request
     * @param response
     */
    @RequestMapping(value = "addFoodFridgeService")
    public void addFoodFridgeService(HttpServletRequest request,HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        String jsonRequestStr = request.getParameter("jsonRequest");
        logger.info("addFoodFridgeService>>>" + jsonRequestStr);
        JSONObject jsonRequest = JSONObject.parseObject(jsonRequestStr);
        long user_id = jsonRequest.getLong("user_id");
        JSONArray food_infos = jsonRequest.getJSONArray("items");
        for(int i=0;i<food_infos.size();i++){
            JSONObject obj = food_infos.getJSONObject(i);
            UserForFoodDto userForFoodDto = new UserForFoodDto();
            long food_id;
            if(obj.containsKey("is_tmp") && obj.getInteger("is_tmp") == 1){
                if(obj.containsKey("food_id") && obj.getLong("food_id") != null){
                    food_id = obj.getLong("food_id");
                }else{
                    //新增到食品库
                    FoodInfoDto foodInfo_tmp = new FoodInfoDto();
                    foodInfo_tmp.setFood_name(obj.getString("food_name"));
                    foodInfo_tmp.setFood_des(obj.getString("food_name"));
                    foodInfo_tmp.setKind_id(obj.getLong("kind_id"));
                    foodInfo_tmp.setHot(0);
                    foodInfo_tmp.setIs_tmp(1);
                    foodInfoDao.addFoodInfo(foodInfo_tmp);
                    food_id = foodInfo_tmp.getFood_id();
                }
            }else{
                food_id = obj.getLong("food_id");
            }
            userForFoodDto.setUser_id(user_id);
            userForFoodDto.setFood_id(food_id);
            userForFoodDto.setNum(obj.getString("num"));
            userForFoodDto.setComment(obj.getString("comment"));
            userForFoodDto.setPast_time(new Date(obj.getLong("past_time")));
            userForFoodDto.setCreate_time(new Date());
            userForFoodDto.setStatus(1);
            userForFoodDao.addUserForFood(userForFoodDto);
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
     * 根据购物单ID查询购物单详细
     * @param request
     * @param response
     */
    @RequestMapping(value = "queryShopInfoService")
    public void queryShopInfoService(HttpServletRequest request,HttpServletResponse response){
        String jsonRequestStr = request.getParameter("jsonRequest");
        logger.info("queryShopInfoService>>>" + jsonRequestStr);
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
        logger.info("queryShopListService>>>" + jsonRequestStr);
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
        logger.info("queryFoodListService>>>" + jsonRequestStr);
        JSONObject jsonRequest = JSONObject.parseObject(jsonRequestStr);
        long user_id = jsonRequest.getLong("user_id");
        List<UserForFoodDto> userForFoodDtos = userForFoodDao.listByUserId(user_id);
        for(UserForFoodDto u:userForFoodDtos){
            long food_id = u.getFood_id();
            FoodInfoDto foodInfoDto = foodInfoDao.getFoodInfoById(food_id);
            if(foodInfoDto != null) {
                FoodKindDto foodKindDto = foodKindDao.queryById(foodInfoDto.getKind_id());
                if(foodKindDto != null) {
                    foodInfoDto.setKind_name(foodKindDto.getFood_kind_name());
                    u.setFood_info(foodInfoDto);
                }
            }
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
        logger.info("queryFoodListByKindService>>>" + jsonRequestStr);
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
        logger.info("queryFoodKindsService>>>" + request.getParameter("jsonRequest"));
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
