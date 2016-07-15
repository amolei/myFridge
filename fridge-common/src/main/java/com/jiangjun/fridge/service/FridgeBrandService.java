package com.jiangjun.fridge.service;

import com.jiangjun.fridge.dto.FridgeBrandDto;

import java.util.List;

/**
 * Created by jiangjun on 16/6/26.
 */
public interface FridgeBrandService {

    public void addFridgeBrand(FridgeBrandDto fridgeBrandDto);

    public List<FridgeBrandDto> list();

    public FridgeBrandDto getById(Long brand_id);

    public void delFridgeBrand(Long brand_id);

    public void update(FridgeBrandDto fridgeBrandDto);
}
