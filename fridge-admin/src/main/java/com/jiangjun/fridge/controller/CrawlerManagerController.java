package com.jiangjun.fridge.controller;

import com.alibaba.fastjson.JSONObject;
import com.jiangjun.fridge.dto.CrawlerSource;
import com.jiangjun.fridge.service.CrawlerSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by jiangjun on 2016/7/24.
 */
@Controller
public class CrawlerManagerController {

    @Autowired
    private CrawlerSourceService crawlerSourceService;

    @RequestMapping(value = "updateCrawlerSource")
    public String updateCrawlerSource(HttpServletRequest request, HttpServletResponse response){
        Long source_id = Long.parseLong(request.getParameter("modifyModal_source_id"));
        String source_name = request.getParameter("modifyModal_source_name");
        String source_path = request.getParameter("modifyModal_source_path");
        Integer source_type = Integer.parseInt(request.getParameter("modifyModal_source_type"));
        CrawlerSource crawlerSource = crawlerSourceService.getById(source_id);
        crawlerSource.setSource_name(source_name);
        crawlerSource.setSource_path(source_path);
        crawlerSource.setSource_type(source_type);
        crawlerSourceService.update(crawlerSource);
        return "redirect:/queryCrawlerSources.do";
    }

    @RequestMapping(value = "getById")
    public void getById(HttpServletRequest request, HttpServletResponse response) {
        Long source_id = Long.parseLong(request.getParameter("source_id"));
        CrawlerSource crawlerSource = crawlerSourceService.getById(source_id);
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json; charset=utf-8");
            JSONObject item = new JSONObject();
            item.put("result", "1");
            item.put("crawlerSource", crawlerSource);
            response.getWriter().write(item.toJSONString());
            response.getWriter().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "delCrawlerSource")
    public String delCrawlerSource(HttpServletRequest request, HttpServletResponse response) {
        Long source_id = Long.parseLong(request.getParameter("source_id"));
        crawlerSourceService.del(source_id);
        return "redirect:/queryCrawlerSources.do";
    }

    @RequestMapping(value = "createCrawlerSource")
    public String createCrawlerSource(HttpServletRequest request, HttpServletResponse response) {
        String source_name = request.getParameter("source_name");
        String source_path = request.getParameter("source_path");
        Integer source_type = Integer.parseInt(request.getParameter("source_type"));
        CrawlerSource crawlerSource = new CrawlerSource();
        crawlerSource.setSource_name(source_name);
        crawlerSource.setSource_path(source_path);
        crawlerSource.setSource_type(source_type);
        crawlerSourceService.add(crawlerSource);
        return "redirect:/queryCrawlerSources.do";
    }

    @RequestMapping(value = "queryCrawlerSources")
    public ModelAndView queryCrawlerSources(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {
        List<CrawlerSource> list = crawlerSourceService.list();
        modelAndView.setViewName("crawlerManager");
        modelAndView.addObject("crawlerSourceList", list);
        return modelAndView;
    }
}
