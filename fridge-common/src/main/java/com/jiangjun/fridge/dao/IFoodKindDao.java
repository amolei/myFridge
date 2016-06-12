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
}
