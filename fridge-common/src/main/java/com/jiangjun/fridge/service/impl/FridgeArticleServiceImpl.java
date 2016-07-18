package com.jiangjun.fridge.service.impl;

import com.jiangjun.fridge.dao.IFridgeArticleDao;
import com.jiangjun.fridge.dto.FridgeArticle;
import com.jiangjun.fridge.service.FridgeArticleService;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jiangjun on 2016/7/17.
 */
@Service("fridgeArticleService")
public class FridgeArticleServiceImpl implements FridgeArticleService {

    private static Logger logger = Logger.getLogger(FridgeArticleServiceImpl.class);

    @Resource
    private IFridgeArticleDao fridgeArticleDao;

    public void add(FridgeArticle fridgeArticle) {
        fridgeArticleDao.add(fridgeArticle);
    }

    public List<FridgeArticle> listByTitleAndType(String title, int type) {
        return fridgeArticleDao.listByTitleAndType(title, type);
    }

    public List<FridgeArticle> listByPage(int count, int pageSize, int type) {
        count = (count - 1) * pageSize;
        return fridgeArticleDao.listByPage(count, pageSize, type);
    }
}
