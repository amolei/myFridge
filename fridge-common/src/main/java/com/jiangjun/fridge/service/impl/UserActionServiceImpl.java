package com.jiangjun.fridge.service.impl;

import com.jiangjun.fridge.dao.IUserActionDao;
import com.jiangjun.fridge.dto.UserAction;
import com.jiangjun.fridge.service.UserActionService;
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
}
