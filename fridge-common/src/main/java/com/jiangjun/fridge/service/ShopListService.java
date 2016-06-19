package com.jiangjun.fridge.service;

import com.jiangjun.fridge.dto.ShopListDto;

import java.util.List;

/**
 * Created by jiangjun on 16/6/13.
 */
public interface ShopListService {

    public Long addShopList(ShopListDto shopListDto);

    public List<ShopListDto> list();

    public List<ShopListDto> listByUserId(long user_id);
}
