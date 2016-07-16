package com.jiangjun.fridge.dao;

import com.jiangjun.fridge.dto.FridgeSellerDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangjun on 2016/7/16.
 */
@Repository
public interface IFridgeSellerDao {

    public void add(FridgeSellerDto fridgeSellerDto);

    public List<FridgeSellerDto> list();

    public List<FridgeSellerDto> listByName(String seller_name);

    public void delById(Long seller_id);

    public FridgeSellerDto getById(Long seller_id);

    public void update(FridgeSellerDto fridgeSellerDto);
}
