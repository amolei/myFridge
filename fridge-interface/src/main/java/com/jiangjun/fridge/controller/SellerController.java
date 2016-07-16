package com.jiangjun.fridge.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiangjun.fridge.bean.ResBody;
import com.jiangjun.fridge.dao.IFridgeSellerDao;
import com.jiangjun.fridge.dto.FridgeSellerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by jiangjun on 2016/7/17.
 */
@Controller
public class SellerController {

    @Autowired
    private IFridgeSellerDao fridgeSellerDao;

    @RequestMapping(value = "querySellers")
    public void querySellers(HttpServletRequest request, HttpServletResponse response) {
        List<FridgeSellerDto> list = fridgeSellerDao.list();
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
}
