package com.jiangjun.fridge.service.impl;

import com.jiangjun.fridge.dao.ICrawlerSourceDao;
import com.jiangjun.fridge.dto.CrawlerSource;
import com.jiangjun.fridge.service.CrawlerSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangjun on 2016/7/24.
 */
@Service("crawlerSourceService")
public class CrawlerSourceServiceImpl implements CrawlerSourceService {

    @Autowired
    private ICrawlerSourceDao crawlerSourceDao;

    public void add(CrawlerSource crawlerSource) {
        crawlerSourceDao.add(crawlerSource);
    }

    public List<CrawlerSource> list() {
        return crawlerSourceDao.list();
    }

    public void del(Long source_id) {
        crawlerSourceDao.del(source_id);
    }

    public CrawlerSource getById(Long source_id) {
        return crawlerSourceDao.getById(source_id);
    }
    public void update(CrawlerSource crawlerSource){
        crawlerSourceDao.update(crawlerSource);
    }
    public List<CrawlerSource> listByType(Integer source_type){
        return crawlerSourceDao.listByType(source_type);
    }
}
