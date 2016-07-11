package com.jiangjun.fridge.service.impl;

import com.jiangjun.fridge.dao.IFoodInfoDao;
import com.jiangjun.fridge.dao.IFoodKindDao;
import com.jiangjun.fridge.dto.FoodInfoDto;
import com.jiangjun.fridge.service.FoodInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangjun on 16/6/12.
 */
@Service("foodInfoService")
public class FoodInfoServiceImpl implements FoodInfoService {

    @Autowired
    private IFoodInfoDao foodInfoDao;

    @Autowired
    private IFoodKindDao foodKindDao;

    public void addFoodInfo(FoodInfoDto foodInfoDto) {
        List<FoodInfoDto> list = foodInfoDao.listByKindId(foodInfoDto.getKind_id());
        if(list != null && list.size() > 0){
            foodInfoDto.setSort(list.get(list.size() - 1).getSort() + 1);
        }else {
            foodInfoDto.setSort(1);
        }
        foodInfoDao.addFoodInfo(foodInfoDto);
    }

    public List<FoodInfoDto> list() {
        return foodInfoDao.list();
    }

    public List<FoodInfoDto> listByKindId(Long kind_id) {
        return foodInfoDao.listByKindId(kind_id);
    }

    public FoodInfoDto getFoodInfoById(long food_id) {
        return foodInfoDao.getFoodInfoById(food_id);
    }

    public FoodInfoDto getFoodInfoByName(String food_name) {
        return foodInfoDao.getFoodInfoByName(food_name);
    }

    public void delFoodInfoById(long food_id) {
        foodInfoDao.delFoodInfoById(food_id);
    }

    public List<FoodInfoDto> listBySName(String keywords){
        return foodInfoDao.listBySName(keywords);
    }

    public void update(FoodInfoDto foodInfoDto){
        foodInfoDao.update(foodInfoDto);
    }

    public List<FoodInfoDto> listLtBySort(FoodInfoDto foodInfoDto){
        return foodInfoDao.listLtBySort(foodInfoDto);
    }

    public List<FoodInfoDto> listGtBySort(FoodInfoDto foodInfoDto){
        return foodInfoDao.listGtBySort(foodInfoDto);
    }
}
