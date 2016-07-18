package com.jiangjun.fridge.service;

import com.jiangjun.fridge.dto.FridgeArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jiangjun on 2016/7/17.
 */
public interface FridgeArticleService {

    public void add(FridgeArticle fridgeArticle);

    public List<FridgeArticle> listByTitleAndType(String title, int type);

    public List<FridgeArticle> listByPage(int count, int pageSize, int type);
}
