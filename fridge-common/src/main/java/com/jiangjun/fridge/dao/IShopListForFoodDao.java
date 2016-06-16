package com.jiangjun.fridge.dao;

import com.jiangjun.fridge.dto.ShopListDto;
import com.jiangjun.fridge.dto.ShopListForFoodDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangjun on 16/6/13.
 */
@Repository
public interface IShopListForFoodDao {

    public void addShopListForFood(ShopListForFoodDto shopListForFood);

    public List<ShopListForFoodDto> list();

    public List<ShopListForFoodDto> listByShopListId(Long shop_list_id);

    public ShopListForFoodDto queryByShopListAndFoodId(@Param("shop_list_id")Long shop_list_id,@Param("food_id")Long food_id);

    public void updateStatus(ShopListForFoodDto shopListForFoodDto);

}
