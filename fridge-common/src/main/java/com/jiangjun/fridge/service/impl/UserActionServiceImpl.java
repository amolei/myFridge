package com.jiangjun.fridge.service.impl;

import com.jiangjun.fridge.dao.IUserActionDao;
import com.jiangjun.fridge.dto.UserAction;
import com.jiangjun.fridge.service.UserActionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangjun on 2016/7/20.
 */
@Service("userActionService")
public class UserActionServiceImpl implements UserActionService {

    @Autowired
    private IUserActionDao userActionDao;

    public void add(UserAction userAction) {
        userActionDao.add(userAction);
    }

    public List<UserAction> list() {
        return userActionDao.list();
    }

    public List<UserAction> listByTypeAndArticleId(@Param("action_type") int action_type, @Param("article_id") long article_id) {
        return userActionDao.listByTypeAndArticleId(action_type, article_id);
    }

    public List<UserAction> listByParam(@Param("article_id") long article_id, @Param("action_type") int action_type, @Param("user_id") long user_id) {
        return userActionDao.listByParam(article_id, action_type, user_id);
    }

    public UserAction queryById(long action_id) {
        return userActionDao.queryById(action_id);
    }
}
