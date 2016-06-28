package com.jiangjun.fridge.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiangjun.fridge.dao.IFoodInfoDao;
import com.jiangjun.fridge.dao.IFoodKindDao;
import com.jiangjun.fridge.dto.FoodInfoDto;
import com.jiangjun.fridge.dto.FoodKindDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.font.MultipleMaster;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jiangjun on 16/6/20.
 */
@Controller
public class FoodManagerController {

    public static String rootPath = "/opt";
    public static String imgPath = "/images/";
    public static String appPath = "/apps/";
//    public static String imgPath = "/Users/111/Documents/images";
    @Autowired
    private IFoodKindDao foodKindDao;

    @Autowired
    private IFoodInfoDao foodInfoDao;

    @RequestMapping(value = "delFoodInfoById")
    public String delFoodInfoById(HttpServletRequest request, HttpServletResponse response){
        String food_id = request.getParameter("food_id");
        foodInfoDao.delFoodInfoById(Long.parseLong(food_id));
        return "redirect:/queryFoodList.do";
    }

    @RequestMapping(value = "delFoodKindById")
    public String delFoodKindById(HttpServletRequest request, HttpServletResponse response){
        String kind_id = request.getParameter("kind_id");
        foodKindDao.delFoodKindById(Long.parseLong(kind_id));
        return "redirect:/queryFoodKindList.do";
    }

    @RequestMapping(value = "validateFoodKindById")
    public void validateFoodKindById(HttpServletRequest request, HttpServletResponse response){
        long kind_id = Long.parseLong(request.getParameter("kind_id"));
        List<FoodInfoDto> list = foodInfoDao.listByKindId(kind_id);
        int result = 0;
        if(list != null && list.size() > 1){
            //不能删除
            result = 1;
        }
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            item.put("flag", result);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "validateFoodInfoName")
    public void validateFoodInfoName(HttpServletRequest request, HttpServletResponse response){
        String food_name = request.getParameter("food_name");
        int result = 0;
        if(foodInfoDao.getFoodInfoByName(food_name) != null){
            result = 1;
        }
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            item.put("flag", result);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "validateFoodKindName")
    public void validateFoodKindName(HttpServletRequest request, HttpServletResponse response){
        String food_kind_name = request.getParameter("food_kind_name");
        int result = 0;
        if(foodKindDao.queryByName(food_kind_name) != null){
            result = 1;
        }
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            item.put("flag", result);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "createFoodInfo")
    public String createFoodInfo(HttpServletRequest request, HttpServletResponse response){
        String food_name = request.getParameter("food_name");
        String food_info = request.getParameter("food_info");
        String simpel_name = request.getParameter("simple_name");
        String food_kind_id = request.getParameter("food_kind");
        String hot = request.getParameter("hot");
        String food_img = "";
        try {
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            //检查form中是否有enctype="multipart/form-data"
            if (multipartResolver.isMultipart(request)) {
                //将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                //获取multiRequest 中所有的文件名
                Iterator iter = multiRequest.getFileNames();

                while (iter.hasNext()) {
                    //一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    if (file != null) {
                        String path = imgPath + File.separator + file.getOriginalFilename();
                        food_img = rootPath + path;
                        //上传
                        file.transferTo(new File(path));
                    }
                }
            }
            FoodInfoDto foodInfoDto = new FoodInfoDto();
            foodInfoDto.setFood_name(food_name);
            foodInfoDto.setFood_des(food_info);
            foodInfoDto.setSimple_name(simpel_name);
            foodInfoDto.setKind_id(Long.parseLong(food_kind_id));
            foodInfoDto.setFood_img(food_img);
            foodInfoDto.setHot(Integer.parseInt(hot));
//            foodInfoDao.addFoodInfo(foodInfoDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/queryFoodList.do";
    }

    @RequestMapping(value = "createFoodKind")
    public String createFoodKind(HttpServletRequest request, HttpServletResponse response) {
        String food_kind_name = request.getParameter("food_kind_name");
        String food_kind_info = request.getParameter("food_kind_info");
        String simple_name = request.getParameter("simple_name");
        String food_kind_img = "";
        try {
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            //检查form中是否有enctype="multipart/form-data"
            if (multipartResolver.isMultipart(request)) {
                //将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                //获取multiRequest 中所有的文件名
                Iterator iter = multiRequest.getFileNames();

                while (iter.hasNext()) {
                    //一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    if (file != null) {
                        String path = imgPath + File.separator + file.getOriginalFilename();
                        food_kind_img = rootPath + path;
                        //上传
                        file.transferTo(new File(path));
                    }
                }
            }
            FoodKindDto foodKindDto = new FoodKindDto();
            foodKindDto.setFood_kind_name(food_kind_name);
            foodKindDto.setFood_kind_info(food_kind_info);
            foodKindDto.setSimple_name(simple_name);
            foodKindDto.setFood_kind_img(food_kind_img);
            foodKindDao.addFoodKind(foodKindDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/queryFoodKindList.do";
    }

    @RequestMapping(value = "queryFoodListByKindId")
    public void queryFoodListByKindId(HttpServletRequest request, HttpServletResponse response) {
        long food_kind_id = Long.parseLong(request.getParameter("food_kind_id"));
        List<FoodInfoDto> foodInfoDtos = foodInfoDao.listByKindId(food_kind_id);
        for (FoodInfoDto f : foodInfoDtos) {
            f.setFoodKindDto(foodKindDao.queryById(f.getKind_id()));
        }
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            item.put("items", JSON.toJSON(foodInfoDtos));
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "queryFoodKindList4Ajax")
    public void queryFoodKindList4Ajax(HttpServletRequest request, HttpServletResponse response) {
        List<FoodKindDto> foodKindDtoList = foodKindDao.list();
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            item.put("items", JSON.toJSON(foodKindDtoList));
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "queryFoodKindList")
    public ModelAndView queryFoodKindList(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {
        String result = "foodKindManager";
        List<FoodKindDto> foodKindDtoList = foodKindDao.list();
        modelAndView.setViewName(result);
        modelAndView.addObject("user", "jiangjun");
        modelAndView.addObject("foodKindList", foodKindDtoList);
        return modelAndView;
    }

    @RequestMapping(value = "queryFoodList")
    public ModelAndView queryFoodList(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {
        List<FoodInfoDto> foodInfoDtoList = foodInfoDao.list();
        for (FoodInfoDto f : foodInfoDtoList) {
            f.setFoodKindDto(foodKindDao.queryById(f.getKind_id()));
        }
        modelAndView.setViewName("foodInfoManager");
        modelAndView.addObject("foodInfoList", foodInfoDtoList);
        return modelAndView;
    }
}
