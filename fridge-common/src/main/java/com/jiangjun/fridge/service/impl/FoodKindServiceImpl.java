package com.jiangjun.fridge.service.impl;

import com.jiangjun.fridge.dao.IFoodKindDao;
import com.jiangjun.fridge.dto.FoodKindDto;
import com.jiangjun.fridge.service.FoodKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jiangjun on 16/6/12.
 */
@Service("foodKindService")
public class FoodKindServiceImpl implements FoodKindService{

    @Resource
    private IFoodKindDao foodKindDao;

    public List<FoodKindDto> list() {
        return foodKindDao.list();
    }

    public void addFoodKind(FoodKindDto foodKindDto) {
        foodKindDao.addFoodKind(foodKindDto);
    }
}
