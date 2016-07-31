package com.jiangjun.fridge.service;

import com.jiangjun.fridge.dto.CrawlerSource;

import java.util.List;

/**
 * Created by jiangjun on 2016/7/24.
 */
public interface CrawlerSourceService {

    public void add(CrawlerSource crawlerSource);
    public List<CrawlerSource> list();
    public void del(Long source_id);
    public CrawlerSource getById(Long source_id);
    public void update(CrawlerSource crawlerSource);
    public List<CrawlerSource> listByType(Integer source_type);
}
