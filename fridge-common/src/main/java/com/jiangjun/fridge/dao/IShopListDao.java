package com.jiangjun.fridge.dao;

import com.jiangjun.fridge.dto.ShopListDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangjun on 16/6/13.
 */
@Repository
public interface IShopListDao {

    public void addShopList(ShopListDto shopListDto);

    public List<ShopListDto> list();
}
