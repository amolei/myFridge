package com.jiangjun.fridge.service;

import com.jiangjun.fridge.dto.ShopListForFoodDto;

import java.util.List;

/**
 * Created by jiangjun on 16/6/13.
 */
public interface ShopListForFoodService {
    public void addShopListForFood(ShopListForFoodDto shopListForFood);

    public List<ShopListForFoodDto> list();

    public List<ShopListForFoodDto> listByShopListId(Long shop_list_id);

    public ShopListForFoodDto queryByShopListAndFoodId(long shop_list_id,long food_id);

    public void updateStatus(ShopListForFoodDto shopListForFoodDto);

    public void delByShopListId(long shop_list_id);
}
