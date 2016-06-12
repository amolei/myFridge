package com.jiangjun.fridge.service;

import com.jiangjun.fridge.dto.FoodKindDto;

import java.util.List;

/**
 * Created by jiangjun on 16/6/12.
 */
public interface FoodKindService {

    public void addFoodKind(FoodKindDto foodKindDto);

    public List<FoodKindDto> list();
}
