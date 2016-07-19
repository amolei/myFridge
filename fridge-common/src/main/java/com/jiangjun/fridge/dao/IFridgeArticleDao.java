package com.jiangjun.fridge.dao;

import com.jiangjun.fridge.dto.FridgeArticle;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangjun on 2016/7/17.
 */
@Repository
public interface IFridgeArticleDao {

    public void add(FridgeArticle fridgeArticle);

    public FridgeArticle getById(long article_id);

    public void update(FridgeArticle fridgeArticle);

    public List<FridgeArticle> listByType(int type);

    public List<FridgeArticle> listByTitleAndType(@Param("title") String title, @Param("type") int type);

    /**
     *
     * @param count 页数
     * @param pageSize  每页的条数
     * @param type  类型，1是九宫格
     * @return
     */
    public List<FridgeArticle> listByPage(@Param("count") int count, @Param("pageSize") int pageSize, @Param("type") int type);
}
