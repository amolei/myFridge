package com.jiangjun.fridge.service.impl;

import com.jiangjun.fridge.dao.IFridgeBrandDao;
import com.jiangjun.fridge.dto.FridgeBrandDto;
import com.jiangjun.fridge.service.FridgeBrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jiangjun on 16/6/26.
 */
@Service("fridgeBrandService")
public class FridgeBrandServiceImpl implements FridgeBrandService {

    @Resource
    private IFridgeBrandDao fridgeBrandDao;

    public void addFridgeBrand(FridgeBrandDto fridgeBrandDto) {
        fridgeBrandDao.addFridgeBrand(fridgeBrandDto);
    }

    public List<FridgeBrandDto> list() {
        return fridgeBrandDao.list();
    }

    public FridgeBrandDto getById(Long brand_id) {
        return fridgeBrandDao.getById(brand_id);
    }

    public void delFridgeBrand(Long brand_id){
        fridgeBrandDao.delFridgeBrand(brand_id);
    }

    public void update(FridgeBrandDto fridgeBrandDto){
        fridgeBrandDao.update(fridgeBrandDto);
    }
}
