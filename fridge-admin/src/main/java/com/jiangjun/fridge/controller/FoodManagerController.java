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

    public static String rootPath = "/opt/content";
    public static String imgPath = "/images/";
    public static String appPath = "/apps/";
//        public static String imgPath = "/Users/111/Documents/images";

    @Autowired
    private IFoodKindDao foodKindDao;

    @Autowired
    private IFoodInfoDao foodInfoDao;

    @RequestMapping(value = "upFoodInfo")
    public String upFoodInfo(HttpServletRequest request, HttpServletResponse response){
        Long food_id = Long.parseLong(request.getParameter("food_id"));
        FoodInfoDto foodInfoDto = foodInfoDao.getFoodInfoById(food_id);
        Long kind_id = foodInfoDto.getKind_id();
        Integer sort = foodInfoDto.getSort();
        List<FoodInfoDto> list = foodInfoDao.listLtBySort(foodInfoDto);
        if(list != null && list.size() > 0){
            FoodInfoDto litter = list.get(list.size() - 1);
            Integer litterSort = litter.getSort();
            foodInfoDto.setSort(litterSort);
            litter.setSort(sort);
            foodInfoDao.update(litter);
            foodInfoDao.update(foodInfoDto);
        }
        return "redirect:/queryFoodList.do?kind_id=" + kind_id;
    }

    @RequestMapping(value = "downFoodInfo")
    public String downFoodInfo(HttpServletRequest request, HttpServletResponse response){
        Long food_id = Long.parseLong(request.getParameter("food_id"));
        FoodInfoDto foodInfoDto = foodInfoDao.getFoodInfoById(food_id);
        Long kind_id = foodInfoDto.getKind_id();
        Integer sort = foodInfoDto.getSort();
        List<FoodInfoDto> list = foodInfoDao.listGtBySort(foodInfoDto);
        if(list != null && list.size() > 0){
            FoodInfoDto greater = list.get(0);
            Integer greaterSort = greater.getSort();
            foodInfoDto.setSort(greaterSort);
            greater.setSort(sort);
            foodInfoDao.update(greater);
            foodInfoDao.update(foodInfoDto);
        }
        return "redirect:/queryFoodList.do?kind_id=" + kind_id;
    }

    @RequestMapping(value = "upFoodKind")
    public String upFoodKind(HttpServletRequest request, HttpServletResponse response){
        Long kind_id = Long.parseLong(request.getParameter("food_kind_id"));
        FoodKindDto foodKindDto = foodKindDao.queryById(kind_id);
        Integer sort = foodKindDto.getSort();
        List<FoodKindDto> list = foodKindDao.listLtBySort(sort);
        if(list != null && list.size() > 0){
            FoodKindDto little = list.get(list.size() - 1);
            Integer littleSort = little.getSort();
            little.setSort(sort);
            foodKindDto.setSort(littleSort);
            foodKindDao.update(little);
            foodKindDao.update(foodKindDto);
        }
        return "redirect:/queryFoodKindList.do";
    }

    @RequestMapping(value = "downFoodKind")
    public String downFoodKind(HttpServletRequest request, HttpServletResponse response){
        Long food_kind_id = Long.parseLong(request.getParameter("food_kind_id"));
        FoodKindDto foodKindDto = foodKindDao.queryById(food_kind_id);
        Integer sort = foodKindDto.getSort();
        List<FoodKindDto> list = foodKindDao.listGtBySort(sort);
        if(list != null && list.size() > 0){
            FoodKindDto greater = list.get(0);
            Integer greaterSort = greater.getSort();
            greater.setSort(sort);
            foodKindDto.setSort(greaterSort);
            foodKindDao.update(greater);
            foodKindDao.update(foodKindDto);
        }
        return "redirect:/queryFoodKindList.do";
    }

    @RequestMapping(value = "delFoodInfoById")
    public String delFoodInfoById(HttpServletRequest request, HttpServletResponse response) {
        String food_id = request.getParameter("food_id");
        foodInfoDao.delFoodInfoById(Long.parseLong(food_id));
        return "redirect:/queryFoodList.do";
    }

    @RequestMapping(value = "delFoodKindById")
    public String delFoodKindById(HttpServletRequest request, HttpServletResponse response) {
        String kind_id = request.getParameter("kind_id");
        foodKindDao.delFoodKindById(Long.parseLong(kind_id));
        return "redirect:/queryFoodKindList.do";
    }

    @RequestMapping(value = "validateFoodKindById")
    public void validateFoodKindById(HttpServletRequest request, HttpServletResponse response) {
        long kind_id = Long.parseLong(request.getParameter("kind_id"));
        List<FoodInfoDto> list = foodInfoDao.listByKindId(kind_id);
        int result = 0;
        if (list != null && list.size() > 1) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "validateFoodInfoName")
    public void validateFoodInfoName(HttpServletRequest request, HttpServletResponse response) {
        String food_name = request.getParameter("food_name");
        int result = 0;
        if (foodInfoDao.getFoodInfoByName(food_name) != null) {
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

    @RequestMapping(value = "validateFoodKindName")
    public void validateFoodKindName(HttpServletRequest request, HttpServletResponse response) {
        String food_kind_name = request.getParameter("food_kind_name");
        int result = 0;
        if (foodKindDao.queryByName(food_kind_name) != null) {
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

    @RequestMapping(value = "createFoodInfo")
    public String createFoodInfo(HttpServletRequest request, HttpServletResponse response) {
        String food_name = request.getParameter("food_name");
        String food_info = request.getParameter("food_info");
        String simpel_name = request.getParameter("simple_name");
        String food_kind_id = request.getParameter("food_kind");
        String hot = "0";
        if(request.getParameter("hot") != null) {
            hot = request.getParameter("hot");
        }
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
                        if(file.getOriginalFilename() != null && !file.getOriginalFilename().trim().equals("")) {
                            String path = imgPath + File.separator + file.getOriginalFilename();
                            food_img =  path;
                            String uploadPath = rootPath + path;
                            //上传
                            file.transferTo(new File(uploadPath));
                        }
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
            foodInfoDao.addFoodInfo(foodInfoDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/queryFoodList.do?kind_id=" + food_kind_id;
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
                        if(file.getOriginalFilename() != null && !file.getOriginalFilename().trim().equals("")) {
                            String path = imgPath + File.separator + file.getOriginalFilename();
                            food_kind_img =  path;
                            String uploadPath = rootPath + path;
                            //上传
                            file.transferTo(new File(uploadPath));
                        }
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
        List<FoodInfoDto> foodInfoDtos;
        long food_kind_id = Long.parseLong(request.getParameter("food_kind_id"));
        if (food_kind_id == -1) {
            foodInfoDtos = foodInfoDao.list();
        } else {
            foodInfoDtos = foodInfoDao.listByKindId(food_kind_id);
        }
        for (FoodInfoDto f : foodInfoDtos) {
            f.setKind_name(foodKindDao.queryById(f.getKind_id()).getFood_kind_name());
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
        List<FoodKindDto> foodKindDtos = foodKindDao.list();
        List<FoodInfoDto> foodInfoDtoList;
        if(request.getParameter("kind_id") != null){
            foodInfoDtoList = foodInfoDao.listByKindId(Long.parseLong(request.getParameter("kind_id")));
            for(int i=0;i<foodKindDtos.size();i++){
                if(foodKindDtos.get(i).getFood_kind_id() == Long.parseLong(request.getParameter("kind_id"))){
                    FoodKindDto tmp = foodKindDtos.get(i);
                    foodKindDtos.remove(i);
                    foodKindDtos.add(0,tmp);
                    break;
                }
            }
        }else {
            foodInfoDtoList = foodInfoDao.listByKindId(foodKindDtos.get(0).getFood_kind_id());
        }
        for (FoodInfoDto f : foodInfoDtoList) {
            f.setKind_name(foodKindDao.queryById(f.getKind_id()).getFood_kind_name());
        }
        modelAndView.setViewName("foodInfoManager");
        modelAndView.addObject("foodInfoList", foodInfoDtoList);
        modelAndView.addObject("foodKindList", foodKindDtos);
        return modelAndView;
    }

    @RequestMapping(value = "getFoodKindById")
    public void getFoodKindById(HttpServletRequest request, HttpServletResponse response){
        String kind_id = request.getParameter("id");
        FoodKindDto foodKindDto = foodKindDao.queryById(Long.parseLong(kind_id));
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            item.put("result","1");
            item.put("foodKind", foodKindDto);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "getFoodInfoById")
    public void getFoodInfoById(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        FoodInfoDto foodInfoDto = foodInfoDao.getFoodInfoById(Long.parseLong(id));
        List<FoodKindDto> foodKindDtoList = foodKindDao.list();
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            item.put("result","1");
            item.put("foodInfo", foodInfoDto);
            item.put("foodKindList",foodKindDtoList);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "updateFoodInfo")
    public String updateFoodInfo(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("modify_food_id");
        String food_name = request.getParameter("modify_food_name");
        String food_info = request.getParameter("modify_food_info");
        String simpel_name = request.getParameter("modify_simple_name");
        String food_kind_id = request.getParameter("modify_food_kind");
        String hot = "0";
        FoodInfoDto foodInfoDto = foodInfoDao.getFoodInfoById(Long.parseLong(id));
        String food_img = foodInfoDto.getFood_img();
        if(request.getParameter("modify_hot") != null) {
            hot = request.getParameter("modify_hot");
        }
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
                        if(file.getOriginalFilename() != null && !file.getOriginalFilename().trim().equals("")) {
                            String path = imgPath + File.separator + file.getOriginalFilename();
                            food_img =  path;
                            String uploadPath = rootPath + path;
                            //上传
                            file.transferTo(new File(uploadPath));
                        }
                    }
                }
            }
            foodInfoDto.setFood_name(food_name);
            foodInfoDto.setFood_des(food_info);
            foodInfoDto.setSimple_name(simpel_name);
            foodInfoDto.setKind_id(Long.parseLong(food_kind_id));
            foodInfoDto.setFood_img(food_img);
            foodInfoDto.setHot(Integer.parseInt(hot));
            foodInfoDao.update(foodInfoDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/queryFoodList.do";
    }

    @RequestMapping(value = "updateFoodKind")
    public String updateFoodKind(HttpServletRequest request, HttpServletResponse response){
        long food_kind_id = Long.parseLong(request.getParameter("modifyModal_food_kind_id"));
        String food_kind_name = request.getParameter("modifyModal_food_kind_name");
        String food_kind_info = request.getParameter("modifyModal_food_kind_info");
        String simple_name = request.getParameter("modifyModal_simple_name");
        String food_kind_img = "";
        FoodKindDto foodKindDto = foodKindDao.queryById(food_kind_id);
        foodKindDto.setFood_kind_name(food_kind_name);
        foodKindDto.setFood_kind_info(food_kind_info);
        foodKindDto.setSimple_name(simple_name);
        food_kind_img = foodKindDto.getFood_kind_img();
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
                    String fileS = iter.next().toString();
                    MultipartFile file = multiRequest.getFile(fileS);
                    if (file != null) {
                        if (file.getOriginalFilename() != null && !file.getOriginalFilename().trim().equals("")) {
                            String path = imgPath + File.separator + file.getOriginalFilename();
                            food_kind_img =  path;
                            String uploadPath = rootPath + path;
                            //上传
                            file.transferTo(new File(uploadPath));
                        }
                    }
                }
            }
            foodKindDto.setFood_kind_img(food_kind_img);
            foodKindDao.update(foodKindDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/queryFoodKindList.do";
    }

}
