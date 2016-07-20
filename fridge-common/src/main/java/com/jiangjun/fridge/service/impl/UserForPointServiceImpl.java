package com.jiangjun.fridge.service.impl;

import com.jiangjun.fridge.dao.IUserForPointDao;
import com.jiangjun.fridge.dto.UserForPoint;
import com.jiangjun.fridge.service.UserForPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jiangjun on 2016/7/21.
 */
@Service("userForPointService")
public class UserForPointServiceImpl implements UserForPointService {

    @Autowired
    private IUserForPointDao userForPointDao;

    public void add(UserForPoint userForPoint) {
        userForPointDao.add(userForPoint);
    }
}
