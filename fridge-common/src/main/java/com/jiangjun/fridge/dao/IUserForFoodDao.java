package com.jiangjun.fridge.dao;

import com.jiangjun.fridge.dto.UserForFoodDto;
import org.apache.ibatis.annotations.Param;
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

    public void delFood(@Param("user_id") long user_id, @Param("food_id") long food_id);
}
