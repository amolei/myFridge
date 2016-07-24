package com.jiangjun.fridge.dao;

import com.jiangjun.fridge.dto.UserAction;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangjun on 2016/7/20.
 */
@Repository
public interface IUserActionDao {

    public UserAction queryById(long action_id);

    public void add(UserAction userAction);

    public List<UserAction> list();

    public List<UserAction> listByTypeAndArticleId(@Param("action_type") int action_type, @Param("article_id") long article_id);

    public List<UserAction> listByParam(@Param("article_id") long article_id, @Param("action_type") int action_type, @Param("user_id") long user_id);
}
