package com.jiangjun.fridge.service;

import com.jiangjun.fridge.dto.FridgeSellerDto;

import java.util.List;

/**
 * Created by jiangjun on 2016/7/16.
 */
public interface FridgeSellerService {

    public void add(FridgeSellerDto fridgeSellerDto);

    public List<FridgeSellerDto> list();

    public List<FridgeSellerDto> listByName(String seller_name);

    public void delById(Long seller_id);

    public FridgeSellerDto getById(Long seller_id);

    public void update(FridgeSellerDto fridgeSellerDto);
}
