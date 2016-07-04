package com.jiangjun.fridge.dao;

import com.jiangjun.fridge.dto.ShopListDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangjun on 16/6/13.
 */
@Repository
public interface IShopListDao {

    public Long addShopList(ShopListDto shopListDto);

    public List<ShopListDto> list();

    public List<ShopListDto> listByUserId(long user_id);

    public void delShopListById(long shop_list_id);
}
