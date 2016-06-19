package com.jiangjun.fridge.service.impl;

import com.jiangjun.fridge.dao.IShopListDao;
import com.jiangjun.fridge.dto.ShopListDto;
import com.jiangjun.fridge.service.ShopListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangjun on 16/6/13.
 */
@Service("shopListService")
public class ShopListServiceImpl implements ShopListService {

    @Autowired
    private IShopListDao shopListDao;

    public Long addShopList(ShopListDto shopListDto) {
        return shopListDao.addShopList(shopListDto);
    }

    public List<ShopListDto> list() {
        return shopListDao.list();
    }

    public List<ShopListDto> listByUserId(long user_id) {
        return shopListDao.listByUserId(user_id);
    }
}
