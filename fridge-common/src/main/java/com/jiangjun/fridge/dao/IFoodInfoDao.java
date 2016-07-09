package com.jiangjun.fridge.dao;

import com.jiangjun.fridge.dto.FoodInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangjun on 16/6/12.
 */
@Repository
public interface IFoodInfoDao {

    public void addFoodInfo(FoodInfoDto foodInfoDto);

    public List<FoodInfoDto> list();

    public List<FoodInfoDto> listByKindId(Long kind_id);

    public FoodInfoDto getFoodInfoById(long food_id);

    public FoodInfoDto getFoodInfoByName(String food_name);

    public void delFoodInfoById(long food_id);

    public List<FoodInfoDto> listBySName(String keywords);

    public void update(FoodInfoDto foodInfoDto);
}
