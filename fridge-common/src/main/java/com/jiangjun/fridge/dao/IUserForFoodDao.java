package com.jiangjun.fridge.dao;

import com.jiangjun.fridge.dto.UserForFoodDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangjun on 16/6/13.
 */
@Repository
public interface IUserForFoodDao {

    public void addUserForFood(UserForFoodDto userForFoodDto);
    public List<UserForFoodDto> list();
    public List<UserForFoodDto> listByUserId(long user_id);
}
