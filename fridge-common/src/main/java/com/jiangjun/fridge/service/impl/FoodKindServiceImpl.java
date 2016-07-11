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
public class FoodKindServiceImpl implements FoodKindService {

    @Resource
    private IFoodKindDao foodKindDao;

    public List<FoodKindDto> list() {
        return foodKindDao.list();
    }

    public void addFoodKind(FoodKindDto foodKindDto) {
        List<FoodKindDto> list = foodKindDao.list();
        if(list != null && list.size() > 0){
            foodKindDto.setSort(list.get(list.size() - 1).getSort() + 1);
        }else {
            foodKindDto.setSort(1);
        }
        foodKindDao.addFoodKind(foodKindDto);
    }

    public FoodKindDto queryById(long kind_id) {
        return foodKindDao.queryById(kind_id);
    }

    public FoodKindDto queryByName(String food_kind_name) {
        return foodKindDao.queryByName(food_kind_name);
    }

    public void delFoodKindById(long kind_id) {
        foodKindDao.delFoodKindById(kind_id);
    }

    public void update(FoodKindDto foodKindDto){foodKindDao.update(foodKindDto);}

    public List<FoodKindDto> listLtBySort(Integer sort){
        return foodKindDao.listLtBySort(sort);
    }

    public List<FoodKindDto> listGtBySort(Integer sort){
        return foodKindDao.listGtBySort(sort);
    }
}
