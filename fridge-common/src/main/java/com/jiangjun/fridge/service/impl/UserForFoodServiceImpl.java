package com.jiangjun.fridge.service.impl;

import com.jiangjun.fridge.dao.IUserForFoodDao;
import com.jiangjun.fridge.dto.UserForFoodDto;
import com.jiangjun.fridge.service.UserForFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangjun on 16/6/13.
 */
@Service("userForFoodService")
public class UserForFoodServiceImpl implements UserForFoodService {

    @Autowired
    private IUserForFoodDao userForFoodDao;

    public void addUserForFood(UserForFoodDto userForFoodDto) {
        userForFoodDao.addUserForFood(userForFoodDto);
    }

    public List<UserForFoodDto> list() {
        return userForFoodDao.list();
    }

    public List<UserForFoodDto> listByUserId(long user_id) {
        return userForFoodDao.listByUserId(user_id);
    }

    public void delFood(long user_id, long food_id) {
        userForFoodDao.delFood(user_id, food_id);
    }
}
