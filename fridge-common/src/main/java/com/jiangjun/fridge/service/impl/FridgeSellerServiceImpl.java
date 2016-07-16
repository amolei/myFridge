package com.jiangjun.fridge.service.impl;

import com.jiangjun.fridge.dao.IFridgeSellerDao;
import com.jiangjun.fridge.dto.FridgeSellerDto;
import com.jiangjun.fridge.service.FridgeSellerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by jiangjun on 2016/7/16.
 */
public class FridgeSellerServiceImpl implements FridgeSellerService {

    @Autowired
    private IFridgeSellerDao fridgeSellerDao;

    public void add(FridgeSellerDto fridgeSellerDto) {
        fridgeSellerDao.add(fridgeSellerDto);
    }

    public List<FridgeSellerDto> list() {
        return fridgeSellerDao.list();
    }
    public List<FridgeSellerDto> listByName(String seller_name){
        return fridgeSellerDao.listByName(seller_name);
    }

    public void delById(Long seller_id){
        fridgeSellerDao.delById(seller_id);
    }

    public FridgeSellerDto getById(Long seller_id){
        return fridgeSellerDao.getById(seller_id);
    }
    public void update(FridgeSellerDto fridgeSellerDto){
        fridgeSellerDao.update(fridgeSellerDto);
    }

}
