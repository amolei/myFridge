package com.jiangjun.fridge.dao;

import com.jiangjun.fridge.dto.FoodKindDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangjun on 16/6/12.
 */
@Repository
public interface IFoodKindDao {

    public void addFoodKind(FoodKindDto foodKindDto);

    public List<FoodKindDto> list();

    public FoodKindDto queryById(long kind_id);

    public FoodKindDto queryByName(String food_kind_name);

    public void delFoodKindById(long kind_id);

    public void update(FoodKindDto foodKindDto);

    public List<FoodKindDto> listLtBySort(Integer sort);

    public List<FoodKindDto> listGtBySort(Integer sort);
}
