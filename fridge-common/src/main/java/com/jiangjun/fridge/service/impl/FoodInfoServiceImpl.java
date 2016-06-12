package com.jiangjun.fridge.service.impl;

import com.jiangjun.fridge.dao.IFoodInfoDao;
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

    public void addFoodInfo(FoodInfoDto foodInfoDto) {
        foodInfoDao.addFoodInfo(foodInfoDto);
    }

    public List<FoodInfoDto> list() {
        return foodInfoDao.list();
    }

    public List<FoodInfoDto> listByKindId(Long kind_id) {
        return foodInfoDao.listByKindId(kind_id);
    }
}
