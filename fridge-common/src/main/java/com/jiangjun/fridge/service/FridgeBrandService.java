package com.jiangjun.fridge.service;

import com.jiangjun.fridge.dto.FridgeBrandDto;

import java.util.List;

/**
 * Created by jiangjun on 16/6/26.
 */
public interface FridgeBrandService {

    public void addFridgeBrand(FridgeBrandDto fridgeBrandDto);

    public List<FridgeBrandDto> list();
}
