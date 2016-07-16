package com.jiangjun.fridge.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jiangjun.fridge.crawler.FridgeBrandsUtil;
import com.jiangjun.fridge.crawler.ImageUtil;
import com.jiangjun.fridge.dao.IFridgeBrandDao;
import com.jiangjun.fridge.dao.IFridgeSellerDao;
import com.jiangjun.fridge.dto.FoodInfoDto;
import com.jiangjun.fridge.dto.FridgeBrandDto;
import com.jiangjun.fridge.dto.FridgeSellerDto;
import com.jiangjun.fridge.util.HttpClientUtil;
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
import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jiangjun on 2016/7/14.
 */
@Controller
public class FridgeManagerController {

    public static String BRAND_FROM_JD = "http://list.jd.com/list.html?cat=737,794,878&md=1&my=list_brand";
//    public static String rootPath = "/opt/content";
    public static String rootPath = "C:\\Users\\jiangjun\\Documents";
//    public static String imgPath = "/brand_logo/";
    public static String imgPath = "\\brand_logo\\";

    @Autowired
    private IFridgeBrandDao fridgeBrandDao;

    @Autowired
    private IFridgeSellerDao fridgeSellerDao;

    @RequestMapping(value = "updateFridgeSeller")
    public String updateFridgeSeller(HttpServletRequest request,HttpServletResponse response){
        Long seller_id = Long.parseLong(request.getParameter("seller_id"));
        String seller_name = request.getParameter("modifyModal_seller_name");
        String seller_address = request.getParameter("modifyModal_seller_address");
        String seller_mobile = request.getParameter("modifyModal_seller_mobile");
        Integer seller_level = Integer.parseInt(request.getParameter("modifyModal_seller_level"));
        FridgeSellerDto fridgeSellerDto = fridgeSellerDao.getById(seller_id);
        fridgeSellerDto.setSeller_name(seller_name);
        fridgeSellerDto.setSeller_address(seller_address);
        fridgeSellerDto.setSeller_level(seller_level);
        fridgeSellerDto.setSeller_mobile(seller_mobile);
        fridgeSellerDao.update(fridgeSellerDto);
        return "redirect:/queryFridgeSeller.do";
    }

    @RequestMapping(value = "getFridgeSellerById")
    public void getFridgeSellerById(HttpServletRequest request,HttpServletResponse response){
        Long seller_id = Long.parseLong(request.getParameter("seller_id"));
        FridgeSellerDto fridgeSellerDto = fridgeSellerDao.getById(seller_id);
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            item.put("result","1");
            item.put("fridgeSeller", fridgeSellerDto);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "delFridgeSeller")
    public String delFridgeSeller(HttpServletRequest request,HttpServletResponse response){
        Long seller_id = Long.parseLong(request.getParameter("seller_id"));
        fridgeSellerDao.delById(seller_id);
        return "redirect:/queryFridgeSeller.do";
    }

    /**
     * 验证商家是否已经存在
     * @param request
     * @param response
     */
    @RequestMapping(value = "validateSellerName")
    public void validateSellerName(HttpServletRequest request,HttpServletResponse response){
        int result = 0;
        String seller_name = request.getParameter("seller_name");
        List<FridgeSellerDto> list = fridgeSellerDao.listByName(seller_name);
        if(list != null && list.size() > 0){
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

    @RequestMapping(value = "addFridgeSeller")
    public String addFridgeSeller(HttpServletRequest request,HttpServletResponse response){
        String seller_name = request.getParameter("seller_name");
        String seller_address = request.getParameter("seller_address");
        Integer seller_level = Integer.parseInt(request.getParameter("seller_level"));
        String seller_mobile = request.getParameter("seller_mobile");
        FridgeSellerDto fridgeSellerDto = new FridgeSellerDto();
        fridgeSellerDto.setSeller_name(seller_name);
        fridgeSellerDto.setSeller_address(seller_address);
        fridgeSellerDto.setSeller_mobile(seller_mobile);
        fridgeSellerDto.setSeller_level(seller_level);
        fridgeSellerDao.add(fridgeSellerDto);
        return "redirect:/queryFridgeSeller.do";
    }

    @RequestMapping(value = "queryFridgeSeller")
    public ModelAndView queryFridgeSeller(ModelAndView modelAndView,HttpServletRequest request, HttpServletResponse response){
        List<FridgeSellerDto> list = fridgeSellerDao.list();
        modelAndView.setViewName("fridgeSellerManager");
        modelAndView.addObject("fridgeSellers",list);
        return modelAndView;
    }

    @RequestMapping(value = "getFridgeBrandById")
    public void getFridgeBrandById(HttpServletRequest request, HttpServletResponse response){
        long brand_id = Long.parseLong(request.getParameter("brand_id"));
        FridgeBrandDto fridgeBrandDto = fridgeBrandDao.getById(brand_id);
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            item.put("result","1");
            item.put("fridgeBrand", fridgeBrandDto);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "delFridgeBrand")
    public String delFridgeBrand(HttpServletRequest request, HttpServletResponse response){
        long brand_id = Long.parseLong(request.getParameter("brand_id"));
        fridgeBrandDao.delFridgeBrand(brand_id);
        return "redirect:/queryFridgeBrand.do";
    }

    @RequestMapping(value = "addFridgeBrandFromJD")
    public String addFridgeBrandFromJD(HttpServletRequest request, HttpServletResponse response) {
        String result = HttpClientUtil.get(BRAND_FROM_JD);
        if (request != null
                && !request.equals("")) {
            JSONObject json = JSONObject.parseObject(result);
            JSONArray array = json.getJSONArray("brands");
            for (int i = 0; i < array.size(); i++) {
                JSONObject object = array.getJSONObject(i);
                String fridge_name = object.getString("name");
                String fridge_pinyin = object.getString("pinyin");
                String fridge_log = object.getString("logo");
                if (fridge_log != null
                        && !"".equals(fridge_log)) {
                    fridge_log = ImageUtil.saveImg("http:" + fridge_log, rootPath, imgPath);
                }
                FridgeBrandDto fridgeBrandDto = new FridgeBrandDto();
                fridgeBrandDto.setFridge_name(fridge_name);
                fridgeBrandDto.setFridge_pinyin(fridge_pinyin);
                fridgeBrandDto.setFridge_logo(fridge_log);
                fridgeBrandDao.addFridgeBrand(fridgeBrandDto);
                System.out.println(">>>>>>add from jd :" + fridge_name);
            }
        }
        return "success";
    }

    @RequestMapping(value = "updateFridgeBrand")
    public String updateFridgeBrand(HttpServletRequest request, HttpServletResponse response) {
        Long brand_id = Long.parseLong(request.getParameter("brand_id"));
        FridgeBrandDto fridgeBrandDto = fridgeBrandDao.getById(brand_id);
        String fridge_name = request.getParameter("modifyModal_fridge_name");
        String fridge_mobile = request.getParameter("modifyModal_fridge_mobile");
        String keep_info = request.getParameter("modifyModal_keep_info");
        String keep_time = request.getParameter("modifyModal_keep_time");
        String fridge_pinyin = request.getParameter("modifyModal_fridge_pinyin");
        String fridge_logo = fridgeBrandDto.getFridge_logo();
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
                        if (file.getOriginalFilename() != null && !file.getOriginalFilename().trim().equals("")) {
                            String path = imgPath + file.getOriginalFilename();
                            fridge_logo = path;
                            String uploadPath = rootPath + path;
                            //上传
                            file.transferTo(new File(uploadPath));
                        }
                    }
                }
            }
            fridgeBrandDto.setFridge_logo(fridge_logo);
            fridgeBrandDto.setFridge_mobile(fridge_mobile);
            fridgeBrandDto.setFridge_name(fridge_name);
            fridgeBrandDto.setKeep_info(keep_info);
            fridgeBrandDto.setKeep_time(keep_time);
            fridgeBrandDto.setFridge_pinyin(fridge_pinyin);
            fridgeBrandDao.update(fridgeBrandDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/queryFridgeBrand.do";
    }

    @RequestMapping(value = "createFridgeBrand")
    public String createFridgeBrand(HttpServletRequest request, HttpServletResponse response) {
        String fridge_name = request.getParameter("fridge_name");
        String fridge_mobile = request.getParameter("fridge_mobile");
        String keep_info = request.getParameter("keep_info");
        String keep_time = request.getParameter("keep_time");
        String fridge_pinyin = request.getParameter("fridge_pinyin");
        String fridge_logo = "";
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
                        if (file.getOriginalFilename() != null && !file.getOriginalFilename().trim().equals("")) {
                            String path = imgPath + file.getOriginalFilename();
                            fridge_logo = path;
                            String uploadPath = rootPath + path;
                            //上传
                            file.transferTo(new File(uploadPath));
                        }
                    }
                }
            }
            FridgeBrandDto fridgeBrandDto = new FridgeBrandDto();
            fridgeBrandDto.setFridge_logo(fridge_logo);
            fridgeBrandDto.setFridge_mobile(fridge_mobile);
            fridgeBrandDto.setFridge_name(fridge_name);
            fridgeBrandDto.setKeep_info(keep_info);
            fridgeBrandDto.setKeep_time(keep_time);
            fridgeBrandDto.setFridge_pinyin(fridge_pinyin);
            fridgeBrandDao.addFridgeBrand(fridgeBrandDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/queryFridgeBrand.do";
    }

    @RequestMapping(value = "queryFridgeBrand")
    public ModelAndView queryFridgeBrand(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {
        List<FridgeBrandDto> list = fridgeBrandDao.list();
        modelAndView.setViewName("fridgeBrandManager");
        modelAndView.addObject("fridgeBrands", list);
        return modelAndView;
    }
}
