package com.jiangjun.fridge.service.impl;

import com.jiangjun.fridge.dao.IShopListForFoodDao;
import com.jiangjun.fridge.dto.ShopListForFoodDto;
import com.jiangjun.fridge.service.ShopListForFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangjun on 16/6/13.
 */
@Service("shopListForFoodService")
public class ShopListForFoodServiceImpl implements ShopListForFoodService {

    @Autowired
    private IShopListForFoodDao shopListForFoodDao;

    public void addShopListForFood(ShopListForFoodDto shopListForFood) {
        shopListForFoodDao.addShopListForFood(shopListForFood);
    }

    public List<ShopListForFoodDto> list() {
        return shopListForFoodDao.list();
    }

    public List<ShopListForFoodDto> listByShopListId(Long shop_list_id) {
        return shopListForFoodDao.listByShopListId(shop_list_id);
    }
}
