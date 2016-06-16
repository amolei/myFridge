package com.jiangjun.fridge.service;

import com.jiangjun.fridge.dto.UserForFoodDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jiangjun on 16/6/13.
 */
public interface UserForFoodService {
    public void addUserForFood(UserForFoodDto userForFoodDto);
    public List<UserForFoodDto> list();
    public List<UserForFoodDto> listByUserId(long user_id);
    public void delFood(long user_id,long food_id);
}
